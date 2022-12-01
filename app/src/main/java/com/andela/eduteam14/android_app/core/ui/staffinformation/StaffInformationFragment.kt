package com.andela.eduteam14.android_app.core.ui.staffinformation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.data.preferences.PreferenceRepository
import com.andela.eduteam14.android_app.core.ui.SchoolBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.extensions.onChange
import com.andela.eduteam14.android_app.core.ui.extensions.onClick
import com.andela.eduteam14.android_app.core.ui.extensions.snackBar
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModelFactory
import com.andela.eduteam14.android_app.databinding.FragmentAddSchoolBinding
import com.andela.eduteam14.android_app.databinding.FragmentStaffInformationBinding
import com.google.android.material.textfield.TextInputEditText

class StaffInformationFragment : Fragment(), UiAction {
    private lateinit var save: Button
    private lateinit var femaleStaff: TextInputEditText
    private lateinit var maleStaff: TextInputEditText

    private var _binding: FragmentStaffInformationBinding? = null
    private val binding get() = _binding

    private lateinit var pref: PreferenceRepository

    private val viewModel: SchoolViewModel by viewModels {
        SchoolViewModelFactory(
            (activity as SchoolBaseActivity).coreComponent.repository,
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentStaffInformationBinding.inflate(inflater, container, false)

        return binding?.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        pref = PreferenceRepository.getInstance(requireContext())

        initViews()

        handleInput()

    }

    private fun handleInput() {
        var numberOfMaleStaff = "0"

        var numberOfFemaleStaff = "0"

        maleStaff.onChange { numberOfMaleStaff = it }

        femaleStaff.onChange { numberOfFemaleStaff = it }

        save.onClick {
            if (numberOfFemaleStaff.isNotEmpty() && numberOfMaleStaff.isNotEmpty()) {

                pref.saveTotalMaleStaff(numberOfMaleStaff.toLong())

                pref.saveTotalFemaleStaff(numberOfFemaleStaff.toLong())

                snackBar(binding?.StaffInfoFragmentSaveBtn as View, getString(R.string.completed))

                findNavController().navigate(
                    R.id.action_staffInformationFragment_to_homeSchoolFragment
                )
            }
        }
    }

    override fun initViews() {
        maleStaff = binding?.StaffInfoFragmentMaleStaffLayoutEdit!!
        femaleStaff = binding?.StaffInfoFragmentFemaleStaffLayoutEdit!!
        save = binding?.StaffInfoFragmentSaveBtn!!
    }

    override fun onDestroyComponents() {

    }


}