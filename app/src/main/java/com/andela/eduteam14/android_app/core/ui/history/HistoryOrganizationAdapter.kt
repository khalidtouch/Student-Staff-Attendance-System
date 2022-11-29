package com.andela.eduteam14.android_app.core.ui.history

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andela.eduteam14.android_app.core.data.models.LocalDailyAttendance
import com.andela.eduteam14.android_app.databinding.AttendanceHistoryItemBinding

class HistoryOrganizationAdapter :
    ListAdapter<LocalDailyAttendance, HistoryOrganizationAdapter.ViewHolder>(DiffUtilCallback()) {

    inner class ViewHolder(private val itemBinding: AttendanceHistoryItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {
        fun bind(history: LocalDailyAttendance) {
            itemBinding.apply {
                HistorySchoolName.text = history.SchoolName
                HistoryDate.text = history.DateModified
            }
        }
    }

    private class DiffUtilCallback : DiffUtil.ItemCallback<LocalDailyAttendance>() {
        override fun areItemsTheSame(
            oldItem: LocalDailyAttendance,
            newItem: LocalDailyAttendance
        ): Boolean {
            return oldItem == newItem
        }

        override fun areContentsTheSame(
            oldItem: LocalDailyAttendance,
            newItem: LocalDailyAttendance
        ): Boolean {
            return oldItem.AttendanceId == newItem.AttendanceId
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val binding = AttendanceHistoryItemBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false,
        )

        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}