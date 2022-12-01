package com.andela.eduteam14.android_app.core.data.models



data class CreateSchoolRequest(
    var SchoolCode: String = "",
    var OrganizationId: String = "",
    var SchoolName: String = "",
    var AdminEmail: String = "",
    var Address: String = "",
    var SchoolLocation: String = "",
    var DateModified: String = "",
) {
    fun isValid(): Boolean {
        val invalid = OrganizationId.isEmpty() || SchoolName.isEmpty()
                || AdminEmail.isEmpty() || Address.isEmpty() || SchoolLocation.isEmpty()
                || DateModified.isEmpty()

        return !invalid
    }

    fun toModel(): LocalSchool {
        return LocalSchool(
            SchoolCode,
            OrganizationId,
            SchoolName,
            AdminEmail,
            Address,
            SchoolLocation,
            DateModified
        )
    }
}