package com.andela.eduteam14.android_app.core.di

import android.app.Application
import com.andela.eduteam14.android_app.core.data.firebase.manager.authentication.FirebaseAuthenticationManager
import com.andela.eduteam14.android_app.core.data.firebase.manager.authentication.FirebaseAuthenticationManagerImpl
import com.andela.eduteam14.android_app.core.data.mock.AttendanceDataSource
import com.andela.eduteam14.android_app.core.data.mock.AttendanceRegistry
import com.andela.eduteam14.android_app.core.data.repository.MainRepository
import com.andela.eduteam14.android_app.core.data.repository.MainRepositoryImpl
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

class CoreComponent(application: Application) {

    val dataSource: AttendanceDataSource by lazy { provideDataSource() } //

    val registry: AttendanceRegistry by lazy { provideRegistry() } //

    private val authentication by lazy { Firebase.auth }



    val repository by lazy { provideMainRepository(authManager) }

    private val authManager by lazy { provideAuthManager(authentication) }

    private fun provideDataSource(): AttendanceDataSource = AttendanceDataSource()

    private fun provideRegistry(): AttendanceRegistry = AttendanceRegistry()


    private fun provideAuthManager(authentication: FirebaseAuth): FirebaseAuthenticationManager =
        FirebaseAuthenticationManagerImpl(authentication)

    private fun provideMainRepository(authManager: FirebaseAuthenticationManager): MainRepository =
        MainRepositoryImpl(authManager)
}