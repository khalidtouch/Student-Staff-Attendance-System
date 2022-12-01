package com.andela.eduteam14.android_app.core.ui.classinformation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.data.models.CreateClassRequest
import com.andela.eduteam14.android_app.core.data.preferences.PreferenceRepository
import com.andela.eduteam14.android_app.core.ui.SchoolBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.extensions.onChange
import com.andela.eduteam14.android_app.core.ui.extensions.onClick
import com.andela.eduteam14.android_app.core.ui.extensions.snackBar
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModelFactory
import com.andela.eduteam14.android_app.databinding.FragmentClassInformationBinding

class ClassInformationFragment : Fragment(), UiAction {

    private lateinit var femaleStudentsNumber: EditText
    private lateinit var heading: TextView
    private lateinit var counter: TextView
    private lateinit var more: Button
    private lateinit var commit: Button
    private lateinit var maleStudentsNumber: EditText
    private lateinit var className: EditText
    private var _binding: FragmentClassInformationBinding? = null
    private val binding get() = _binding

    private var currentPage: Int = 1


    private var classes: Int = 0

    private lateinit var pref: PreferenceRepository


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
        _binding = FragmentClassInformationBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        pref = PreferenceRepository.getInstance(requireContext())

        classes = pref.retrieveNumberOfClasses()

        viewModel.maxClasses = classes

        handleInput()

    }


    private fun handleInput() {

        className.onChange { viewModel.setClassName(it.trim()) }

        maleStudentsNumber.onChange { viewModel.setNumberOfMaleStudents(it.trim()) }
        femaleStudentsNumber.onChange { viewModel.setNumberOfFemaleStudents(it.trim()) }

        more.onClick {
            onNext()
        }

        commit.onClick {

            val names = arrayListOf<String>()

            viewModel.classRequestList.map {
                names.add(it.className)
            }

            pref.saveClassNames(names)

            viewModel.onCommitClassInformation(pref)
            Log.i(TAG, "onNext: Number of classes ${viewModel.classRequestList.size}")
            findNavController().navigate(
                R.id.action_classInformationFragment_to_homeSchoolFragment
            )
        }

    }


    private fun onNext() {

        if (viewModel.createClassRequest.isValid()) {
            if (viewModel.currentClass < viewModel.maxClasses) {
                viewModel.addClass {
                    setFields(it)
                    hideCommit()
                }

                Log.i(TAG, "onNext: Number of classes ${viewModel.classRequestList.size}")

                return
            }

            if (viewModel.currentClass >= viewModel.maxClasses) {
                counter.text = getString(R.string.completed)
                showCommit()
                hideAll()
            }

            Log.i(TAG, "onNext: Number of classes ${viewModel.classRequestList.size}")
        } else snackBar(binding?.btnAddMore as View, "Please enter a valid info")

    }

    private fun showCommit() {
        commit.visibility = View.VISIBLE
        more.visibility = View.INVISIBLE
    }

    private fun hideCommit() {
        commit.visibility = View.INVISIBLE
        more.visibility = View.VISIBLE
    }

    private fun hideAll() {
        className.visibility = View.GONE
        maleStudentsNumber.visibility = View.GONE
        femaleStudentsNumber.visibility = View.GONE
        heading.visibility = View.GONE
    }

    private fun showAll() {
        className.visibility = View.VISIBLE
        maleStudentsNumber.visibility = View.VISIBLE
        femaleStudentsNumber.visibility = View.VISIBLE
        heading.visibility = View.VISIBLE
    }

    private fun setFields(createClassRequest: CreateClassRequest) {
        className.setText(createClassRequest.className)
        maleStudentsNumber.setText(createClassRequest.numberOfMales)
        femaleStudentsNumber.setText(createClassRequest.numberOfFemales)
        counter.text = viewModel.currentClass.toString()
    }

    override fun initViews() {
        className = binding?.ClassName!!
        maleStudentsNumber = binding?.NumberOfMaleStudents!!
        femaleStudentsNumber = binding?.NumberOfFemaleStudents!!
        commit = binding?.btnCommit!!
        more = binding?.btnAddMore!!
        counter = binding?.ClassCounter!!
        heading = binding?.Heading!!

        className.setHint(getString(R.string.classLevel, viewModel.currentClass.toString()))
        counter.text = getString(R.string.class_counter)

        if (viewModel.currentClass < viewModel.maxClasses) hideCommit()

        if (viewModel.currentClass == viewModel.maxClasses - 1) showCommit()


        showAll()

    }

    override fun onDestroyComponents() {
        _binding = null
    }


    override fun onDestroy() {
        onDestroyComponents()
        super.onDestroy()

    }

    companion object {
        val TAG = "ClassInformationFragment"
    }


}