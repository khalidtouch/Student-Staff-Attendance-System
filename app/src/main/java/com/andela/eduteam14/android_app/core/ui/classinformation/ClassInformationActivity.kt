package com.andela.eduteam14.android_app.core.ui.classinformation

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.viewpager2.widget.ViewPager2
import com.andela.eduteam14.android_app.MainApplication
import com.andela.eduteam14.android_app.core.di.CoreComponent
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModelFactory
import com.andela.eduteam14.android_app.databinding.ActivityClassInformationBinding

class ClassInformationActivity : AppCompatActivity(), UiAction, PagerControl {

    lateinit var coreComponent: CoreComponent

    lateinit var pager: ViewPager2

    private var _binding: ActivityClassInformationBinding? = null

    var classes: Int = 15

    private val binding get() = _binding

    private val viewModel: SchoolViewModel by viewModels {
        SchoolViewModelFactory(
            coreComponent.repository
        )
    }

    private lateinit var baseAdapter: ClassInformationViewPagerAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        coreComponent = (application as MainApplication).coreComponent

        _binding = ActivityClassInformationBinding.inflate(layoutInflater)

        setContentView(binding?.root)

        initViews()

        baseAdapter = ClassInformationViewPagerAdapter(this, classes)

        pager.adapter = baseAdapter


    }

    override fun initViews() {
        pager = binding?.ClassInformationViewPager!!
    }

    override fun onDestroyComponents() {
        _binding = null
    }

    override fun onPageChange(page: Int) {
        pager.currentItem = page
    }

    override fun currentPage(): Int {
        return pager.currentItem
    }

    override fun totalClasses(): Int {
        return classes
    }

}

interface PagerControl {
    fun onPageChange(page: Int)

    fun currentPage(): Int

    fun totalClasses(): Int
}