package com.andela.eduteam14.android_app.core.data.models

data class DailyStaffAttendanceRequest(
    var date: String = "",
    var maleStaff: String = "",
    var femaleStaff: String = "",
) {
    fun isValid(): Boolean {
        val invalid = date.isEmpty() || maleStaff.isEmpty() || femaleStaff.isEmpty()

        return !invalid
    }
}