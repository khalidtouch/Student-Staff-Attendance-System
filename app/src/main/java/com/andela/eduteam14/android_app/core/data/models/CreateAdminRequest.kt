package com.andela.eduteam14.android_app.core.data.models


data class CreateAdminRequest(
    var AdminId: String = "",
    var AdminName: String = "",
    var AdminEmail: String = "",
    var Password: String = "",
    var ConfirmPassword: String = "",
) {
    fun isValid(): Boolean {
        val invalid = AdminEmail.isEmpty() || Password.isEmpty() || Password != ConfirmPassword

        return !invalid
    }

    fun toModel(): LocalAdmin {
        return LocalAdmin(
            AdminId, AdminName, AdminEmail
        )
    }

}