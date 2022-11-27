package com.andela.eduteam14.android_app.core.data.models

import com.andela.eduteam14.android_app.core.data.firebase.models.RemoteOrganization
import java.util.UUID

data class LocalOrganization(
    val OrganizationId: String,
    val Name: String,
    val Location: String,
    val Address: String,
    val DateModified: String,
) {

    fun mapToRemote(): RemoteOrganization {
        return RemoteOrganization(
            OrganizationId,
            Name,
            Location,
            Address,
            DateModified
        )
    }

    companion object {
        val Default = LocalOrganization(
            OrganizationId = UUID.randomUUID().toString(),
            Name = "Federal Ministry of Education",
            Location = "Abuja, Nigeria",
            Address = "No, 2 GT road, Abuja",
            DateModified = "March 13, 2020"
        )
    }
}