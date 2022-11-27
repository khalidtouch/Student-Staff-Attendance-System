package com.andela.eduteam14.android_app.core.data.models


data class CreateOrganizationRequest(
    val OrganizationId: String = "",
    val Name: String = "",
    val Location: String = "",
    val Address: String = "",
    val DateModified: String = "",
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
        )
    }
}