package com.andela.eduteam14.android_app.core.data.firebase.manager.firestore

import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import com.google.firebase.firestore.DocumentChange
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.EventListener
import com.google.firebase.firestore.FirebaseFirestoreException
import com.google.firebase.firestore.ListenerRegistration
import com.google.firebase.firestore.QuerySnapshot
import com.google.firebase.firestore.Query

abstract class FireStoreAdapter<ViewHolder : RecyclerView.ViewHolder>(
    private val query: Query,
) : RecyclerView.Adapter<ViewHolder>(), EventListener<QuerySnapshot> {

    private var registration: ListenerRegistration? = null
    private val snapshots = ArrayList<DocumentSnapshot>()

    open fun startListening() {
        if (registration == null) {
            registration = query.addSnapshotListener(this)
        }
    }


    open fun stopListening() {
        if (registration != null) {
            registration!!.remove()
            registration = null
        }

        snapshots.clear()
    }

    override fun onEvent(value: QuerySnapshot?, error: FirebaseFirestoreException?) {
        if (error != null) {
            Log.e(TAG, "onEvent: Error", error)
            return
        }

        for (change in value!!.documentChanges) {
            when (change.type) {
                DocumentChange.Type.ADDED -> onDocumentAdded(change)

                DocumentChange.Type.MODIFIED -> onDocumentModified(change)

                DocumentChange.Type.REMOVED -> onDocumentRemoved(change)
            }
        }
    }


    protected open fun onDocumentAdded(change: DocumentChange) {
        snapshots.add(change.newIndex, change.document)
        notifyItemInserted(change.newIndex)
    }

    protected open fun onDocumentModified(change: DocumentChange) {
        if (change.oldIndex == change.newIndex) {
            snapshots[change.oldIndex] = change.document
            notifyItemChanged(change.oldIndex)
        }
    }

    protected open fun onDocumentRemoved(change: DocumentChange) {
        snapshots.removeAt(change.oldIndex)
        notifyItemRemoved(change.oldIndex)
    }


    override fun getItemCount(): Int {
        return snapshots.size
    }

    protected open fun getSnapshot(index: Int): DocumentSnapshot? {
        return snapshots[index]
    }

    protected open fun getSnapshots(): ArrayList<DocumentSnapshot>? {
        return snapshots
    }

    companion object {
        const val TAG = "FirebaseAdapter"
    }

}