package com.andela.eduteam14.android_app.core.ui.onboarding

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewParent
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.Lifecycle
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.ui.onboarding.screens.FirstScreenFragment
import com.andela.eduteam14.android_app.core.ui.onboarding.screens.SecondScreenFragment
import com.andela.eduteam14.android_app.core.ui.onboarding.screens.ThirdScreenFragment
import java.text.FieldPosition

class ViewPagerAdapter(f: Fragment) :
    FragmentStateAdapter(f) {

    private val fragmentList = listOf(
        FirstScreenFragment(),
        SecondScreenFragment(),
        ThirdScreenFragment(),
    )

    override fun createFragment(position: Int): Fragment {
        return fragmentList[position]

    }

    override fun getItemCount(): Int = 3


}