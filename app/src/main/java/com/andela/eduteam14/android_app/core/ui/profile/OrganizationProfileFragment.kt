package com.andela.eduteam14.android_app.core.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.data.models.CreateOrganizationRequest
import com.andela.eduteam14.android_app.core.ui.OrganizationBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.auth.AuthActivity
import com.andela.eduteam14.android_app.core.ui.extensions.goto
import com.andela.eduteam14.android_app.core.ui.extensions.onChange
import com.andela.eduteam14.android_app.core.ui.extensions.onClick
import com.andela.eduteam14.android_app.core.ui.extensions.snackBar
import com.andela.eduteam14.android_app.core.ui.viewmodel.OrganizationViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.OrganizationViewModelFactory
import com.andela.eduteam14.android_app.databinding.FragmentOrganizationProfileBinding
import com.andela.eduteam14.android_app.databinding.FragmentSettingsOrganizationBinding
import com.google.android.material.textfield.TextInputEditText
import java.util.UUID

class OrganizationProfileFragment : Fragment(), UiAction {

    private lateinit var createBtn: Button
    private lateinit var address: TextInputEditText
    private lateinit var location: TextInputEditText
    private lateinit var name: TextInputEditText
    private var _binding: FragmentOrganizationProfileBinding? = null

    private val binding get() = _binding

    private val viewModel: OrganizationViewModel by viewModels {
        OrganizationViewModelFactory(
            (activity as OrganizationBaseActivity).coreComponent.repository,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentOrganizationProfileBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        handleInput()
    }

    private fun handleInput() {

        val request = CreateOrganizationRequest()

        name.onChange {
            request.Name = it.lowercase()
            viewModel.setOrganizationRequest(request)
        }

        location.onChange {
            request.Location = it
            viewModel.setOrganizationRequest(request)
        }

        address.onChange {
            request.Address = it
            viewModel.setOrganizationRequest(request)
        }

        viewModel.activeAdminEmail {
            request.AdminEmail = it
        }

        request.DateModified = "March 12, 2022"

        request.OrganizationId = UUID.randomUUID().toString()

        createBtn.onClick {
            if (canCreateOrganization()) {
                if (request.isValid()) {
                    viewModel.saveOrganization {
                        if (it) {
                            findNavController().navigate(
                                R.id.action_organizationProfileFragment_to_homeOrganizationFragment
                            )
                        }
                    }
                }
            } else snackBar(binding?.root as View, getString(R.string.can_create_org))

        }

    }

    override fun initViews() {
        name = binding?.OrganizationProfileFragmentNameLayoutEdit!!
        location = binding?.OrganizationProfileFragmentLocationLayoutEdit!!
        address = binding?.OrganizationProfileFragmentAddressLayoutEdit!!
        createBtn = binding?.OrganizationProfileFragmentCreateBtn!!
    }

    private fun canCreateOrganization(): Boolean {
        var can: Boolean = true

        viewModel.activeAdminEmail { email ->
            viewModel.findOrganizationByAdminEmail(email) { organization ->
                val local = organization.mapToLocal()
                if (local.Name.isNotEmpty()) {
                    can = false
                }
            }
        }

        return can
    }

    override fun onDestroyComponents() {
        _binding = null
    }

    override fun onDestroy() {
        onDestroyComponents()
        super.onDestroy()

    }
}