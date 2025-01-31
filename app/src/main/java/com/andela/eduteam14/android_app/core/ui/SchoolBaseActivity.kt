package com.andela.eduteam14.android_app.core.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.widget.Toast
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.AppBarConfiguration
import androidx.navigation.ui.setupWithNavController
import com.andela.eduteam14.android_app.MainApplication
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.di.CoreComponent
import com.andela.eduteam14.android_app.core.ui.auth.GO_TO_ADD_SCHOOL
import com.andela.eduteam14.android_app.core.ui.auth.KEY_GO_TO_ADD_SCHOOL
import com.andela.eduteam14.android_app.core.ui.extensions.goto
import com.andela.eduteam14.android_app.core.ui.extensions.onItemClick
import com.andela.eduteam14.android_app.core.ui.search.SearchActivity
import com.andela.eduteam14.android_app.databinding.ActivityBaseBinding
import com.google.android.material.appbar.MaterialToolbar
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.floatingactionbutton.ExtendedFloatingActionButton

class SchoolBaseActivity : AppCompatActivity(), UiAction {

    private lateinit var navController: NavController
    private lateinit var navView: BottomNavigationView
    private var _binding: ActivityBaseBinding? = null

    private val binding get() = _binding
    lateinit var coreComponent: CoreComponent

    lateinit var toolbar: MaterialToolbar
    lateinit var recordFab: ExtendedFloatingActionButton


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        coreComponent = (application as MainApplication).coreComponent

        _binding = ActivityBaseBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        Log.d(TAG, "onCreate: SchoolBaseActivity called onCreate")

        initViews()

        val navHostFragment =
            supportFragmentManager.findFragmentById(R.id.BaseNavHostFragment)
                    as NavHostFragment

        navController = navHostFragment.navController
        val appBarConfig = AppBarConfiguration(
            topLevelDestinationIds = setOf(),
            fallbackOnNavigateUpListener = ::onSupportNavigateUp
        )

        val toolbar = binding?.BaseToolbar!!
        toolbar.setupWithNavController(navController, appBarConfig)
        navView.setupWithNavController(navController)


        handleMenuClicks(onStartSearch = { onStartSearch() })

        hideFab()

    }

    override fun onResume() {
        super.onResume()

    }

    override fun onStart() {
        super.onStart()
        Log.i(TAG, "onStart: Called")

        if(intent.getStringExtra(KEY_GO_TO_ADD_SCHOOL) == GO_TO_ADD_SCHOOL) {
            jumpTo(R.id.joinOrganizationFragment)
        }
    }

    fun hideFab() {
        recordFab.hide()
    }

    fun showFab() {
        recordFab.show()
    }

    private fun onStartSearch() {
        Toast.makeText(this, "Search will commence", Toast.LENGTH_SHORT).show()
    }

    private fun handleMenuClicks(onStartSearch: () -> Unit) {
        toolbar.onItemClick {
            when (it.itemId) {
                R.id.search_school -> {
                    this.goto(SearchActivity::class.java)
                }
            }
        }

    }

    override fun initViews() {
        navView = binding?.SchoolBottomNav!!
        toolbar = binding?.BaseToolbar!!
        recordFab = binding?.BaseExtendedFab!!
    }

    override fun onDestroyComponents() {
        _binding = null
    }

     fun jumpTo(id: Int) {
        navController.navigateUp()
        navController.navigate(id)
    }

    override fun onDestroy() {
        onDestroyComponents()
        super.onDestroy()
    }

    companion object {
        val TAG = "SchoolBaseActivity"
    }
}