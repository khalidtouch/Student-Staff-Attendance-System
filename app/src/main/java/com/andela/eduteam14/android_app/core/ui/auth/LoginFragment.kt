package com.andela.eduteam14.android_app.core.ui.auth

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.ui.OrganizationBaseActivity
import com.andela.eduteam14.android_app.core.ui.SchoolBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.extensions.ShowToast
import com.andela.eduteam14.android_app.core.ui.extensions.goto
import com.andela.eduteam14.android_app.core.ui.extensions.onChange
import com.andela.eduteam14.android_app.core.ui.extensions.onClick
import com.andela.eduteam14.android_app.core.ui.extensions.onLongClick
import com.andela.eduteam14.android_app.core.ui.extensions.snackBar
import com.andela.eduteam14.android_app.core.ui.viewmodel.AuthViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.AuthViewModelFactory
import com.andela.eduteam14.android_app.databinding.FragmentLoginBinding
import com.andela.eduteam14.android_app.databinding.FragmentRegisterBinding
import com.google.android.material.textfield.TextInputEditText

class LoginFragment : Fragment(), UiAction {

    private lateinit var password: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var googleLogo: ImageView
    private lateinit var githubLogo: ImageView
    private lateinit var fbLogo: ImageView
    private var _binding: FragmentLoginBinding? = null

    private val binding get() = _binding

    private lateinit var login: Button
    private lateinit var registerInstead: TextView

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
        _binding = FragmentLoginBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        registerInstead.onClick { findNavController().navigate(R.id.action_loginFragment_to_registerFragment) }



        onLogin()
    }

    private fun onLogin() {

        email.onChange { viewModel.setEmailForLogin(it) }

        password.onChange { viewModel.setPasswordForLogin(it) }

        login.onClick {
            if(viewModel.loginAdminRequest.isValid()) {
                viewModel.login {
                    if(it){
                        snackBar(binding?.root as View, "You're in")


                    } else  snackBar(binding?.root as View, "Sorry, something went wrong")
                }


            } else snackBar(binding?.root as View, "Sorry,check your credentials")

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

    override fun initViews() {
        login = binding?.LoginFragmentLoginBtn!!
        registerInstead = binding?.NewUser!!
        googleLogo = binding?.LoginFragmentGoogleIcon!!
        githubLogo = binding?.LoginFragmentGithubIcon!!
        fbLogo = binding?.LoginFragmentFacebookIcon!!

        email = binding?.LoginFragmentEmailLayoutEdit!!
        password = binding?.LoginFragmentPasswordLayoutEdit!!
    }

    override fun onDestroyComponents() {

    }
}