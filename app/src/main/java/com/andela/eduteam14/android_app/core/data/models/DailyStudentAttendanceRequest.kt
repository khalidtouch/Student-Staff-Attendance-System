package com.andela.eduteam14.android_app.core.data.models

data class DailyStudentAttendanceRequest(
    var date: String = "",
    var className: String = "",
    var maleStudents: Long = 0L,
    var femaleStudents: Long = 0L,
) {
    fun isValid(): Boolean {
        val invalid = date.isEmpty() || className.isEmpty() ||
                maleStudents == 0L || femaleStudents == 0L

        return !invalid
    }
}