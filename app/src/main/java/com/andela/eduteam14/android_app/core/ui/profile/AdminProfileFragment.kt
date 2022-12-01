package com.andela.eduteam14.android_app.core.ui.profile

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.preference.PreferenceDataStore
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.data.models.CreateAdminRequest
import com.andela.eduteam14.android_app.core.data.preferences.PreferenceRepository
import com.andela.eduteam14.android_app.core.ui.SchoolBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.extensions.onChange
import com.andela.eduteam14.android_app.core.ui.extensions.onClick
import com.andela.eduteam14.android_app.core.ui.extensions.snackBar
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModelFactory
import com.andela.eduteam14.android_app.databinding.FragmentAdminProfileBinding
import com.andela.eduteam14.android_app.databinding.FragmentSettingsSchoolBinding
import com.google.android.material.textfield.TextInputEditText

class AdminProfileFragment : Fragment(), UiAction {

    private lateinit var username: TextInputEditText
    private lateinit var phone: TextInputEditText
    private lateinit var save: Button
    private var _binding: FragmentAdminProfileBinding? = null

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
        _binding = FragmentAdminProfileBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        pref = PreferenceRepository.getInstance(requireContext())

        (activity as SchoolBaseActivity).hideFab()


        handleInput()
    }

    private fun handleInput() {

        val createAdminRequest = CreateAdminRequest()

        username.onChange { createAdminRequest.AdminName = it }

        phone.onChange { createAdminRequest.AdminPhone = it }


        viewModel.adminId {
            createAdminRequest.AdminId = it
        }

        createAdminRequest.Account = pref.retrieveUserAccount()


        save.onClick {
            viewModel.setAdminRequest(createAdminRequest)

            pref.saveAdmin(createAdminRequest.toModel()) //

            viewModel.createAdmin {
                if (it) {
                    snackBar(binding?.root as View, getString(R.string.completed))

                    findNavController().navigate(
                        R.id.action_adminProfileFragment_to_homeSchoolFragment
                    )
                }
            }
        }
    }


    override fun initViews() {
        username = binding?.AdminProfileFragmentUsernameLayoutEdit!!
        phone = binding?.AdminProfileFragmentPhoneLayoutEdit!!
        save = binding?.AdminProfileFragmentSaveBtn!!

    }

    override fun onDestroyComponents() {
        _binding = null
    }

    override fun onDestroy() {
        onDestroyComponents()
        super.onDestroy()
    }

}