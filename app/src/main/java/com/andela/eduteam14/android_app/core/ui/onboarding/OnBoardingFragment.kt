package com.andela.eduteam14.android_app.core.ui.onboarding.screens

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.viewpager2.widget.ViewPager2
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.R.*
import com.andela.eduteam14.android_app.core.ui.onboarding.ViewPagerAdapter
import com.andela.eduteam14.android_app.databinding.FragmentOnboardingBinding
import com.andela.eduteam14.android_app.databinding.FragmentRegisterBinding

class OnBoardingFragment : Fragment() {

    private var _binding: FragmentOnboardingBinding? = null

    private val binding get() = _binding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentOnboardingBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val adapter = ViewPagerAdapter(this)

        binding?.OnBoardingViewPager?.adapter = adapter
    }

}