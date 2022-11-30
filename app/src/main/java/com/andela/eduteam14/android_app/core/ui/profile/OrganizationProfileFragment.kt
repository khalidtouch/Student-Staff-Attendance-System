package com.andela.eduteam14.android_app.core.ui.profile

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.ui.OrganizationBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.auth.AuthActivity
import com.andela.eduteam14.android_app.core.ui.extensions.goto
import com.andela.eduteam14.android_app.core.ui.extensions.onClick
import com.andela.eduteam14.android_app.core.ui.viewmodel.OrganizationViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.OrganizationViewModelFactory
import com.andela.eduteam14.android_app.databinding.FragmentOrganizationProfileBinding
import com.andela.eduteam14.android_app.databinding.FragmentSettingsOrganizationBinding

class OrganizationProfileFragment : Fragment(), UiAction {

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

    }

    override fun initViews() {

    }

    override fun onDestroyComponents() {
        _binding = null
    }

    override fun onDestroy() {
        onDestroyComponents()
        super.onDestroy()

    }
}