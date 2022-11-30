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
    val MaleStudentsTotal: Long,
    val FemaleStudentsTotal: Long,
    val MaleStaffTotal: Long,
    val FemaleStaffTotal: Long,
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
            SchoolName,
            MaleStudentsTotal,
            FemaleStudentsTotal,
            MaleStaffTotal,
            FemaleStaffTotal,
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
            SchoolName = "Maryland International School",
            MaleStaffTotal = 234,
            FemaleStaffTotal = 234,
            MaleStudentsTotal = 3009,
            FemaleStudentsTotal = 30000,
        )
    }


}