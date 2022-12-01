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
import com.google.firebase.firestore.Query
import kotlin.math.log

class MainRepositoryImpl(
    private val authManager: FirebaseAuthenticationManager,
    private val fireStoreManager: FireStoreManager,
) : MainRepository {

    companion object {
        const val TAG = "MainRepositoryImpl"
    }

    override fun createAccount(request: LoginAdminRequest, onResult: (Boolean) -> Unit) {
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

    override fun adminId(onResult: (String) -> Unit) {
        authManager.adminId(onResult)
    }

    override fun activeAdminEmail(onResult: (String) -> Unit) {
        authManager.activeAdminEmail(onResult)
    }

    override fun createSchool(request: CreateSchoolRequest, onResult: (Boolean) -> Unit) {
        fireStoreManager.createSchool(request, onResult)
    }

    override fun createOrganization(
        request: CreateOrganizationRequest,
        onResult: (Boolean) -> Unit
    ) {
        fireStoreManager.createOrganization(request, onResult)
    }

    override fun createAdmin(request: CreateAdminRequest, onResult: (Boolean) -> Unit) {
        fireStoreManager.createAdmin(request, onResult)
    }

    override fun addAttendance(attendance: LocalDailyAttendance, onResult: (Boolean) -> Unit) {
        fireStoreManager.addAttendance(attendance, onResult)
    }

    override fun findSchoolById(id: String, onResult: (RemoteSchool) -> Unit) {
        fireStoreManager.findSchoolById(id, onResult)
    }

    override fun findSchoolByName(name: String, onResult: (RemoteSchool) -> Unit) {
        fireStoreManager.findSchoolByName(name, onResult)
    }

    override fun findSchoolByAdminEmail(email: String, onResult: (RemoteSchool) -> Unit) {
        fireStoreManager.findSchoolByAdminEmail(email, onResult)
    }

    override fun findAllSchools(onResult: (Query) -> Unit) {
        fireStoreManager.findAllSchools(onResult)
    }

    override fun findOrganizationByName(name: String, onResult: (RemoteOrganization) -> Unit) {
        fireStoreManager.findOrganizationByName(name, onResult)
    }

    override fun findOrganizationById(id: String, onResult: (RemoteOrganization) -> Unit) {
        fireStoreManager.findOrganizationById(id, onResult)
    }

    override fun findOrganizationByAdminEmail(
        email: String,
        onResult: (RemoteOrganization) -> Unit
    ) {
        fireStoreManager.findOrganizationByAdminEmail(email, onResult)
    }

    override fun findAllOrganizations(onResult: (Query) -> Unit) {
        fireStoreManager.findAllOrganizations(onResult)
    }

    override fun findAttendanceById(id: String, onResult: (RemoteDailyAttendance) -> Unit) {
        fireStoreManager.findAttendanceById(id, onResult)
    }

    override fun findAttendanceBySchool(
        schoolName: String,
        onResult: (RemoteDailyAttendance) -> Unit
    ) {
        fireStoreManager.findAttendanceBySchool(schoolName, onResult)
    }

    override fun findAllAttendance(onResult: (Query) -> Unit) {
        fireStoreManager.findAllAttendance(onResult)
    }

    override fun findAdminById(id: String, onResult: (RemoteAdmin) -> Unit) {
        fireStoreManager.findAdminById(id, onResult)
    }

    override fun findAdminByName(name: String, onResult: (RemoteAdmin) -> Unit) {
        fireStoreManager.findAdminByName(name, onResult)
    }

    override fun findAllAdmins(onResult: (Query) -> Unit) {
        fireStoreManager.findAllAdmins(onResult)
    }

    override fun removeSchoolById(id: String, onResult: (Boolean) -> Unit) {
        fireStoreManager.removeSchoolById(id, onResult)
    }

    override fun removeOrganizationById(id: String, onResult: (Boolean) -> Unit) {
        fireStoreManager.removeOrganizationById(id, onResult)
    }

    override fun removeAttendanceById(id: String, onResult: (Boolean) -> Unit) {
        fireStoreManager.removeAttendanceById(id, onResult)
    }
}