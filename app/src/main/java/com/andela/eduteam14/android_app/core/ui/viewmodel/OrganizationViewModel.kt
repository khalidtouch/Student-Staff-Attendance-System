package com.andela.eduteam14.android_app.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.andela.eduteam14.android_app.core.data.mock.AttendanceDataSource
import com.andela.eduteam14.android_app.core.data.mock.AttendanceRegistry
import com.andela.eduteam14.android_app.core.data.models.CreateOrganizationRequest
import com.andela.eduteam14.android_app.core.data.repository.MainRepository
import com.google.firebase.firestore.Query
import kotlinx.coroutines.launch

class OrganizationViewModel(
    private val repository: MainRepository,
) : ViewModel() {

    private var _createOrganiztionRequest: CreateOrganizationRequest = CreateOrganizationRequest()

    val createOrganizationRequest get() = _createOrganiztionRequest

    fun setOrganizationRequest(request: CreateOrganizationRequest) {
        this._createOrganiztionRequest = request
    }



    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }

    }
}


@Suppress("UNCHECKED_CAST")
class OrganizationViewModelFactory(
    private val repository: MainRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(OrganizationViewModel::class.java)) {
            OrganizationViewModel(repository) as T
        } else throw IllegalArgumentException("Unknown ViewModel class")
    }
}