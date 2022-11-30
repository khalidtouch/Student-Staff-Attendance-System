package com.andela.eduteam14.android_app.core.data.preferences

import android.content.Context
import androidx.core.content.edit


const val USER_SESSION = "user_session"

const val USER_SESSION_STATE = "user_session_state"

const val KEY_STUDENT_MALE = "key_student_male"

const val KEY_STUDENT_FEMALE = "key_student_female"

const val KEY_MALE_STAFF = "key_male_staff"

const val KEY_FEMALE_STAFF = "key_female_staff"

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

    fun saveTotalMaleStudents(number: Long) {
        pref.edit {
            putLong(KEY_STUDENT_MALE, number)
        }
    }

    fun retrieveTotalMaleStudents(): Long {
        return pref.getLong(KEY_STUDENT_MALE, 0L)
    }

    fun saveTotalFemaleStudents(number: Long) {
        pref.edit {
            putLong(KEY_STUDENT_FEMALE, number)
        }
    }

    fun retrieveTotalFemaleStudents(): Long {
        return pref.getLong(KEY_STUDENT_FEMALE, 0L)
    }


    fun saveMaleStaffPresent(number: Long) {
        pref.edit { putLong(KEY_MALE_STAFF, number) }
    }

    fun retrieveMaleStaff(): Long {
        return pref.getLong(KEY_MALE_STAFF, 0L)
    }


    fun saveFemaleStaffPresent(number: Long) {
        pref.edit { putLong(KEY_FEMALE_STAFF, number) }
    }

    fun retrieveFemaleStaff(): Long {
        return pref.getLong(KEY_FEMALE_STAFF, 0L)
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