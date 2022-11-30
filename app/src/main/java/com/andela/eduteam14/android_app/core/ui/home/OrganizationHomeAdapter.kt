package com.andela.eduteam14.android_app.core.ui.home

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
import com.andela.eduteam14.android_app.core.data.mock.AttendanceRegistry
import com.andela.eduteam14.android_app.core.data.models.LocalDailyAttendance
import com.andela.eduteam14.android_app.core.domain.usecase.AbsentUseCase
import com.andela.eduteam14.android_app.core.ui.extensions.onClick
import com.andela.eduteam14.android_app.databinding.DailyAttendanceItemBinding
import com.google.firebase.firestore.DocumentSnapshot
import com.google.firebase.firestore.Query


class OrganizationHomeAdapter(
    private val context: Context,
    query: Query,
    private val onSelectAttendance: (LocalDailyAttendance) -> Unit,
) :
    FireStoreAdapter<OrganizationHomeAdapter.ViewHolder>(query) {

    val absentCase = AbsentUseCase()

    inner class ViewHolder(itemView: View) :
        RecyclerView.ViewHolder(itemView) {

        private val container: CardView = itemView.findViewById(R.id.container)
        private val time: TextView = itemView.findViewById(R.id.time)
        private val schoolName: TextView = itemView.findViewById(R.id.SchoolName)
        private val maleStudentPresent: TextView = itemView.findViewById(R.id.MaleStudentPresent)

        private val femaleStudentPresent: TextView =
            itemView.findViewById(R.id.FemaleStudentPresent)
        private val maleStudentAbsent: TextView = itemView.findViewById(R.id.MaleStudentAbsent)
        private val femaleStudentAbsent: TextView = itemView.findViewById(R.id.FemaleStudentAbsent)

        private val totalMaleStudent: TextView = itemView.findViewById(R.id.totalMalesStudent)
        private val totalFemaleStudent: TextView = itemView.findViewById(R.id.totalFemalesStudent)

        private val maleStaffPresent: TextView = itemView.findViewById(R.id.MaleStaffPresent)
        private val femaleStaffPresent: TextView = itemView.findViewById(R.id.FemaleStaffPresent)
        private val maleStaffAbsent: TextView = itemView.findViewById(R.id.MaleStaffAbsent)
        private val femaleStaffAbsent: TextView = itemView.findViewById(R.id.FemaleStaffAbsent)

        private val totalMaleStaff: TextView = itemView.findViewById(R.id.totalMalesStaff)
        private val totalFemaleStaff: TextView = itemView.findViewById(R.id.totalFemalesStaff)


        fun bind(snapshot: DocumentSnapshot, onSelectAttendance: (LocalDailyAttendance) -> Unit) {
            val attendance: RemoteDailyAttendance? =
                snapshot.toObject(RemoteDailyAttendance::class.java)

            time.text = attendance?.dateModified

            schoolName.text = attendance?.schoolName

            maleStudentPresent.text =
                context.getString(R.string.male_student, attendance?.maleStudentsPresent.toString())

            femaleStudentPresent.text =
                context.getString(
                    R.string.female_student,
                    attendance?.femaleStudentsPresent.toString()
                )


            maleStudentAbsent.text =
                context.getString(
                    R.string.male_student, absentCase(
                        attendance?.maleStudentsPresent!!,
                        attendance?.maleStudentsTotal!!
                    ).toString()
                )

            femaleStudentAbsent.text =
                context.getString(
                    R.string.female_student, absentCase(
                        attendance.femaleStudentsPresent,
                        attendance.femaleStudentsTotal
                    ).toString()
                )

            maleStaffPresent.text =
                context.getString(
                    R.string.male_staff, attendance.maleStaffPresent.toString()
                )

            femaleStaffPresent.text =
                context.getString(
                    R.string.female_staff, attendance.femaleStaffPresent.toString()
                )


            maleStaffAbsent.text =
                context.getString(
                    R.string.male_staff, absentCase(
                        attendance.maleStaffPresent,
                        attendance.maleStaffTotal
                    ).toString()
                )

            femaleStaffAbsent.text =
                context.getString(
                    R.string.female_staff, absentCase(
                        attendance.femaleStaffPresent,
                        attendance.femaleStaffTotal
                    ).toString()
                )


            totalMaleStudent.text =
                context.getString(
                    R.string.male_student, attendance.maleStudentsTotal.toString()
                )

            totalFemaleStudent.text =
                context.getString(
                    R.string.female_student, attendance.femaleStudentsTotal.toString()
                )

            totalMaleStaff.text =
                context.getString(
                    R.string.male_staff, attendance.maleStaffTotal.toString()
                )

            totalFemaleStaff.text =
                context.getString(
                    R.string.female_staff, attendance.femaleStaffTotal.toString()
                )

            container.onClick { onSelectAttendance(attendance.mapToLocal()!!) }
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(
                R.layout.daily_attendance_item,
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

    fun snapshots(): ArrayList<DocumentSnapshot>? = getSnapshots()
}
