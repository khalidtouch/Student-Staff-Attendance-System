package com.andela.eduteam14.android_app

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.animation.AnimationUtils
import android.widget.TextView
import com.andela.eduteam14.android_app.core.data.preferences.PreferenceRepository
import com.andela.eduteam14.android_app.core.ui.OrganizationBaseActivity
import com.andela.eduteam14.android_app.core.ui.SchoolBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.auth.AuthActivity
import com.andela.eduteam14.android_app.core.ui.extensions.goto
import com.andela.eduteam14.android_app.databinding.ActivitySplashBinding

@SuppressLint("CustomSplashScreen")
class SplashActivity : AppCompatActivity(), UiAction {

    private var binding: ActivitySplashBinding? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySplashBinding.inflate(layoutInflater)

        setContentView(binding?.root)
        initViews()

        Handler(Looper.getMainLooper()).postDelayed({
            goto(AuthActivity::class.java)
        }, 2000)
    }

    override fun initViews() {

    }

    override fun onDestroyComponents() {
        binding = null
    }

    override fun onDestroy() {
        onDestroyComponents()
        super.onDestroy()

        Log.d(TAG, "onDestroy: ended successfully")
    }

    companion object  {
        const val  TAG = "SplashActivity"
    }
}