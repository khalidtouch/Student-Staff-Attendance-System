package com.andela.eduteam14.android_app.core.domain.listeners

import com.andela.eduteam14.android_app.core.data.models.LocalDailyAttendance

interface OnPublishDailyAttendanceListener {
    fun onPublish(attendance: LocalDailyAttendance)
}