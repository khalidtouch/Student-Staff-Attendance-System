package com.andela.eduteam14.android_app.core.data.models


data class CreateAdminRequest(
    var AdminId: String = "",
    var AdminName: String = "",
    var AdminPhone: String = "",
    var Account: String = "",
) {
    fun isValid(): Boolean {
        val invalid = AdminPhone.isEmpty()

        return !invalid
    }

    fun toModel(): LocalAdmin {
        return LocalAdmin(
            AdminId, AdminName, AdminPhone, Account,
        )
    }

}