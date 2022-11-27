package com.andela.eduteam14.android_app.core.data.firebase.manager.database

import com.andela.eduteam14.android_app.core.data.firebase.models.RemoteAdmin
import com.andela.eduteam14.android_app.core.data.firebase.models.RemoteDailyAttendance
import com.andela.eduteam14.android_app.core.data.firebase.models.RemoteOrganization
import com.andela.eduteam14.android_app.core.data.firebase.models.RemoteSchool
import com.andela.eduteam14.android_app.core.data.models.CreateAdminRequest
import com.andela.eduteam14.android_app.core.data.models.CreateOrganizationRequest
import com.andela.eduteam14.android_app.core.data.models.CreateSchoolRequest
import com.andela.eduteam14.android_app.core.data.models.LocalDailyAttendance
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.FirebaseDatabase

class FirebaseDatabaseManagerImpl(
   private val  authentication: FirebaseAuth,
   private val  database: FirebaseDatabase,
) : FirebaseDatabaseManager {

    private val ref = database.reference

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
       val user = request.toModel()

        ref.child(ADMINS).child(user.AdminId).setValue(user)

        onResult(true)
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

    override fun findOrganizationByName(name: String, onResult: (RemoteOrganization) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun findOrganizationById(id: String, onResult: (RemoteOrganization) -> Unit) {
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

    override fun findAdminById(id: String, onResult: (RemoteAdmin) -> Unit) {
        TODO("Not yet implemented")
    }

    override fun findAdminByName(name: String, onResult: (RemoteAdmin) -> Unit) {
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

    companion object {
        const val ADMINS = "admins"
    }
}