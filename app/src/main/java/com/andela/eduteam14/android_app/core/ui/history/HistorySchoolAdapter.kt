package com.andela.eduteam14.android_app.core.ui.history

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.data.firebase.manager.firestore.FireStoreAdapter
import com.andela.eduteam14.android_app.core.data.firebase.models.RemoteDailyAttendance
import com.andela.eduteam14.android_app.core.data.models.LocalDailyAttendance
import com.andela.eduteam14.android_app.core.ui.extensions.onClick
import com.andela.eduteam14.android_app.databinding.AttendanceHistoryItemBinding
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query

class HistorySchoolAdapter(
    private val context: Context,
    query: Query,
    private val onSelectAttendance: (LocalDailyAttendance) -> Unit,
) :
    FireStoreAdapter<HistorySchoolAdapter.ViewHolder>(query) {

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val historyName: TextView = itemView.findViewById(R.id.HistorySchoolName)
        private val historyState: TextView = itemView.findViewById(R.id.HistoryState)
        private val historyDate: TextView = itemView.findViewById(R.id.HistoryDate)
        private val container: CardView = itemView.findViewById(R.id.HistoryCardView)

        fun bind(snapshot: DocumentSnapshot, onSelectAttendance: (LocalDailyAttendance) -> Unit) {
            val attendance: RemoteDailyAttendance? =
                snapshot.toObject(RemoteDailyAttendance::class.java)

            historyName.text = attendance?.schoolName
            historyDate.text = attendance?.dateModified
            historyState.text = context.getString(R.string.sent_by, attendance?.adminName)
            container.onClick { onSelectAttendance(attendance?.mapToLocal()!!) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.attendance_history_item,
                parent,
                false,
            )
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        getSnapshot(position)?.let {
            holder.bind(it, onSelectAttendance = onSelectAttendance)
        }
    }
}