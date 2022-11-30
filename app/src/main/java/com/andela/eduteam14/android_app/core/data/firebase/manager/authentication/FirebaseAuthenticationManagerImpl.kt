package com.andela.eduteam14.android_app.core.data.firebase.manager.authentication

import com.andela.eduteam14.android_app.core.data.models.CreateAdminRequest
import com.andela.eduteam14.android_app.core.data.models.LoginAdminRequest
import com.google.firebase.auth.FirebaseAuth

class FirebaseAuthenticationManagerImpl(
    private val authentication: FirebaseAuth,
) : FirebaseAuthenticationManager {

    override fun createAccount(request: CreateAdminRequest, onResult: (Boolean) -> Unit) {
        authentication.createUserWithEmailAndPassword(
            request.AdminEmail, request.Password
        ).addOnCompleteListener {
            if (it.isSuccessful) onResult(true) else onResult(false)
        }
    }

    override fun login(request: LoginAdminRequest, onResult: (Boolean) -> Unit) {
        authentication.signInWithEmailAndPassword(
            request.AdminEmail, request.Password,
        ).addOnCompleteListener {
            if (it.isSuccessful) onResult(true) else onResult(false)
        }
    }

    override fun logout() {
        authentication.signOut()
    }

    override fun activeAdminEmail(onResult: (String) -> Unit) {
        onResult(authentication.currentUser?.email.toString())
    }
}