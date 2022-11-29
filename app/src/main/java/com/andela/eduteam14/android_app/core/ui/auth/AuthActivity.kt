package com.andela.eduteam14.android_app.core.ui.auth

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import com.andela.eduteam14.android_app.MainApplication
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.data.preferences.PreferenceRepository
import com.andela.eduteam14.android_app.core.data.preferences.USER_SESSION_STATE
import com.andela.eduteam14.android_app.core.data.preferences.UserSessionState
import com.andela.eduteam14.android_app.core.di.CoreComponent
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.settings.GO_TO_LOGIN
import com.andela.eduteam14.android_app.core.ui.settings.KEY_DESTINATION
import com.andela.eduteam14.android_app.databinding.ActivityAuthBinding


class AuthActivity : AppCompatActivity(), UiAction {

    private lateinit var navController: NavController
    private var _binding: ActivityAuthBinding? = null

    private lateinit var userSessionPref: PreferenceRepository

    private val binding get() = _binding

    lateinit var coreComponent: CoreComponent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        coreComponent = (application as MainApplication).coreComponent

        _binding = ActivityAuthBinding.inflate(layoutInflater)

        userSessionPref = PreferenceRepository.getInstance(this)

        setContentView(binding?.root)

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.RegisterNavHostFragment)
                    as NavHostFragment

        navController = navHostFragment.navController




    }

    override fun onStart() {
        super.onStart()

        if(userSessionPref.state == UserSessionState.NOT_DIRTY.state) {
            jumpTo(R.id.onBoardingFragment)
        }

        if (intent.getStringExtra(KEY_DESTINATION) == GO_TO_LOGIN) {
            jumpTo(R.id.loginFragment)
        }
    }

    private fun jumpTo(id: Int) {
        navController.navigateUp()
        navController.navigate(id)
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