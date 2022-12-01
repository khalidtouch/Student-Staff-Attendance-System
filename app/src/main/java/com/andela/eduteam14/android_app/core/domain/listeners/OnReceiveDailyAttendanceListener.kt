package com.andela.eduteam14.android_app.core.domain.listeners

import com.andela.eduteam14.android_app.core.data.models.LocalDailyAttendance

interface OnReceiveDailyAttendanceListener {
    fun onReceive(attendance: LocalDailyAttendance)
}