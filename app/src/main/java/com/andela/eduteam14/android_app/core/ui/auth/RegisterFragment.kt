package com.andela.eduteam14.android_app.core.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.domain.usecase.ChooseOrganizationDialogUseCase
import com.andela.eduteam14.android_app.core.ui.OrganizationBaseActivity
import com.andela.eduteam14.android_app.core.ui.SchoolBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.extensions.goto
import com.andela.eduteam14.android_app.core.ui.extensions.onChange
import com.andela.eduteam14.android_app.core.ui.extensions.onClick
import com.andela.eduteam14.android_app.core.ui.extensions.snackBar
import com.andela.eduteam14.android_app.core.ui.viewmodel.AuthViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.AuthViewModelFactory
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModelFactory
import com.andela.eduteam14.android_app.databinding.FragmentRegisterBinding
import com.google.android.material.textfield.TextInputEditText

class RegisterFragment : Fragment(), UiAction {

    private lateinit var confirmPassword: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var googleLogo: ImageView
    private lateinit var fbLogo: ImageView
    private lateinit var githubLogo: ImageView
    private lateinit var createAccountBtn: Button


    private var _binding: FragmentRegisterBinding? = null
    private val binding get() = _binding

    private lateinit var login: TextView

    private lateinit var dialogUseCase: ChooseOrganizationDialogUseCase

    private val viewModel: AuthViewModel by viewModels {
        AuthViewModelFactory(
            (activity as AuthActivity).coreComponent.repository,
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentRegisterBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        dialogUseCase = ChooseOrganizationDialogUseCase(requireContext(), this)



        login.onClick {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        onEnterText()

        onRegister()
    }

    private fun onEnterText() {
        email.onChange { viewModel.setEmail(it) }
        password.onChange { viewModel.setPassword(it) }
        confirmPassword.onChange { viewModel.setConfirmPassword(it) }
    }

    private fun onRegister() {

        createAccountBtn.onClick {
            if (viewModel.createAdminRequest.isValid()) {
                viewModel.createAccount {
                    if (it) {
                        snackBar(binding?.root as View, "Successfully created admin")
                        loadDialog()
                    } else {
                        snackBar(binding?.root as View, "Could not create the admin")
                    }
                }
            } else  snackBar(binding?.root as View, "invalid input")

            loadDialog()
        }


        githubLogo.onClick {
            snackBar(binding?.root as View, getString(R.string.not_yet_implemented))
        }

        fbLogo.onClick {
            snackBar(binding?.root as View, getString(R.string.not_yet_implemented))
        }

        googleLogo.onClick {
            snackBar(binding?.root as View, getString(R.string.not_yet_implemented))
        }
    }


    private fun loadDialog() {
        dialogUseCase(
            onChooseSchool = {
                (activity as AuthActivity).goto(SchoolBaseActivity::class.java)
            },
            onChooseOrganization = {
                (activity as AuthActivity).goto(OrganizationBaseActivity::class.java)
            }
        )
    }


    override fun initViews() {
        createAccountBtn = binding?.RegisterFragmentRegisterBtn!!
        login = binding?.OldUser!!
        githubLogo = binding?.RegisterFragmentGithubIcon!!
        fbLogo = binding?.RegisterFragmentFacebookIcon!!
        googleLogo = binding?.RegisterFragmentGoogleIcon!!
        email = binding?.RegisterFragmentEmailLayoutEdit!!
        password = binding?.RegisterFragmentPasswordLayoutEdit!!
        confirmPassword = binding?.RegisterFragmentConfirmPasswordLayoutEdit!!
    }

    override fun onDestroyComponents() {
        _binding = null
    }

    override fun onDestroy() {
        onDestroyComponents()
        super.onDestroy()
    }
}