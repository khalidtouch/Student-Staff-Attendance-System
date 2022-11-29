package com.andela.eduteam14.android_app.core.ui.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.viewpager2.widget.ViewPager2
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.databinding.FragmentFirstScreenBinding
import com.andela.eduteam14.android_app.databinding.FragmentSecondScreenBinding


class SecondScreenFragment : Fragment() {
    private var _binding: FragmentSecondScreenBinding? = null

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentSecondScreenBinding.inflate(inflater, container, false)

        return binding?.root
    }

}