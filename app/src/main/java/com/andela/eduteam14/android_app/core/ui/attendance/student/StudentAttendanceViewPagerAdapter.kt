package com.andela.eduteam14.android_app.core.ui.attendance.student

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter

const val KEY_CLASS_POSITION = "key_class_position"

class StudentAttendanceViewPagerAdapter(
    f: FragmentActivity,
    private val classes: Int,
) : FragmentStateAdapter(f) {
    override fun getItemCount(): Int = classes

    override fun createFragment(position: Int): Fragment {
        val fragment = StudentAttendanceFragment()

        fragment.arguments = Bundle().apply {
            putInt(KEY_CLASS_POSITION, position + 1)
        }

        return fragment
    }
}