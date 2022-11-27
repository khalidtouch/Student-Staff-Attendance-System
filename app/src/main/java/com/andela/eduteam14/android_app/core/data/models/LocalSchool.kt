package com.andela.eduteam14.android_app.core.data.models

import com.andela.eduteam14.android_app.core.data.firebase.models.RemoteSchool

data class LocalSchool(
    val SchoolCode: String,
    val OrganizationId: String,
    val SchoolName: String,
    val AdminName: String,
    val Address: String,
    val SchoolLocation: String,
    val DateModified: String,
) {
    fun mapToRemote(): RemoteSchool {
        return RemoteSchool(
            SchoolCode,
            OrganizationId,
            SchoolName,
            AdminName,
            Address,
            SchoolLocation,
            DateModified,
        )
    }

}