package com.andela.eduteam14.android_app.core.ui.home

import android.content.Context
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
import com.andela.eduteam14.android_app.MainApplication
import com.andela.eduteam14.android_app.core.data.firebase.manager.firestore.FireStoreManagerImpl
import com.andela.eduteam14.android_app.core.data.mock.AttendanceRegistry
import com.andela.eduteam14.android_app.core.domain.usecase.DateTodayUseCase
import com.andela.eduteam14.android_app.core.ui.SchoolBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModelFactory
import com.andela.eduteam14.android_app.databinding.FragmentHomeSchoolBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HomeSchoolFragment : Fragment(), UiAction {

    private var _binding: FragmentHomeSchoolBinding? = null

    private val binding get() = _binding

    private lateinit var noDataLayout: LinearLayout

    private lateinit var recyclerView: RecyclerView
    private lateinit var homeAdapter: SchoolHomeAdapter
    private lateinit var registry: AttendanceRegistry

    private lateinit var query: Query
    private lateinit var fireStore: FirebaseFirestore

    private val viewModel: SchoolViewModel by viewModels {
        SchoolViewModelFactory(
            (activity as SchoolBaseActivity).coreComponent.repository
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

        registry = (activity as SchoolBaseActivity).coreComponent.registry
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHomeSchoolBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()


        (activity as SchoolBaseActivity).hideFab()

        fireStore = Firebase.firestore

        val first = fireStore.collection(FireStoreManagerImpl.REF_ATTENDANCE)
            .orderBy("DateModified")


        query = first


        homeAdapter = SchoolHomeAdapter(requireContext(), query) { attendance ->
            Toast.makeText(requireContext(), attendance.SchoolName, Toast.LENGTH_SHORT).show()
        }

        homeAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY


        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = homeAdapter
        }



    }

    override fun onStart() {
        super.onStart()

        homeAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()

        homeAdapter.stopListening()
    }

    override fun initViews() {
        recyclerView = binding?.SchoolHomeRecyclerView!!
        noDataLayout = binding?.LayoutEmptyData!!
    }

    override fun onDestroyComponents() {
        _binding = null
    }

    override fun onDestroy() {
        onDestroyComponents()
        super.onDestroy()
    }

    private fun showData() {
        noDataLayout.visibility = View.INVISIBLE
        recyclerView.visibility = View.VISIBLE
    }

    private fun hideData() {
        noDataLayout.visibility = View.VISIBLE
        recyclerView.visibility = View.INVISIBLE
    }
}