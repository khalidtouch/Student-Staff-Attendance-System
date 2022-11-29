package com.andela.eduteam14.android_app.core.data.models

data class LoginAdminRequest(
    var AdminEmail: String ="",
    var Password: String= "",
) {
    fun isValid(): Boolean {
        return AdminEmail.isNotEmpty() && Password.isNotEmpty()
    }
}