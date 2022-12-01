package com.andela.eduteam14.android_app.core.ui.classinformation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.data.preferences.PreferenceRepository
import com.andela.eduteam14.android_app.core.ui.SchoolBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.extensions.onChange
import com.andela.eduteam14.android_app.core.ui.extensions.onClick
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModelFactory
import com.andela.eduteam14.android_app.databinding.FragmentClassInformationBinding
import com.andela.eduteam14.android_app.databinding.FragmentNumberOfClassesBinding
import com.google.android.material.textfield.TextInputEditText

class NumberOfClassFragment : Fragment(), UiAction {

    private lateinit var save: Button
    private lateinit var number: TextInputEditText
    private lateinit var pref: PreferenceRepository

    private var _binding: FragmentNumberOfClassesBinding? = null
    private val binding get() = _binding

    private val viewModel: SchoolViewModel by viewModels {
        SchoolViewModelFactory(
            (activity as SchoolBaseActivity).coreComponent.repository
        )
    }


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentNumberOfClassesBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        pref = PreferenceRepository.getInstance(requireContext())

        handleInput()


    }

    private fun handleInput() {
        var numberOfClasses = "0"

        number.onChange { numberOfClasses = it }

        save.onClick {
            pref.saveNumberOfClasses(numberOfClasses.toInt())

            findNavController().navigate(
                R.id.action_numberOfClassFragment_to_classInformationFragment
            )
        }
    }

    override fun initViews() {
        number = binding?.NumberClassesFragmentLayoutEdit!!
        save = binding?.NumberClassesFragmentSaveBtn!!
    }

    override fun onDestroyComponents() {

    }
}