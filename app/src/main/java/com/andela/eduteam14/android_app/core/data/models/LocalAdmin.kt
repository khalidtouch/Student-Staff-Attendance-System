package com.andela.eduteam14.android_app.core.data.models

import com.andela.eduteam14.android_app.core.data.firebase.models.RemoteAdmin

data class LocalAdmin(
    val AdminId: String,
    val AdminName: String,
    val AdminEmail: String,
    val Account: String,
) {
    fun mapToRemote(): RemoteAdmin {
        return RemoteAdmin(
            AdminId,
            AdminName,
            AdminEmail,
            Account,
        )
    }
}