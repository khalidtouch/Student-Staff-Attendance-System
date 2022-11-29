package com.andela.eduteam14.android_app.core.data.firebase.manager.firestore

import com.andela.eduteam14.android_app.core.data.firebase.models.RemoteAdmin
import com.andela.eduteam14.android_app.core.data.firebase.models.RemoteDailyAttendance
import com.andela.eduteam14.android_app.core.data.firebase.models.RemoteOrganization
import com.andela.eduteam14.android_app.core.data.firebase.models.RemoteSchool
import com.andela.eduteam14.android_app.core.data.models.CreateAdminRequest
import com.andela.eduteam14.android_app.core.data.models.CreateOrganizationRequest
import com.andela.eduteam14.android_app.core.data.models.CreateSchoolRequest
import com.andela.eduteam14.android_app.core.data.models.LocalDailyAttendance
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.FirebaseFirestore

class FireStoreManagerImpl(
    private val fireStore: FirebaseFirestore,
    private val authentication: FirebaseAuth,
) : FireStoreManager {


    override fun createSchool(request: CreateSchoolRequest, onResult: (Boolean) -> Unit) {
        val school = request.toModel()

        fireStore.collection(REF_SCHOOLS).document(school.SchoolCode)
            .set(school)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(true) }
    }

    override fun createOrganization(
        request: CreateOrganizationRequest,
        onResult: (Boolean) -> Unit
    ) {
        val organization = request.toModel()

        fireStore.collection(REF_ORGANIZATIONS).document(organization.OrganizationId)
            .set(organization)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    override fun createAdmin(request: CreateAdminRequest, onResult: (Boolean) -> Unit) {
        val admin = request.toModel()

        fireStore.collection(REF_ADMINS).document(admin.AdminId)
            .set(admin)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    override fun addAttendance(attendance: LocalDailyAttendance, onResult: (Boolean) -> Unit) {
        fireStore.collection(REF_ATTENDANCE).document(attendance.AttendanceId)
            .set(attendance)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    override fun findSchoolById(id: String, onResult: (RemoteSchool) -> Unit) {

    }

    override fun findSchoolByName(name: String, onResult: (RemoteSchool) -> Unit) {

    }

    override fun findAllSchools(onResult: (List<RemoteSchool>) -> Unit) {

    }

    override fun findOrganizationByName(name: String, onResult: (RemoteOrganization) -> Unit) {

    }

    override fun findOrganizationById(id: String, onResult: (RemoteOrganization) -> Unit) {

    }

    override fun findAllOrganizations(onResult: (List<RemoteOrganization>) -> Unit) {
        //real-time
    }

    override fun findAttendanceById(id: String, onResult: (RemoteDailyAttendance) -> Unit) {

    }

    override fun findAttendanceBySchool(
        schoolName: String,
        onResult: (RemoteDailyAttendance) -> Unit
    ) {

    }

    override fun findAllAttendance(onResult: (List<RemoteDailyAttendance>) -> Unit) {
        //real-time

    }

    override fun findAdminById(id: String, onResult: (RemoteAdmin) -> Unit) {

    }

    override fun findAdminByName(name: String, onResult: (RemoteAdmin) -> Unit) {

    }

    override fun findAllAdmins(onResult: (List<RemoteAdmin>) -> Unit) {

    }

    override fun removeSchoolById(id: String, onResult: (Boolean) -> Unit) {

    }

    override fun removeOrganizationById(id: String, onResult: (Boolean) -> Unit) {

    }

    override fun removeAttendanceById(id: String, onResult: (Boolean) -> Unit) {

    }

    companion object {
        const val REF_ADMINS = "admins"
        const val REF_ORGANIZATIONS = "organizations"
        const val REF_SCHOOLS = "schools"
        const val REF_ATTENDANCE = "attendance"
    }
}