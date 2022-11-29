package com.andela.eduteam14.android_app.core.ui.home

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.andela.eduteam14.android_app.R
import com.andela.eduteam14.android_app.core.data.mock.AttendanceRegistry
import com.andela.eduteam14.android_app.core.data.models.LocalDailyAttendance
import com.andela.eduteam14.android_app.databinding.DailyAttendanceItemBinding

class SchoolHomeAdapter(
    private val registry: AttendanceRegistry,
) : ListAdapter<LocalDailyAttendance, SchoolHomeAdapter.ViewHolder>(DiffUtilCallback()) {

    inner class ViewHolder(private val itemBinding: DailyAttendanceItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(attendance: LocalDailyAttendance) {
            itemBinding.apply {

                totalMalesStaff.text = ""

                totalFemalesStaff.text = ""

                totalMalesStudent.text = ""

                totalFemalesStudent.text = ""

                time.text = ""

                SchoolName.text = ""

                MaleStaffPresent.text = ""
                FemaleStaffPresent.text = ""

                MaleStudentPresent.text = ""

                FemaleStudentPresent.text = ""

                MaleStaffAbsent.text = ""

                FemaleStaffAbsent.text = ""


                MaleStudentAbsent.text = ""

                FemaleStudentAbsent.text = ""
            }
        }
    }

    private class DiffUtilCallback :
        DiffUtil.ItemCallback<LocalDailyAttendance>() {
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
        val binding = DailyAttendanceItemBinding.inflate(
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