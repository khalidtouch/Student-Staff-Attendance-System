package com.andela.eduteam14.android_app.core.data.models


data class CreateAdminRequest(
    val AdminId: String = "",
    val AdminName: String = "",
    val AdminEmail: String = "",
    val Password: String = "",
    val ConfirmPassword: String = "",
) {
    fun isValid(): Boolean {
        val invalid = AdminName.isEmpty() || AdminEmail.isEmpty()

        return !invalid && passwordValid
    }

    fun toModel(): LocalAdmin {
        return LocalAdmin(
            AdminId, AdminName, AdminEmail
        )
    }

    private val passwordValid = Password == ConfirmPassword
}