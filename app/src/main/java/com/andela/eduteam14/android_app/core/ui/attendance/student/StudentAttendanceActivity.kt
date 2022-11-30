package com.andela.eduteam14.android_app.core.ui.attendance.student

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.fragment.app.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.andela.eduteam14.android_app.MainApplication
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.di.CoreComponent
import com.andela.eduteam14.android_app.core.ui.SchoolBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModelFactory
import com.andela.eduteam14.android_app.databinding.ActivityBaseBinding
import com.andela.eduteam14.android_app.databinding.ActivityStudentAttendanceBinding

class StudentAttendanceActivity : AppCompatActivity(), UiAction {

    lateinit var coreComponent: CoreComponent

    lateinit var pager: ViewPager2

    private var _binding: ActivityStudentAttendanceBinding? = null

    private val binding get() = _binding

    private lateinit var baseAdapter: StudentAttendanceViewPagerAdapter


    private val viewModel: SchoolViewModel by viewModels {
        SchoolViewModelFactory(
            coreComponent.repository
        )
    }

    var classes: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        coreComponent = (application as MainApplication).coreComponent

        _binding = ActivityStudentAttendanceBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        baseAdapter = StudentAttendanceViewPagerAdapter(this, classes)

        initViews()

        pager.adapter = baseAdapter
    }


    override fun initViews() {
        pager = binding?.StudentAttendanceViewPager!!
    }

    override fun onDestroyComponents() {
        _binding = null
    }

    override fun onDestroy() {
        onDestroyComponents()
        super.onDestroy()
    }
}