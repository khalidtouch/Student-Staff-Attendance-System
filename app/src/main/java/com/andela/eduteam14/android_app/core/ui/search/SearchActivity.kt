package com.andela.eduteam14.android_app.core.ui.search

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.widget.SearchView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.data.firebase.manager.firestore.FireStoreManagerImpl
import com.andela.eduteam14.android_app.core.ui.UiAction
import com.andela.eduteam14.android_app.core.ui.extensions.onClick
import com.andela.eduteam14.android_app.core.ui.extensions.onSearch
import com.andela.eduteam14.android_app.core.ui.history.HistorySchoolAdapter
import com.andela.eduteam14.android_app.databinding.ActivitySearchBinding
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase

class SearchActivity : AppCompatActivity(), UiAction {

    private lateinit var search: android.widget.SearchView
    private lateinit var recyclerView: RecyclerView
    private var _binding: ActivitySearchBinding? = null

    private val binding get() = _binding

    private lateinit var searchAdapter: SearchAdapter

    private lateinit var query: Query

    private lateinit var fireStore: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        _binding = ActivitySearchBinding.inflate(layoutInflater)
        Log.d("TAGbbbb", "onCreate: ")

        setContentView(binding?.root)
        initViews()

        fireStore = Firebase.firestore


        val first = fireStore.collection(FireStoreManagerImpl.REF_ORGANIZATIONS)
            .orderBy("Location")

        query = first

        searchAdapter = SearchAdapter(this, query) { organization ->
            Toast.makeText(this, organization.OrganizationId, Toast.LENGTH_SHORT).show()
        }


        recyclerView.apply {
            layoutManager = LinearLayoutManager(this@SearchActivity)
            adapter = searchAdapter
        }

        search.clearFocus()


        search.onClick {
            Log.d("TAGbbbb", "onCreate: search view clicked  ")
            search.onActionViewExpanded()
        }

        search.onSearch {
            Log.d("TAGbbbb", "on search ")
//            query = fireStore.collection(FireStoreManagerImpl.REF_ORGANIZATIONS)
//                .whereEqualTo("Name", it.lowercase())
        }

    }

    override fun onStart() {
        super.onStart()

        searchAdapter.startListening()
    }

    override fun onStop() {
        super.onStop()

        searchAdapter.stopListening()
    }

    override fun initViews() {
        search = binding?.OrganizationSearchView!!
        recyclerView = binding?.HistorySchoolRecyclerView!!
    }

    override fun onDestroyComponents() {

    }
}