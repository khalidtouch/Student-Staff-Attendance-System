package com.andela.eduteam14.android_app.core.ui.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.andela.eduteam14.android_app.core.data.mock.AttendanceDataSource
import com.andela.eduteam14.android_app.core.data.mock.AttendanceRegistry
import com.andela.eduteam14.android_app.core.data.models.CreateSchoolRequest
import com.andela.eduteam14.android_app.core.data.models.DailyAttendanceRequest
import com.andela.eduteam14.android_app.core.data.models.LocalClassAttendance
import com.andela.eduteam14.android_app.core.data.repository.MainRepository
import kotlinx.coroutines.launch

class SchoolViewModel(
    private val repository: MainRepository,
) : ViewModel() {

    private var _createSchoolRequest: CreateSchoolRequest = CreateSchoolRequest()

    private var _AttendanceRequest: DailyAttendanceRequest = DailyAttendanceRequest()

    val createSchoolRequest get() = _createSchoolRequest



    fun addAttendance() {

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





}


@Suppress("UNCHECKED_CAST")
class SchoolViewModelFactory(
   private val repository: MainRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(SchoolViewModel::class.java)) {
            SchoolViewModel(repository,) as T
        } else throw IllegalArgumentException("Unknown ViewModel class")
    }
}