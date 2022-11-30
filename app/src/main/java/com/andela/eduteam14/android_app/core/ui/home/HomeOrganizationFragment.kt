package com.andela.eduteam14.android_app.core.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andela.eduteam14.android_app.core.data.firebase.manager.firestore.FireStoreManagerImpl
import com.andela.eduteam14.android_app.core.ui.OrganizationBaseActivity
import com.andela.eduteam14.android_app.core.ui.SchoolBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.viewmodel.OrganizationViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.OrganizationViewModelFactory
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModelFactory
import com.andela.eduteam14.android_app.databinding.FragmentHomeOrganizationBinding
import com.andela.eduteam14.android_app.databinding.FragmentHomeSchoolBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeOrganizationFragment : Fragment(), UiAction {

    private lateinit var noDataLayout: LinearLayout
    private var _binding: FragmentHomeOrganizationBinding? = null
    private val binding get() = _binding

    private lateinit var recyclerView: RecyclerView
    private lateinit var organizationAdapter: OrganizationHomeAdapter

    private lateinit var query: Query
    private lateinit var fireStore: FirebaseFirestore


    private val viewModel: OrganizationViewModel by viewModels {
        OrganizationViewModelFactory(
            (activity as OrganizationBaseActivity).coreComponent.repository
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

        fireStore = Firebase.firestore

        val first = fireStore.collection(FireStoreManagerImpl.REF_ATTENDANCE)
            .orderBy("DateModified")


        query = first

        organizationAdapter = OrganizationHomeAdapter(requireContext(), query) { attendance ->
            Toast.makeText(requireContext(), attendance.SchoolName, Toast.LENGTH_SHORT).show()
        }

        organizationAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY


        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = organizationAdapter
        }



    }

    override fun onStart() {
        super.onStart()

        organizationAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()

        organizationAdapter.stopListening()
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