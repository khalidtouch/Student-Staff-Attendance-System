package com.andela.eduteam14.android_app.core.ui.classinformation

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.andela.eduteam14.android_app.core.ui.SchoolBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.attendance.student.KEY_CLASS_POSITION
import com.andela.eduteam14.android_app.core.ui.attendance.student.StudentAttendanceActivity
import com.andela.eduteam14.android_app.core.ui.extensions.onClick
import com.andela.eduteam14.android_app.core.ui.extensions.snackBar
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModelFactory
import com.andela.eduteam14.android_app.databinding.FragmentClassInformationBinding
import com.andela.eduteam14.android_app.databinding.FragmentStudentAttendanceBinding

class ClassInformationFragment : Fragment(), UiAction {

    private lateinit var more: Button
    private lateinit var commit: Button
    private lateinit var number: EditText
    private lateinit var className: EditText
    private var _binding: FragmentClassInformationBinding? = null
    private val binding get() = _binding

    private var currentPage: Int = 0

    private var classes: Int = 0

    private var pagerControl: PagerControl? = null

    private val viewModel: SchoolViewModel by viewModels {
        SchoolViewModelFactory(
            (activity as ClassInformationActivity).coreComponent.repository
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

        pagerControl = ClassInformationActivity()

        arguments?.takeIf { it.containsKey(KEY_CLASS_POSITION) }?.apply {
            bind(this)
        }

        currentPage = pagerControl?.currentPage()!!

        classes = pagerControl?.totalClasses()!!

        if(currentPage < classes) hideCommit() else showCommit()


        handleClicks()

    }

    private fun handleClicks() {
        more.onClick {
            currentPage++

            pagerControl?.onPageChange(currentPage)
        }

        commit.onClick {
            snackBar(binding?.root as View, "Done")
        }
    }

    private fun showCommit() {
        commit.visibility = View.VISIBLE
        more.visibility = View.INVISIBLE
    }

    private fun hideCommit() {
        commit.visibility = View.INVISIBLE
        more.visibility = View.VISIBLE
    }

    override fun initViews() {
        className = binding?.ClassName!!
        number = binding?.NumberOfStudents!!
        commit = binding?.btnCommit!!
        more = binding?.btnAddMore!!
    }

    override fun onDestroyComponents() {
        _binding = null
    }

    private fun bind(bundle: Bundle) {

    }

    override fun onDestroy() {
        onDestroyComponents()
        super.onDestroy()

    }


}