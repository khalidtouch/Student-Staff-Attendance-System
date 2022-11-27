package com.andela.eduteam14.android_app.core.data.models



data class CreateSchoolRequest(
    val SchoolCode: String = "",
    val OrganizationId: String = "",
    val SchoolName: String = "",
    val AdminName: String = "",
    val Address: String = "",
    val SchoolLocation: String = "",
    val DateModified: String = "",
) {
    fun isValid(): Boolean {
        val invalid = OrganizationId.isEmpty() || SchoolName.isEmpty()
                || AdminName.isEmpty() || Address.isEmpty() || SchoolLocation.isEmpty()
                || DateModified.isEmpty()

        return !invalid
    }

    fun toModel(): LocalSchool {
        return LocalSchool(
            SchoolCode,
            OrganizationId,
            SchoolName,
            AdminName,
            Address,
            SchoolLocation,
            DateModified
        )
    }
}