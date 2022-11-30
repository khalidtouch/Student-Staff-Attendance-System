package com.andela.eduteam14.android_app.core.data.models


data class CreateOrganizationRequest(
    var OrganizationId: String = "",
    var Name: String = "",
    var Location: String = "",
    var Address: String = "",
    var DateModified: String = "",
    var AdminEmail: String = "",
) {
    fun isValid(): Boolean {
        val invalid =
            Name.isEmpty() || Location.isEmpty() || Address.isEmpty() || DateModified.isEmpty()

        return !invalid
    }

    fun toModel(): LocalOrganization {
        return LocalOrganization(
            OrganizationId,
            Name,
            Location,
            Address,
            DateModified,
            AdminEmail
        )
    }
}