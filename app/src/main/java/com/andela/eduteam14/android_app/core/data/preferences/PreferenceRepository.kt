package com.andela.eduteam14.android_app.core.data.preferences

import android.content.Context
import androidx.core.content.edit
import com.andela.eduteam14.android_app.core.data.models.LocalAdmin
import com.andela.eduteam14.android_app.core.data.models.LocalSchool


const val USER_SESSION = "user_session"

const val USER_ACCOUNT = "user_account"

const val USER_SESSION_STATE = "user_session_state"

const val KEY_STUDENT_MALE_TOTAL = "key_student_male"

const val KEY_STUDENT_MALE_PRESENT = "key_student_male_present"

const val KEY_STUDENT_FEMALE_TOTAL = "key_student_female"

const val KEY_STUDENT_FEMALE_PRESENT = "key_student_female_present"

const val KEY_MALE_STAFF_PRESENT = "key_male_staff"

const val KEY_FEMALE_STAFF_PRESENT = "key_female_staff"

const val KEY_ORGANIZATION_TO_JOIN = "key_organization_to_join"

const val KEY_NUMBER_OF_CLASSES = "key_number_of_classes"

const val KEY_CLASS_NUMBER = "key_class_number_"

const val KEY_ADMIN_ID = "key_admin_id"

const val KEY_ADMIN_NAME = "key_admin_name"

const val KEY_ADMIN_EMAIL = "key_admin_email"

const val KEY_ADMIN_ACCOUNT = "key_admin_account"


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

    fun saveUserAccount(type: String) {
        pref.edit { putString(USER_ACCOUNT, type) }
    }

    fun retrieveUserAccount(): String {
        return pref.getString(USER_ACCOUNT, "").toString()
    }

    fun saveTotalMaleStudents(number: Long) {
        pref.edit {
            putLong(KEY_STUDENT_MALE_TOTAL, number)
        }
    }

    fun retrieveTotalMaleStudents(): Long {
        return pref.getLong(KEY_STUDENT_MALE_TOTAL, 0L)
    }

    fun saveTotalFemaleStudents(number: Long) {
        pref.edit {
            putLong(KEY_STUDENT_FEMALE_TOTAL, number)
        }
    }

    fun retrieveTotalFemaleStudents(): Long {
        return pref.getLong(KEY_STUDENT_FEMALE_TOTAL, 0L)
    }


    fun saveMaleStaffPresent(number: Long) {
        pref.edit { putLong(KEY_MALE_STAFF_PRESENT, number) }
    }

    fun retrieveMaleStaffPresent(): Long {
        return pref.getLong(KEY_MALE_STAFF_PRESENT, 0L)
    }


    fun saveFemaleStaffPresent(number: Long) {
        pref.edit { putLong(KEY_FEMALE_STAFF_PRESENT, number) }
    }

    fun retrieveFemaleStaffPresent(): Long {
        return pref.getLong(KEY_FEMALE_STAFF_PRESENT, 0L)
    }

    fun saveOrganizationToJoin(id: String) {
        pref.edit { putString(KEY_ORGANIZATION_TO_JOIN, id) }
    }

    fun retrieveOrganizationToJoin(): String {
        return pref.getString(KEY_ORGANIZATION_TO_JOIN, "").toString()
    }

    fun saveNumberOfClasses(number: Int) {
        pref.edit { putInt(KEY_NUMBER_OF_CLASSES, number) }
    }

    fun retrieveNumberOfClasses(): Int {
        return pref.getInt(KEY_NUMBER_OF_CLASSES, 15)
    }

    fun saveMaleStudentsPresent(number: Long) {
        pref.edit { putLong(KEY_STUDENT_MALE_PRESENT, number) }
    }

    fun saveFemaleStudentsPresent(number: Long) {
        pref.edit { putLong(KEY_STUDENT_FEMALE_PRESENT, number) }
    }

    fun retrieveMaleStudentsPresent(): Long {
        return pref.getLong(KEY_STUDENT_MALE_PRESENT, 0L)
    }

    fun retrieveFemaleStudentsPresent(): Long {
        return pref.getLong(KEY_STUDENT_FEMALE_PRESENT, 0L)
    }

    fun saveClassNames(items: ArrayList<String>) {
        pref.edit { putInt(KEY_CLASS_NUMBER, items.size) }

        for (i in 0 until items.size) {
            pref.edit { putString("$KEY_CLASS_NUMBER $i", items[i]) }
        }
    }

    fun retrieveClassNames(): ArrayList<String> {
        val size = pref.getInt(KEY_CLASS_NUMBER, 0)
        val items = arrayListOf<String>()

        for (i in 0 until size) {
            val item = pref.getString("$KEY_CLASS_NUMBER $i", "").toString()
            items.add(item)
        }

        return items

    }

    fun retrieveAdmin(): LocalAdmin {
        return LocalAdmin(
            AdminId = pref.getString(KEY_ADMIN_ID, "").toString(),
            AdminEmail = pref.getString(KEY_ADMIN_EMAIL, "").toString(),
            AdminName = pref.getString(KEY_ADMIN_NAME, "").toString(),
            Account = pref.getString(KEY_ADMIN_ACCOUNT, "").toString()
        )
    }

    fun saveAdmin(admin: LocalAdmin) {
        admin.apply {
            pref.edit { putString(KEY_ADMIN_ID, AdminId) }

            pref.edit { putString(KEY_ADMIN_NAME, AdminName) }

            pref.edit { putString(KEY_ADMIN_EMAIL, AdminEmail) }

            pref.edit { putString(KEY_ADMIN_ACCOUNT, Account) }
        }
    }

    fun retrieveTotalMaleStaff(): Long {
        TODO("Not yet implemented")
    }

    fun retrieveTotalFemaleStaff(): Long {
            TODO("Not yet implemented")
    }

    fun retrieveSchoolName(): String {

    }

    fun saveSchoolInformation(school: LocalSchool) {
        
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