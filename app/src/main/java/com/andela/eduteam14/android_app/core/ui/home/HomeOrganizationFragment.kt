package com.andela.eduteam14.android_app.core.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andela.eduteam14.android_app.core.ui.OrganizationBaseActivity
import com.andela.eduteam14.android_app.core.ui.SchoolBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.viewmodel.OrganizationViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.OrganizationViewModelFactory
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModelFactory
import com.andela.eduteam14.android_app.databinding.FragmentHomeOrganizationBinding
import com.andela.eduteam14.android_app.databinding.FragmentHomeSchoolBinding

class HomeOrganizationFragment : Fragment(), UiAction {

    private lateinit var noDataLayout: LinearLayout
    private var _binding: FragmentHomeOrganizationBinding? = null
    private val binding get() = _binding

    private lateinit var recyclerView: RecyclerView
    private lateinit var organizationAdapter: OrganizationHomeAdapter

    private val viewModel: OrganizationViewModel by viewModels {
        OrganizationViewModelFactory(
            (activity as OrganizationBaseActivity).coreComponent.registry,
            (activity as OrganizationBaseActivity).coreComponent.dataSource,
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeOrganizationBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()


        organizationAdapter = OrganizationHomeAdapter(viewModel.attendanceRegistry)


        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = organizationAdapter
        }

        if (viewModel.entries.isEmpty()) hideData() else {
            organizationAdapter.submitList(viewModel.entries)
            showData()
        }


    }

    override fun initViews() {
        recyclerView = binding?.OrganizationHomeRecyclerView!!
        noDataLayout = binding?.LayoutEmptyData!!
    }

    override fun onDestroyComponents() {
        _binding = null
    }

    private fun showData() {
        noDataLayout.visibility = View.INVISIBLE
        recyclerView.visibility = View.VISIBLE
    }

    private fun hideData() {
        noDataLayout.visibility = View.VISIBLE
        recyclerView.visibility = View.INVISIBLE
    }


    override fun onDestroy() {
        onDestroyComponents()
        super.onDestroy()
    }
}