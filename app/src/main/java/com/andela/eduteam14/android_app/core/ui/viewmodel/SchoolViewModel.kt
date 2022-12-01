package com.andela.eduteam14.android_app.core.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.andela.eduteam14.android_app.core.data.firebase.models.RemoteSchool
import com.andela.eduteam14.android_app.core.data.models.CreateAdminRequest
import com.andela.eduteam14.android_app.core.data.models.CreateClassRequest
import com.andela.eduteam14.android_app.core.data.models.CreateSchoolRequest
import com.andela.eduteam14.android_app.core.data.models.DailyAttendanceRequest
import com.andela.eduteam14.android_app.core.data.models.DailyStaffAttendanceRequest
import com.andela.eduteam14.android_app.core.data.models.DailyStudentAttendanceRequest
import com.andela.eduteam14.android_app.core.data.models.LocalDailyAttendance
import com.andela.eduteam14.android_app.core.data.preferences.PreferenceRepository
import com.andela.eduteam14.android_app.core.data.repository.MainRepository
import com.andela.eduteam14.android_app.core.domain.listeners.OnPublishDailyAttendanceListener
import com.andela.eduteam14.android_app.core.domain.usecase.LongAggregationUseCase
import com.andela.eduteam14.android_app.core.domain.usecase.StudentAggregationUseCase
import kotlinx.coroutines.launch

class SchoolViewModel(
    private val repository: MainRepository,
) : ViewModel() {

    private var _createSchoolRequest: CreateSchoolRequest = CreateSchoolRequest()

    private var _attendanceRequest: DailyAttendanceRequest = DailyAttendanceRequest()

    private var _createClassRequest: CreateClassRequest = CreateClassRequest()

    private var _createAdminRequest: CreateAdminRequest = CreateAdminRequest()

    private var _classRequestList = ArrayList<CreateClassRequest>()

    private var _maleStudentsPresent = ArrayList<String>()

    val maleStudentsPresent get() = _maleStudentsPresent

    private var _femaleStudentsPresent = ArrayList<String>()

    val femaleStudentsPresent get() = _femaleStudentsPresent


    private var _dailyStaffAttendanceRequest: DailyStaffAttendanceRequest =
        DailyStaffAttendanceRequest()

    private var _dailyStudentAttendanceRequest: DailyStudentAttendanceRequest =
        DailyStudentAttendanceRequest()

    val createSchoolRequest get() = _createSchoolRequest
    val createClassRequest get() = _createClassRequest
    val classRequestList get() = _classRequestList
    val attendanceRequest get() = _attendanceRequest

    val dailyStaffAttendanceRequest get() = _dailyStaffAttendanceRequest

    val dailyStudentAttendanceRequest get() = _dailyStudentAttendanceRequest

    val createAdminRequest get() = _createAdminRequest

    var currentClass = 1

    var maxClasses = 0

    val aggregator = StudentAggregationUseCase()

    val longAggregation = LongAggregationUseCase()

    private val pages: ArrayList<Map<String, Pair<String, String>>> = arrayListOf()

    val studentPageInstance = mapOf<String, Pair<String, String>>()

    fun saveAllMaleStudentsPresent(items: ArrayList<String>, pref: PreferenceRepository) {
        pref.saveMaleStudentsPresent(longAggregation(items).toLong())
    }

    fun saveAllFemaleStudentsPresent(items: ArrayList<String>, pref: PreferenceRepository) {
        pref.saveFemaleStudentsPresent(longAggregation(items).toLong())
    }

    fun addInstanceToPages(currentClass: String, males: String, females: String) {
        val instance = mapOf(currentClass to Pair(males, females))
        this.pages.add(instance)
    }

    fun retrieveInstance(currentClass: String, onResult: (Pair<String, String>) -> Unit) {
        viewModelScope.launch {
            val instance = pages.find {
                it.keys.equals(currentClass)
            }

            onResult(instance?.get(currentClass)!!)
        }
    }


    fun updateMaleStudents(number: String) {
        _maleStudentsPresent.add(number)
    }

    fun updateFemaleStudents(number: String) {
        _femaleStudentsPresent.add(number)
    }

    fun setMaxClasses(pref: PreferenceRepository) {
        maxClasses = pref.retrieveNumberOfClasses()
    }

    fun setAdminRequest(request: CreateAdminRequest) {
        this._createAdminRequest = request
    }

    fun setMaleStaff(number: String) {
        try {
            this._dailyStaffAttendanceRequest.maleStaff = number
        } catch (e: NumberFormatException) {
            Log.e(TAG, "setMaleStaff: Number format exception", e)
        }
    }

    fun setFemaleStaff(number: String) {
        try {
            this._dailyStaffAttendanceRequest.femaleStaff = number
        } catch (e: NumberFormatException) {
            Log.e(TAG, "setFemaleStaff: Number format exception", e)
        }
    }


    fun addClass(onResult: (CreateClassRequest) -> Unit) {
        if (_createClassRequest.isValid() && _classRequestList.size < maxClasses) {
            _classRequestList.add(_createClassRequest)
        }

        currentClass += 1

        this._createClassRequest = CreateClassRequest()

        onResult(this._createClassRequest)
    }

    fun setClassName(name: String) {
        this._createClassRequest.className = name
    }

    fun setNumberOfMaleStudents(number: String) {
        try {
            this._createClassRequest.numberOfMales = number
        } catch (e: NumberFormatException) {
            Log.e(TAG, "setNumberOfStudents: Number format exception", e)
        }
    }

    fun setNumberOfFemaleStudents(number: String) {
        try {
            this._createClassRequest.numberOfFemales = number
        } catch (e: NumberFormatException) {
            Log.e(TAG, "setNumberOfStudents: Number format exception", e)
        }

    }


    fun onCommitClassInformation(pref: PreferenceRepository) {

        pref.saveTotalMaleStudents(aggregator(classRequestList).first)
        pref.saveTotalFemaleStudents(aggregator(classRequestList).second)

        Log.d(TAG, "onCommitClassInformation: Saved student info")
    }

    fun setSchoolRequest(request: CreateSchoolRequest) {
        this._createSchoolRequest = request
    }


    fun createSchool(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            repository.createSchool(_createSchoolRequest, onResult)
        }
    }

    fun findSchoolByAdminEmail(email: String, onResult: (RemoteSchool) -> Unit) {
        viewModelScope.launch {
            repository.findSchoolByAdminEmail(email, onResult)
        }
    }

    fun activeAdminEmail(onResult: (String) -> Unit) {
        viewModelScope.launch { repository.activeAdminEmail(onResult) }

    }

    fun adminId(onResult: (String) -> Unit) {
        viewModelScope.launch { repository.adminId(onResult) }
    }

    fun createAdmin(onResult: (Boolean) -> Unit) {
        viewModelScope.launch { repository.createAdmin(createAdminRequest, onResult) }

    }


    companion object {
        val TAG = "SchoolViewModel"
    }

    fun onPublish(attendance: LocalDailyAttendance) {
        viewModelScope.launch {
            repository.addAttendance(attendance) {}
        }

    }
}


@Suppress("UNCHECKED_CAST")
class SchoolViewModelFactory(
    private val repository: MainRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SchoolViewModel::class.java)) {
            SchoolViewModel(repository) as T
        } else throw IllegalArgumentException("Unknown ViewModel class")
    }
}