package com.andela.eduteam14.android_app.core.di

import android.app.Application
import com.andela.eduteam14.android_app.core.data.mock.AttendanceDataSource
import com.andela.eduteam14.android_app.core.data.mock.AttendanceRegistry
import com.google.firebase.auth.ktx.auth
import com.google.firebase.database.ktx.database
import com.google.firebase.ktx.Firebase

class CoreComponent(application: Application) {

    val dataSource: AttendanceDataSource by lazy { provideDataSource() }

    val registry: AttendanceRegistry by lazy { provideRegistry() }

    val authentication by lazy { Firebase.auth }

    val database by lazy { Firebase.database }

    private fun provideDataSource(): AttendanceDataSource = AttendanceDataSource()

    private fun provideRegistry(): AttendanceRegistry = AttendanceRegistry()
}