package com.andela.eduteam14.android_app.core.ui.settings

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.ui.SchoolBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.auth.AuthActivity
import com.andela.eduteam14.android_app.core.ui.extensions.goto
import com.andela.eduteam14.android_app.core.ui.extensions.onClick
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModelFactory
import com.andela.eduteam14.android_app.databinding.FragmentSettingsSchoolBinding

const val KEY_DESTINATION = "DESTINATION_KEY"
const val GO_TO_LOGIN = "LOGIN_VALUE"

class SettingsSchoolFragment : Fragment(), UiAction {
    private var _binding: FragmentSettingsSchoolBinding? = null

    private val binding get() = _binding

    private lateinit var numberOfStaff: TextView
    private lateinit var logout: Button
    private lateinit var schoolInformation: LinearLayout
    private lateinit var staffInformation: LinearLayout
    private lateinit var studentAndClass: LinearLayout
    private lateinit var adminProfile: LinearLayout


    private val viewModel: SchoolViewModel by viewModels {
        SchoolViewModelFactory(
            (activity as SchoolBaseActivity).coreComponent.repository,
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentSettingsSchoolBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        (activity as SchoolBaseActivity).hideFab()


        handleEvents()
    }

    private fun handleEvents() {
        schoolInformation.onClick {
            findNavController().navigate(
                R.id.action_settingsSchoolFragment_to_addSchoolFragment2
            )
        }

        staffInformation.onClick {

        }

        studentAndClass.onClick {
            findNavController().navigate(
                R.id.action_settingsSchoolFragment_to_numberOfClassFragment
            )
        }

        adminProfile.onClick {
            findNavController().navigate(
                R.id.action_settingsSchoolFragment_to_adminProfileFragment
            )
        }

        logout.onClick {
            (activity as SchoolBaseActivity).goto(
                AuthActivity::class.java,
                KEY_DESTINATION, GO_TO_LOGIN
            )
        }
    }


    override fun initViews() {
        numberOfStaff = binding?.NumberOfStaff!!
        adminProfile = binding?.AdminProfile!!
        schoolInformation = binding?.SchoolInformation!!
        staffInformation = binding?.StaffInformation!!
        studentAndClass = binding?.StudentAndClass!!
        logout = binding?.SchoolSettingsFragmentLogoutBtn!!
    }

    override fun onDestroyComponents() {
        _binding = null
    }

    override fun onDestroy() {
        onDestroyComponents()
        super.onDestroy()
    }

}