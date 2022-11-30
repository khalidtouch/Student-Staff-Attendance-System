package com.andela.eduteam14.android_app.core.data.models

data class DailyAttendanceRequest(
    var attendanceId: String = "",
    var adminName: String = "",
    var maleStudentsPresent: Long = 0,
    var femaleStudentsPresent: Long = 0,
    var maleStaffPresent: Long = 0,
    var femaleStaffPresent: Long = 0,
    var dateModified: String = ""
) {
    fun isValid(): Boolean {
        val invalid =
            attendanceId.isEmpty() ||
                    adminName.isEmpty() ||
                    maleStudentsPresent == 0L ||
                    femaleStudentsPresent == 0L ||
                    maleStaffPresent == 0L ||
                    femaleStaffPresent == 0L ||
                    dateModified.isEmpty()

        return !invalid
    }
}