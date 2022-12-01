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
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.data.models.LocalClassAttendance
import com.andela.eduteam14.android_app.core.data.preferences.PreferenceRepository
import com.andela.eduteam14.android_app.core.domain.usecase.ChooseOrganizationDialogUseCase
import com.andela.eduteam14.android_app.core.domain.usecase.DateTodayUseCase
import com.andela.eduteam14.android_app.core.ui.SchoolBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.extensions.onChange
import com.andela.eduteam14.android_app.core.ui.extensions.onClick
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModelFactory
import com.andela.eduteam14.android_app.databinding.FragmentStudentAttendanceBinding
import com.google.android.material.textfield.TextInputEditText
import kotlin.properties.Delegates

class StudentAttendanceFragment : Fragment(), UiAction {

    private lateinit var submit: Button
    private lateinit var date: TextView
    private var _binding: FragmentStudentAttendanceBinding? = null
    private val binding get() = _binding

    private lateinit var next: Button
    private lateinit var previous: Button

    private lateinit var males: TextInputEditText
    private lateinit var females: TextInputEditText
    private lateinit var className: TextView

    private lateinit var today: DateTodayUseCase

    private lateinit var pref: PreferenceRepository

    private var maxClasses by Delegates.notNull<Int>()

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

        viewModel.setMaxClasses(pref)

        maxClasses = viewModel.maxClasses

        enableNextNotPrevious()

        pref = PreferenceRepository.getInstance(requireContext())

        viewModel.setMaxClasses(pref)

        today = DateTodayUseCase()

        handleInput()

    }

    private fun handleInput() {

        date.text = today()

        var maleStudents = "0"

        var femaleStudents = "0"

        manageButton()

        males.onChange { maleStudents = it }

        females.onChange { femaleStudents = it }

        next.onClick {

           if(maleStudents.isNotEmpty() && femaleStudents.isNotEmpty()) {
               clearField()

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

        }

        previous.onClick {

        }


    }

    private fun clearField() {
        males.setText(0.toString())
        females.setText(0.toString())
    }

    private fun manageButton() {
        when (viewModel.currentClass) {
            1 -> {
                enableNextNotPrevious()
                showBoth()
                submit.visibility = View.INVISIBLE
            }

            in 2 until maxClasses -> {
                enablePreviousNext()
                showBoth()
                submit.visibility = View.INVISIBLE
            }

            maxClasses -> {
                hideBoth()
                submit.visibility = View.VISIBLE
            }
        }
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
    }


    private fun showBoth() {
        previous.visibility = View.VISIBLE
        next.visibility = View.VISIBLE
    }

    private fun enableNextNotPrevious() {
        previous.isEnabled = false
        next.isEnabled = false
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