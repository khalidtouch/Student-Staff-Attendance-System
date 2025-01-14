package com.andela.eduteam14.android_app.core.ui.attendance.staff

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
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.data.preferences.PreferenceRepository
import com.andela.eduteam14.android_app.core.domain.usecase.DateTodayUseCase
import com.andela.eduteam14.android_app.core.ui.OrganizationBaseActivity
import com.andela.eduteam14.android_app.core.ui.SchoolBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.extensions.onChange
import com.andela.eduteam14.android_app.core.ui.extensions.onClick
import com.andela.eduteam14.android_app.core.ui.extensions.snackBar
import com.andela.eduteam14.android_app.core.ui.home.OrganizationHomeAdapter
import com.andela.eduteam14.android_app.core.ui.viewmodel.OrganizationViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.OrganizationViewModelFactory
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModelFactory
import com.andela.eduteam14.android_app.databinding.FragmentAttendanceOrganizationBinding
import com.andela.eduteam14.android_app.databinding.FragmentStaffAttendanceBinding
import com.google.android.material.textfield.TextInputEditText
import com.google.android.material.textfield.TextInputLayout

class StaffAttendanceFragment : Fragment(), UiAction {


    private lateinit var females: TextInputEditText
    private lateinit var males: TextInputEditText
    private lateinit var date: TextView
    private lateinit var commit: Button

    private lateinit var today: DateTodayUseCase

    private var _binding: FragmentStaffAttendanceBinding? = null

    private lateinit var pref: PreferenceRepository

    private val binding get() = _binding

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
        _binding = FragmentStaffAttendanceBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        today = DateTodayUseCase()

        pref = PreferenceRepository.getInstance(requireContext())

        (activity as SchoolBaseActivity).recordFab.hide()

        handleInputs()
    }

    private fun handleInputs() {
        var maleStaffPresent = "0"
        var femaleStaffPresent = "0"

        date.text = today()

        males.onChange {
            maleStaffPresent = it
        }

        females.onChange {
            femaleStaffPresent = it
        }

        commit.onClick {
            pref.saveMaleStaffPresent(maleStaffPresent.toLong())
            pref.saveFemaleStaffPresent(femaleStaffPresent.toLong())

            findNavController().navigate(
                R.id.action_staffAttendanceFragment_to_homeSchoolFragment
            )
        }
    }


    override fun initViews() {
        date = binding?.dateModified!!
        males = binding?.StaffAttendanceFragmentMaleEdit!!
        females = binding?.StaffAttendanceFragmentFemaleEdit!!
        commit = binding?.StaffAttendanceFragmentCommitBtn!!
    }

    override fun onDestroyComponents() {
        _binding = null
    }

    override fun onDestroy() {
        onDestroyComponents()
        super.onDestroy()
    }

}