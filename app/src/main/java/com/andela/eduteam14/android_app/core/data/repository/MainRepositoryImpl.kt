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

class MainRepositoryImpl(
) : MainRepository {



    companion object {
        const val TAG = "MainRepositoryImpl"
    }
}