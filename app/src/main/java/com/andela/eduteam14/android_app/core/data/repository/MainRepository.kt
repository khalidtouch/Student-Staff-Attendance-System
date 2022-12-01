package com.andela.eduteam14.android_app.core.data.repository

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

interface MainRepository {

    fun createAccount(request: LoginAdminRequest, onResult: (Boolean) -> Unit) //

    fun login(request: LoginAdminRequest, onResult: (Boolean) -> Unit) //

    fun logout() //

    fun adminId(onResult: (String) -> Unit)

    fun activeAdminEmail(onResult: (String) -> Unit)


    fun createSchool(request: CreateSchoolRequest, onResult: (Boolean) -> Unit)

    fun createOrganization(request: CreateOrganizationRequest, onResult: (Boolean) -> Unit)

    fun createAdmin(request: CreateAdminRequest, onResult: (Boolean) -> Unit)

    fun addAttendance(attendance: LocalDailyAttendance, onResult: (Boolean) -> Unit)

    //GET
    fun findSchoolById(id: String, onResult: (RemoteSchool) -> Unit)

    fun findSchoolByName(name: String, onResult: (RemoteSchool) -> Unit)

    fun findSchoolByAdminEmail(email: String, onResult: (RemoteSchool) -> Unit)

    fun findAllSchools(onResult: (Query) -> Unit)

    fun findOrganizationByName(name: String, onResult: (RemoteOrganization) -> Unit)

    fun findOrganizationById(id: String, onResult: (RemoteOrganization) -> Unit)

    fun findOrganizationByAdminEmail(email: String, onResult: (RemoteOrganization) -> Unit)

    fun findAllOrganizations(onResult: (Query) -> Unit)

    fun findAttendanceById(id: String, onResult: (RemoteDailyAttendance) -> Unit)

    fun findAttendanceBySchool(schoolName: String, onResult: (RemoteDailyAttendance) -> Unit)

    fun findAllAttendance(onResult: (Query) -> Unit)

    fun findAdminById(id: String, onResult: (RemoteAdmin) -> Unit)

    fun findAdminByName(name: String, onResult: (RemoteAdmin) -> Unit)

    fun findAllAdmins(onResult: (Query) -> Unit)


    //DELETE
    fun removeSchoolById(id: String, onResult: (Boolean) -> Unit)

    fun removeOrganizationById(id: String, onResult: (Boolean) -> Unit)

    fun removeAttendanceById(id: String, onResult: (Boolean) -> Unit)

}