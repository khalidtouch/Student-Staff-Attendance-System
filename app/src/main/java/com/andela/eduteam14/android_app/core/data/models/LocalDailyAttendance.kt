package com.andela.eduteam14.android_app.core.data.models

import com.andela.eduteam14.android_app.core.data.firebase.models.RemoteDailyAttendance
import java.util.UUID

data class LocalDailyAttendance(
    val AttendanceId: String,
    val AdminName: String,
    val MaleStudentsPresent: Long,
    val FemaleStudentsPresent: Long,
    val MaleStaffPresent: Long,
    val FemaleStaffPresent: Long,
    val DateModified: String,
    val SchoolName: String,
) {

    fun mapToRemote(): RemoteDailyAttendance {
        return RemoteDailyAttendance(
            AttendanceId,
            AdminName,
            MaleStudentsPresent,
            FemaleStaffPresent,
            MaleStaffPresent,
            FemaleStaffPresent,
            DateModified,
            SchoolName
        )
    }
    companion object {
        val Default = LocalDailyAttendance(
           AttendanceId = UUID.randomUUID().toString(),
            AdminName = "James Bond",
            MaleStudentsPresent = 234,
            FemaleStudentsPresent = 234,
            MaleStaffPresent = 12,
            FemaleStaffPresent = 14,
            DateModified = "March 12, 2022",
            SchoolName = "Maryland International School"
        )
    }


}