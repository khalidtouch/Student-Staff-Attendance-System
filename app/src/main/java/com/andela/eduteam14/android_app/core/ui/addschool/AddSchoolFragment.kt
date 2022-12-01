package com.andela.eduteam14.android_app.core.ui.addschool

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.data.models.CreateOrganizationRequest
import com.andela.eduteam14.android_app.core.data.models.CreateSchoolRequest
import com.andela.eduteam14.android_app.core.data.preferences.PreferenceRepository
import com.andela.eduteam14.android_app.core.ui.OrganizationBaseActivity
import com.andela.eduteam14.android_app.core.ui.SchoolBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.extensions.onChange
import com.andela.eduteam14.android_app.core.ui.extensions.onClick
import com.andela.eduteam14.android_app.core.ui.extensions.snackBar
import com.andela.eduteam14.android_app.core.ui.viewmodel.OrganizationViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.OrganizationViewModelFactory
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModelFactory
import com.andela.eduteam14.android_app.databinding.FragmentAddSchoolBinding
import com.andela.eduteam14.android_app.databinding.FragmentRegisterBinding
import com.google.android.material.textfield.TextInputEditText
import kotlinx.coroutines.launch
import java.util.UUID

class AddSchoolFragment : Fragment(), UiAction {

    private lateinit var createBtn: Button
    private lateinit var address: TextInputEditText
    private lateinit var location: TextInputEditText
    private lateinit var name: TextInputEditText
    private var _binding: FragmentAddSchoolBinding? = null
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
        _binding = FragmentAddSchoolBinding.inflate(inflater, container, false)

        return binding?.root
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        pref = PreferenceRepository.getInstance(requireContext())

        handleInput()
    }

    private fun handleInput() {
        val request = CreateSchoolRequest()

        name.onChange {
            request.SchoolName = it
            viewModel.setSchoolRequest(request)
        }

        location.onChange {
            request.SchoolLocation = it
            viewModel.setSchoolRequest(request)
        }

        address.onChange {
            request.Address = it
            viewModel.setSchoolRequest(request)
        }

        viewModel.activeAdminEmail {
            request.AdminEmail = it
        }

        request.DateModified = "March 12, 2022"


        if(pref.retrieveOrganizationToJoin().isNotEmpty()) {
            request.OrganizationId = pref.retrieveOrganizationToJoin()
        } else {
            request.OrganizationId = "random"
        }


        request.SchoolCode = UUID.randomUUID().toString()

        createBtn.onClick {
            if (canCreateSchool()) {
                if (request.isValid()) {
                    viewModel.createSchool {
                        if (it) {
                            findNavController().navigate(
                                R.id.action_addSchoolFragment2_to_homeSchoolFragment
                            )
                        }
                    }
                }
            } else snackBar(binding?.root as View, getString(R.string.can_create_org))

        }
    }


    private fun canCreateSchool(): Boolean {
        var can: Boolean = true

        viewModel.activeAdminEmail { email ->
            viewModel.findSchoolByAdminEmail(email) { organization ->
                val local = organization.mapToLocal()
                if (local.SchoolName.isNotEmpty()) {
                    can = false
                }
            }
        }

        return can
    }

    override fun initViews() {
        name = binding?.SchoolProfileFragmentNameLayoutEdit!!
        location = binding?.SchoolProfileFragmentLocationLayoutEdit!!
        address = binding?.SchoolProfileFragmentAddressLayoutEdit!!
        createBtn = binding?.SchoolProfileFragmentCreateBtn!!
    }

    override fun onDestroyComponents() {

    }


}