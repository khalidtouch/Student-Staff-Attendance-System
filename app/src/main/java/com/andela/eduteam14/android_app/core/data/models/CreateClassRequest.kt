package com.andela.eduteam14.android_app.core.data.models

data class CreateClassRequest(
    var className: String = "",
    var numberOfMales: String = "",
    var numberOfFemales: String = ""
) {
    fun isValid(): Boolean {
        val invalid = className.isEmpty() || numberOfMales.isEmpty() || numberOfFemales.isEmpty()

        return !invalid
    }
}