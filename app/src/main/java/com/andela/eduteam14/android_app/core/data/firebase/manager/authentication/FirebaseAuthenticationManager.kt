package com.andela.eduteam14.android_app.core.data.firebase.manager.authentication

import com.andela.eduteam14.android_app.core.data.models.CreateAdminRequest
import com.andela.eduteam14.android_app.core.data.models.LoginAdminRequest

interface FirebaseAuthenticationManager {
   fun createAccount(request: CreateAdminRequest, onResult: (Boolean) -> Unit)

   fun login(request: LoginAdminRequest, onResult: (Boolean) -> Unit)

   fun logout()
}