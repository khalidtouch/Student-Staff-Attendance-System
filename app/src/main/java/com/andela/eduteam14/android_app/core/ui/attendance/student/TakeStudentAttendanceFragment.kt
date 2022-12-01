package com.andela.eduteam14.android_app.core.ui.attendance.student

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.data.firebase.manager.firestore.FireStoreManagerImpl
import com.andela.eduteam14.android_app.core.data.models.LocalAdmin
import com.andela.eduteam14.android_app.core.data.models.LocalDailyAttendance
import com.andela.eduteam14.android_app.core.data.preferences.PreferenceRepository
import com.andela.eduteam14.android_app.core.domain.listeners.OnPublishDailyAttendanceListener
import com.andela.eduteam14.android_app.core.domain.usecase.ChooseMemberDialogUseCase
import com.andela.eduteam14.android_app.core.domain.usecase.DateTodayUseCase
import com.andela.eduteam14.android_app.core.ui.SchoolBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.extensions.onChange
import com.andela.eduteam14.android_app.core.ui.extensions.onClick
import com.andela.eduteam14.android_app.core.ui.extensions.snackBar
import com.andela.eduteam14.android_app.core.ui.home.SchoolHomeAdapter
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModelFactory
import com.andela.eduteam14.android_app.databinding.FragmentAttendanceSchoolBinding
import com.andela.eduteam14.android_app.databinding.FragmentStudentAttendanceBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import java.util.UUID
import kotlin.properties.Delegates

class TakeStudentAttendanceFragment : Fragment(), UiAction {
    private var _binding: FragmentStudentAttendanceBinding? = null

    private val binding get() = _binding

    private lateinit var submit: Button

    private lateinit var date: TextView

    private lateinit var next: Button
    private lateinit var previous: Button

    private lateinit var males: TextInputEditText
    private lateinit var females: TextInputEditText
    private lateinit var className: TextView

    private lateinit var today: DateTodayUseCase

    private lateinit var pref: PreferenceRepository

    private var maxClasses by Delegates.notNull<Int>()

    private var publishListener: OnPublishDailyAttendanceListener? = null

    private val viewModel: SchoolViewModel by viewModels {
        SchoolViewModelFactory(
            (activity as SchoolBaseActivity).coreComponent.repository
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStudentAttendanceBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)



        initViews()

        (activity as SchoolBaseActivity).recordFab.hide()

        pref = PreferenceRepository.getInstance(requireContext())

        maxClasses = viewModel.maxClasses

        viewModel.setMaxClasses(pref)


        // enableNextNotPrevious()


        viewModel.setMaxClasses(pref)

        today = DateTodayUseCase()

        handleInput()


    }

    private fun handleInput() {
        date.text = today()

        val allClasses = pref.retrieveClassNames()

        if (allClasses.isNotEmpty()) className.text = allClasses[viewModel.currentClass - 1] else
            className.text = getString(R.string.no_class_specified)

        var maleStudents = "0"

        var femaleStudents = "0"

        manageButton()

        males.onChange { maleStudents = it }

        females.onChange { femaleStudents = it }

        next.onClick {

            if (allClasses.isNotEmpty()) {
                if (maleStudents.isNotEmpty() && femaleStudents.isNotEmpty()) {

                    if (viewModel.currentClass < viewModel.maxClasses) {

                        clearField()

                        if (allClasses.isNotEmpty()) className.text =
                            allClasses[viewModel.currentClass - 1] else
                            className.text = getString(R.string.no_class_specified)

                        viewModel.updateMaleStudents(maleStudents)

                        viewModel.updateFemaleStudents(femaleStudents)

                        viewModel.addInstanceToPages(
                            viewModel.currentClass.toString(),
                            maleStudents,
                            femaleStudents,
                        )

                        viewModel.currentClass += 1

                        manageButton()


                    }


                    if (viewModel.currentClass >= viewModel.maxClasses) {
                        hideBoth()
                    }

                }
            }


        }

        previous.onClick {
            snackBar(
                binding?.StudentAttendanceFragmentPreviousBtn as View,
                getString(R.string.not_yet_implemented)
            )
        }

        submit.onClick {
            onSubmit()

            findNavController().navigate(
                R.id.action_takeStudentAttendanceFragment_to_homeSchoolFragment
            )
        }

    }

    private fun onSubmit() {
        viewModel.saveAllMaleStudentsPresent(viewModel.maleStudentsPresent, pref)

        viewModel.saveAllFemaleStudentsPresent(viewModel.femaleStudentsPresent, pref)

        val admin: LocalAdmin = pref.retrieveAdmin()

        val attendance = LocalDailyAttendance(
            AttendanceId = UUID.randomUUID().toString(),
            AdminName = admin.AdminName,
            SchoolName = pref.retrieveSchoolName(),
            MaleStudentsTotal = pref.retrieveTotalMaleStudents(),
            FemaleStudentsTotal = pref.retrieveTotalFemaleStudents(),
            MaleStudentsPresent = pref.retrieveMaleStudentsPresent(),
            FemaleStudentsPresent = pref.retrieveFemaleStudentsPresent(),
            MaleStaffTotal = pref.retrieveTotalMaleStaff(),
            FemaleStaffTotal = pref.retrieveTotalFemaleStaff(),
            FemaleStaffPresent = pref.retrieveFemaleStaffPresent(),
            MaleStaffPresent = pref.retrieveMaleStaffPresent(),
            DateModified = today(),
        )

        viewModel.onPublish(attendance)
    }

    private fun clearField() {
        males.setText("")
        females.setText("")
    }


    private fun manageButton() {
        when (viewModel.currentClass) {
            1 -> {
                // enableNextNotPrevious()
                showBoth()
                submit.visibility = View.INVISIBLE
            }

            in 2 until maxClasses -> {
                //  enablePreviousNext()
                showBoth()
                submit.visibility = View.INVISIBLE
            }

            maxClasses -> {
                hideBoth()
                submit.visibility = View.VISIBLE
            }
        }
    }

    private fun enableNextNotPrevious() {
        previous.isEnabled = false
        next.isEnabled = false
    }

    private fun enablePreviousNext() {
        previous.isEnabled = true
        next.isEnabled = true
    }

    private fun enablePreviousNotNext() {
        previous.isEnabled = true
        next.isEnabled = false
    }

    private fun hideBoth() {
        previous.visibility = View.INVISIBLE
        next.visibility = View.INVISIBLE
        submit.visibility = View.VISIBLE
    }


    private fun showBoth() {
        previous.visibility = View.VISIBLE
        next.visibility = View.VISIBLE
        submit.visibility = View.INVISIBLE
    }


    override fun initViews() {
        next = binding?.StudentAttendanceFragmentNextBtn!!
        previous = binding?.StudentAttendanceFragmentPreviousBtn!!
        className = binding?.className!!
        males = binding?.StudentAttendanceFragmentMaleEdit!!
        females = binding?.StudentAttendanceFragmentFemaleEdit!!
        date = binding?.dateModified!!
        submit = binding?.StudentAttendanceFragmentSubmitBtn!!
    }

    override fun onDestroyComponents() {
        _binding = null
    }

    override fun onDestroy() {
        onDestroyComponents()
        super.onDestroy()

    }

}