package com.andela.eduteam14.android_app.core.ui.onboarding.screens

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.navigation.fragment.findNavController
import androidx.viewpager2.widget.ViewPager2
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.data.preferences.PreferenceRepository
import com.andela.eduteam14.android_app.core.ui.extensions.onClick
import com.andela.eduteam14.android_app.databinding.FragmentFirstScreenBinding
import com.andela.eduteam14.android_app.databinding.FragmentThirdScreenBinding


class ThirdScreenFragment : Fragment() {

    private lateinit var finish: TextView
    private var _binding: FragmentThirdScreenBinding? = null

    private val binding get() = _binding

    private lateinit var pref: PreferenceRepository


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentThirdScreenBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        pref = PreferenceRepository.getInstance(requireContext())

        finish = binding?.next3!!

        finish.onClick {
            pref.stain()
            findNavController().navigate(
                R.id.action_onBoardingFragment_to_registerFragment
            )
        }
    }


}