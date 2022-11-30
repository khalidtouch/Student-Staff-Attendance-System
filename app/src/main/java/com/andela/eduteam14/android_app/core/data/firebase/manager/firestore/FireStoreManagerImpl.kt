package com.andela.eduteam14.android_app.core.data.firebase.manager.firestore

import android.util.Log
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
import com.google.firebase.firestore.Query
import com.google.firebase.firestore.ktx.toObject

class FireStoreManagerImpl(
    private val fireStore: FirebaseFirestore,
) : FireStoreManager {


    override fun createSchool(request: CreateSchoolRequest, onResult: (Boolean) -> Unit) {
        val school = request.toModel()

        fireStore.collection(REF_SCHOOLS).document(school.SchoolCode)
            .set(school)
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
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
        fireStore.collection(REF_SCHOOLS)
            .whereEqualTo("SchoolCode", id)
            .get()
            .addOnSuccessListener { docs ->
                docs.forEach {
                    val school = it.toObject<RemoteSchool>()
                    onResult(school)
                }
            }
    }

    override fun findSchoolByName(name: String, onResult: (RemoteSchool) -> Unit) {
        fireStore.collection(REF_SCHOOLS)
            .whereEqualTo("SchoolName", name)
            .get()
            .addOnSuccessListener { docs ->
                docs.forEach {
                    val school = it.toObject<RemoteSchool>()
                    onResult(school)
                }
            }
    }

    override fun findAllSchools(onResult: (Query) -> Unit) {
        //real-time with paging

        val first = fireStore.collection(REF_SCHOOLS)
            .orderBy("SchoolName")
            .limit(25)

        first.get()
            .addOnSuccessListener { snapShots ->
                val lastVisible = snapShots.documents[snapShots.size() - 1]

                val next = fireStore.collection(REF_SCHOOLS)
                    .orderBy("SchoolName")
                    .startAfter(lastVisible)
                    .limit(25)

                onResult(next)
            }

    }

    override fun findOrganizationByName(name: String, onResult: (RemoteOrganization) -> Unit) {
        fireStore.collection(REF_ORGANIZATIONS)
            .whereEqualTo("Name", name)
            .get()
            .addOnSuccessListener { docs ->
                docs.forEach {
                    val organization = it.toObject<RemoteOrganization>()
                    onResult(organization)
                }
            }
    }

    override fun findOrganizationById(id: String, onResult: (RemoteOrganization) -> Unit) {

        fireStore.collection(REF_ORGANIZATIONS)
            .whereEqualTo("OrganizationId", id)
            .get()
            .addOnSuccessListener { docs ->
                docs.forEach {
                    val organization = it.toObject<RemoteOrganization>()
                    onResult(organization)
                }
            }
    }

    override fun findOrganizationByAdminEmail(
        email: String,
        onResult: (RemoteOrganization) -> Unit
    ) {
        fireStore.collection(REF_ORGANIZATIONS)
            .whereEqualTo("AdminEmail", email)
            .get()
            .addOnSuccessListener { docs ->
                docs.forEach {
                    val organization = it.toObject<RemoteOrganization>()
                    onResult(organization)
                }
            }
    }


    override fun findAllOrganizations(onResult: (Query) -> Unit) {
        //real-time with paging

        val first = fireStore.collection(REF_ORGANIZATIONS)
            .orderBy("Name")
            .limit(25)

        first.get()
            .addOnSuccessListener { snapShots ->
                val lastVisible = snapShots.documents[snapShots.size() - 1]

                val next = fireStore.collection(REF_ORGANIZATIONS)
                    .orderBy("Name")
                    .startAfter(lastVisible)
                    .limit(25)

                onResult(next)
            }
    }

    override fun findAttendanceById(id: String, onResult: (RemoteDailyAttendance) -> Unit) {
        fireStore.collection(REF_ATTENDANCE)
            .whereEqualTo("AttendanceId", id)
            .get()
            .addOnSuccessListener { docs ->
                docs.forEach {
                    val attendance = it.toObject<RemoteDailyAttendance>()
                    onResult(attendance)
                }
            }
    }

    override fun findAttendanceBySchool(
        schoolName: String,
        onResult: (RemoteDailyAttendance) -> Unit
    ) {
        fireStore.collection(REF_ATTENDANCE)
            .whereEqualTo("SchoolName", schoolName)
            .get()
            .addOnSuccessListener { docs ->
                docs.forEach {
                    val attendance = it.toObject<RemoteDailyAttendance>()
                    onResult(attendance)
                }
            }
    }

    override fun findAllAttendance(onResult: (Query) -> Unit) {
        //real-time with paging
        val first = fireStore.collection(REF_ATTENDANCE)
            .orderBy("DateModified")
            .limit(25)

        first.get()
            .addOnSuccessListener { snapShots ->
                val lastVisible = snapShots.documents[snapShots.size() - 1]

                val next = fireStore.collection(REF_ATTENDANCE)
                    .orderBy("DateModified")
                    .startAfter(lastVisible)
                    .limit(25)

                onResult(next)
            }

    }

    override fun findAdminById(id: String, onResult: (RemoteAdmin) -> Unit) {
        fireStore.collection(REF_ADMINS)
            .whereEqualTo("AdminId", id)
            .get()
            .addOnSuccessListener { docs ->
                docs.forEach {
                    val admin = it.toObject<RemoteAdmin>()
                    onResult(admin)
                }
            }

    }

    override fun findAdminByName(name: String, onResult: (RemoteAdmin) -> Unit) {
        fireStore.collection(REF_ADMINS)
            .whereEqualTo("AdminName", name)
            .get()
            .addOnSuccessListener { docs ->
                docs.forEach {
                    val admin = it.toObject<RemoteAdmin>()
                    onResult(admin)
                }
            }
    }

    override fun findAllAdmins(onResult: (Query) -> Unit) {
        val first = fireStore.collection(REF_ADMINS)
            .orderBy("AdminName")
            .limit(25)

        first.get()
            .addOnSuccessListener { snapShots ->
                val lastVisible = snapShots.documents[snapShots.size() - 1]

                val next = fireStore.collection(REF_ADMINS)
                    .orderBy("AdminName")
                    .startAfter(lastVisible)
                    .limit(25)

                onResult(next)
            }
    }

    override fun removeSchoolById(id: String, onResult: (Boolean) -> Unit) {
        fireStore.collection(REF_SCHOOLS).document(id)
            .delete()
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    override fun removeOrganizationById(id: String, onResult: (Boolean) -> Unit) {
        fireStore.collection(REF_ORGANIZATIONS).document(id)
            .delete()
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    override fun removeAttendanceById(id: String, onResult: (Boolean) -> Unit) {
        fireStore.collection(REF_ATTENDANCE).document(id)
            .delete()
            .addOnSuccessListener { onResult(true) }
            .addOnFailureListener { onResult(false) }
    }

    companion object {
        const val REF_ADMINS = "admins"
        const val REF_ORGANIZATIONS = "organizations"
        const val REF_SCHOOLS = "schools"
        const val REF_ATTENDANCE = "attendance"
        const val TAG = "FireStoreManagerImpl"
    }
}