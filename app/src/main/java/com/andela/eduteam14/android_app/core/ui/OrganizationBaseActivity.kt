package com.andela.eduteam14.android_app.core.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.andela.eduteam14.android_app.MainApplication
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.di.CoreComponent
import com.andela.eduteam14.android_app.core.ui.auth.GO_TO_ADD_ORGANIZATION
import com.andela.eduteam14.android_app.core.ui.auth.GO_TO_ADD_SCHOOL
import com.andela.eduteam14.android_app.core.ui.auth.KEY_GO_TO_ADD_ORGANIZATION
import com.andela.eduteam14.android_app.core.ui.auth.KEY_GO_TO_ADD_SCHOOL
import com.andela.eduteam14.android_app.databinding.ActivityBaseBinding
import com.andela.eduteam14.android_app.databinding.ActivityOrganizationBaseBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView

class OrganizationBaseActivity : AppCompatActivity(), UiAction {

    private var _binding: ActivityOrganizationBaseBinding? = null

    private val binding get() = _binding

    private lateinit var navController: NavController
    private lateinit var navView: BottomNavigationView

    lateinit var coreComponent: CoreComponent

    lateinit var toolbar: MaterialToolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        coreComponent = (application as MainApplication).coreComponent

        _binding = ActivityOrganizationBaseBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        initViews()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.OrganizationBaseNavHostFragment)
                    as NavHostFragment

        navController = navHostFragment.navController

        val appBarConfig = AppBarConfiguration(
            topLevelDestinationIds = setOf(),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )

        val toolbar = binding?.BaseToolbar!!
        toolbar.setupWithNavController(navController, appBarConfig)

        navView.setupWithNavController(navController)

    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: Called")

        if(intent.getStringExtra(KEY_GO_TO_ADD_ORGANIZATION) == GO_TO_ADD_ORGANIZATION) {
            jumpTo(R.id.organizationProfileFragment)
        }
    }

    override fun initViews() {
       navView = binding?.OrganizationBottomNav!!
    }

    override fun onDestroyComponents() {

    }

     fun jumpTo(id: Int) {
        navController.navigateUp()
        navController.navigate(id)
    }

    companion object {
        val TAG = "OrganizationBaseActivity"
    }
}