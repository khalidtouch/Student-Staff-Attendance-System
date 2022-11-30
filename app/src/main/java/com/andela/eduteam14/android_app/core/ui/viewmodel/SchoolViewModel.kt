package com.andela.eduteam14.android_app.core.ui.viewmodel

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.andela.eduteam14.android_app.core.data.models.CreateClassRequest
import com.andela.eduteam14.android_app.core.data.models.CreateSchoolRequest
import com.andela.eduteam14.android_app.core.data.models.DailyAttendanceRequest
import com.andela.eduteam14.android_app.core.data.models.DailyStaffAttendanceRequest
import com.andela.eduteam14.android_app.core.data.models.DailyStudentAttendanceRequest
import com.andela.eduteam14.android_app.core.data.preferences.PreferenceRepository
import com.andela.eduteam14.android_app.core.data.repository.MainRepository
import com.andela.eduteam14.android_app.core.domain.usecase.StudentAggregationUseCase
import kotlinx.coroutines.launch

class SchoolViewModel(
    private val repository: MainRepository,
) : ViewModel() {

    private var _createSchoolRequest: CreateSchoolRequest = CreateSchoolRequest()

    private var _attendanceRequest: DailyAttendanceRequest = DailyAttendanceRequest()

    private var _createClassRequest: CreateClassRequest = CreateClassRequest()

    private var _classRequestList = ArrayList<CreateClassRequest>(15)

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

    var currentClass = 1

    val maxClasses = 3

    val aggregator = StudentAggregationUseCase()

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

    fun setStaffAttendanceDate(date: String) {
        this._dailyStaffAttendanceRequest.date = date
    }

    fun onCommitStaffAttendance(pref: PreferenceRepository) {
        pref.saveMaleStaffPresent(_dailyStaffAttendanceRequest.maleStaff.toLong())
        pref.saveFemaleStaffPresent(_dailyStaffAttendanceRequest.femaleStaff.toLong())

        Log.i(TAG, "onCommitStaffAttendance: Staff attendance info has been saved")
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

    fun setSchoolCode(code: String) {
        this._createSchoolRequest.SchoolCode = code
    }

    fun setOrganizationId(id: String) {
        this._createSchoolRequest.OrganizationId = id
    }

    fun setSchoolName(name: String) {
        this._createSchoolRequest.SchoolName = name
    }

    fun setAdminName(name: String) {
        this._createSchoolRequest.AdminName = name
    }

    fun setAddress(address: String) {
        this._createSchoolRequest.Address = address
    }

    fun setSchoolLocation(location: String) {
        this._createSchoolRequest.SchoolLocation = location
    }

    fun setDateModified(date: String) {
        this._createSchoolRequest.DateModified = date
    }


    fun createSchool(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            repository.createSchool(_createSchoolRequest, onResult)
        }
    }


    companion object {
        val TAG = "SchoolViewModel"
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