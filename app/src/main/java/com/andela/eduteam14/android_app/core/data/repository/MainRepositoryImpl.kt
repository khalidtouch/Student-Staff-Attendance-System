package com.andela.eduteam14.android_app.core.data.repository

import android.util.Log
import com.andela.eduteam14.android_app.core.data.firebase.manager.authentication.FirebaseAuthenticationManager
import com.andela.eduteam14.android_app.core.data.firebase.manager.firestore.FireStoreManager
import com.andela.eduteam14.android_app.core.data.firebase.models.RemoteAdmin
import com.andela.eduteam14.android_app.core.data.firebase.models.RemoteDailyAttendance
import com.andela.eduteam14.android_app.core.data.firebase.models.RemoteOrganization
import com.andela.eduteam14.android_app.core.data.firebase.models.RemoteSchool
import com.andela.eduteam14.android_app.core.data.models.CreateAdminRequest
import com.andela.eduteam14.android_app.core.data.models.CreateOrganizationRequest
import com.andela.eduteam14.android_app.core.data.models.CreateSchoolRequest
import com.andela.eduteam14.android_app.core.data.models.LocalDailyAttendance
import com.andela.eduteam14.android_app.core.data.models.LoginAdminRequest
import kotlin.math.log

class MainRepositoryImpl(
    private val authManager: FirebaseAuthenticationManager,
) : MainRepository {

    companion object {
        const val TAG = "MainRepositoryImpl"
    }

    override fun createAccount(request: CreateAdminRequest, onResult: (Boolean) -> Unit) {
        Log.i(TAG, "createAccount: Creating user account")
        authManager.createAccount(request, onResult)
    }

    override fun login(request: LoginAdminRequest, onResult: (Boolean) -> Unit) {
        authManager.login(request, onResult)
    }

    override fun logout() {
        Log.i(TAG, "logout: Logged out")
        authManager.logout()
    }

    override fun createSchool(request: CreateSchoolRequest, onResult: (Boolean) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun createOrganization(
        request: CreateOrganizationRequest,
        onResult: (Boolean) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun createAdmin(request: CreateAdminRequest, onResult: (Boolean) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun addAttendance(attendance: LocalDailyAttendance, onResult: (Boolean) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun findSchoolById(id: String, onResult: (RemoteSchool) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun findSchoolByName(name: String, onResult: (RemoteSchool) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun findAllSchools(onResult: (List<RemoteSchool>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun findOrganizationByName(name: String, onResult: (RemoteOrganization) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun findOrganizationById(id: String, onResult: (RemoteOrganization) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun findAllOrganizations(onResult: (List<RemoteOrganization>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun findAttendanceById(id: String, onResult: (RemoteDailyAttendance) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun findAttendanceBySchool(
        schoolName: String,
        onResult: (RemoteDailyAttendance) -> Unit
    ) {
        TODO("Not yet implemented")
    }

    override fun findAllAttendance(onResult: (List<RemoteDailyAttendance>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun findAdminById(id: String, onResult: (RemoteAdmin) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun findAdminByName(name: String, onResult: (RemoteAdmin) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun findAllAdmins(onResult: (List<RemoteAdmin>) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun removeSchoolById(id: String, onResult: (Boolean) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun removeOrganizationById(id: String, onResult: (Boolean) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun removeAttendanceById(id: String, onResult: (Boolean) -> Unit) {
        TODO("Not yet implemented")
    }
}