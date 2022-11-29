package com.andela.eduteam14.android_app.core.data.preferences

import android.content.Context
import androidx.core.content.edit


const val USER_SESSION = "user_session"
const val USER_SESSION_STATE = "user_session_state"

class PreferenceRepository private constructor(context: Context) {
    private val pref =
        context.applicationContext.getSharedPreferences(USER_SESSION, Context.MODE_PRIVATE)

    val state: String
        get() {
            return pref.getString(USER_SESSION_STATE, UserSessionState.NOT_DIRTY.state).toString()
        }

    fun init() {
        if (state.isEmpty()) {
            pref.edit { putString(USER_SESSION_STATE, UserSessionState.NOT_DIRTY.state) }
        }

    }

    fun stain() {
        pref.edit { putString(USER_SESSION_STATE, UserSessionState.DIRTY.state) }
    }


    companion object {
        @Volatile
        private var instance: PreferenceRepository? = null

        fun getInstance(context: Context): PreferenceRepository {
            return instance ?: synchronized(this) {
                instance?.let {
                    return it
                }

                val another = PreferenceRepository(context)
                instance = another
                another
            }
        }
    }
}

enum class UserSessionState(val state: String) {
    DIRTY("dirty"),
    NOT_DIRTY("not_dirty")
}