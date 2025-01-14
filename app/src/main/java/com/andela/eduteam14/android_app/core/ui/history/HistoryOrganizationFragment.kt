package com.andela.eduteam14.android_app.core.ui.history

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
import com.andela.eduteam14.android_app.core.data.firebase.manager.firestore.FireStoreManagerImpl
import com.andela.eduteam14.android_app.core.ui.OrganizationBaseActivity
import com.andela.eduteam14.android_app.core.ui.SchoolBaseActivity
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.home.OrganizationHomeAdapter
import com.andela.eduteam14.android_app.core.ui.viewmodel.OrganizationViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.OrganizationViewModelFactory
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModel
import com.andela.eduteam14.android_app.core.ui.viewmodel.SchoolViewModelFactory
import com.andela.eduteam14.android_app.databinding.FragmentHistoryOrganizationBinding
import com.andela.eduteam14.android_app.databinding.FragmentHistorySchoolBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class HistoryOrganizationFragment : Fragment(), UiAction {

    private lateinit var noDataLayout: LinearLayout
    private var _binding: FragmentHistoryOrganizationBinding? = null

    private val binding get() = _binding


    private lateinit var query: Query
    private lateinit var fireStore: FirebaseFirestore


    private lateinit var recyclerView: RecyclerView

    private lateinit var historyAdapter: HistoryOrganizationAdapter

    private val viewModel: OrganizationViewModel by viewModels {
        OrganizationViewModelFactory(
            (activity as OrganizationBaseActivity).coreComponent.repository
        )
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentHistoryOrganizationBinding.inflate(inflater, container, false)

        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews()

        fireStore = Firebase.firestore

        val first = fireStore.collection(FireStoreManagerImpl.REF_ATTENDANCE)
            .orderBy("DateModified")

        query = first

        historyAdapter = HistoryOrganizationAdapter(requireContext(), query) { history ->
            Toast.makeText(requireContext(), history.SchoolName, Toast.LENGTH_SHORT).show()
        }

        historyAdapter.stateRestorationPolicy =
            RecyclerView.Adapter.StateRestorationPolicy.PREVENT_WHEN_EMPTY


        recyclerView.apply {
            layoutManager = LinearLayoutManager(requireContext())
            adapter = historyAdapter
        }


    }

    override fun onStart() {
        super.onStart()

        historyAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()

        historyAdapter.stopListening()
    }


    override fun initViews() {
        recyclerView = binding?.HistoryOrganizationRecyclerView!!
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