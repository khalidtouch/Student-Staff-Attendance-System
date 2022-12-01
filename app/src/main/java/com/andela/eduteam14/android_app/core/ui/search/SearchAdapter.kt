package com.andela.eduteam14.android_app.core.ui.search

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.RecyclerView
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.data.firebase.manager.firestore.FireStoreAdapter
import com.andela.eduteam14.android_app.core.data.firebase.models.RemoteOrganization
import com.andela.eduteam14.android_app.core.data.models.LocalOrganization
import com.andela.eduteam14.android_app.core.ui.extensions.onClick
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query

class SearchAdapter(
    private val context: Context,
    query: Query,
    private val onSelectOrganization: (LocalOrganization) -> Unit,
) :
    FireStoreAdapter<SearchAdapter.ViewHolder>(query) {

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val organizationName: TextView = itemView.findViewById(R.id.OrganizationName)
        private val organizationLocation: TextView = itemView.findViewById(R.id.OrganizationLocation)
        private val container: CardView = itemView.findViewById(R.id.SearchContainer)

        fun bind(snapshot: DocumentSnapshot, onSelectOrganization: (LocalOrganization) -> Unit) {
            val organization: RemoteOrganization? =
                snapshot.toObject(RemoteOrganization::class.java)

            organizationLocation.text = organization?.location
            organizationName.text = organization?.name

            container.onClick { onSelectOrganization(organization?.mapToLocal()!!) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.search_item_layout,
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getSnapshot(position)?.let {
            holder.bind(it, onSelectOrganization = onSelectOrganization)
        }
    }

    fun snapshots(): ArrayList<DocumentSnapshot>? = getSnapshots()
}