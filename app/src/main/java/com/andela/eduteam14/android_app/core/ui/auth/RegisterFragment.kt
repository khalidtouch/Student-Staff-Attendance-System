package com.andela.eduteam14.android_app.core.ui.auth

import android.content.Intent
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
import com.andela.eduteam14.android_app.core.data.models.LoginAdminRequest
import com.andela.eduteam14.android_app.core.data.models.UserAccount
import com.andela.eduteam14.android_app.core.data.preferences.PreferenceRepository
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
import com.andela.eduteam14.android_app.databinding.FragmentRegisterBinding
import com.google.android.material.textfield.TextInputEditText

const val KEY_GO_TO_ADD_SCHOOL = "key_go_to_add_school"
const val GO_TO_ADD_SCHOOL = "add_school"
const val GO_TO_ADD_ORGANIZATION = "add_organization"
const val KEY_GO_TO_ADD_ORGANIZATION = "key_go_to_add_organization"

class RegisterFragment : Fragment(), UiAction {

    private lateinit var confirmPassword: TextInputEditText
    private lateinit var password: TextInputEditText
    private lateinit var email: TextInputEditText
    private lateinit var googleLogo: ImageView
    private lateinit var fbLogo: ImageView
    private lateinit var phoneLogo: ImageView
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

    private lateinit var pref: PreferenceRepository


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

        pref = PreferenceRepository.getInstance(requireContext())

        dialogUseCase = ChooseOrganizationDialogUseCase(requireContext(), this)



        login.onClick {
            findNavController().navigate(R.id.action_registerFragment_to_loginFragment)
        }

        onEnterText()

        onRegister()
    }

    private fun onEnterText() {
        val loginAdminRequest = LoginAdminRequest()

        email.onChange { loginAdminRequest.AdminEmail = it }
        password.onChange { loginAdminRequest.Password = it }
        confirmPassword.onChange { }

        viewModel.setLoginAdminRequest(loginAdminRequest)
    }

    private fun onRegister() {

        createAccountBtn.onClick {
            if (viewModel.loginAdminRequest.isValid()) {
                viewModel.createAccount {
                    if (it) {
                        snackBar(binding?.root as View, "Successfully created admin")
                        loadDialog()
                    } else {
                        snackBar(binding?.root as View, "Could not create the admin")
                    }
                }
            } else snackBar(binding?.root as View, "invalid input")

            loadDialog()
        }


        phoneLogo.onClick {
            activity.let {
                //start MobileNumberVerificationActivity
                val intent = Intent(it, MobileNumberVerificationActivity::class.java)
                startActivity(intent)
            }
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
                pref.saveUserAccount(UserAccount.SCHOOL.account)

                (activity as AuthActivity).goto(
                    SchoolBaseActivity::class.java,
                    KEY_GO_TO_ADD_SCHOOL,
                    GO_TO_ADD_SCHOOL
                )
            },
            onChooseOrganization = {
                pref.saveUserAccount(UserAccount.ORGANIZATION.account)

                (activity as AuthActivity).goto(
                    OrganizationBaseActivity::class.java,
                    KEY_GO_TO_ADD_ORGANIZATION,
                    GO_TO_ADD_ORGANIZATION
                )
            }
        )
    }


    override fun initViews() {
        createAccountBtn = binding?.RegisterFragmentRegisterBtn!!
        login = binding?.OldUser!!
        phoneLogo = binding?.RegisterFragmentPhoneIcon!!
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