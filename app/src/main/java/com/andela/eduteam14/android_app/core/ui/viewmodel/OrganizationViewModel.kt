package com.andela.eduteam14.android_app.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.andela.eduteam14.android_app.core.data.firebase.models.RemoteOrganization
import com.andela.eduteam14.android_app.core.data.models.CreateOrganizationRequest
import com.andela.eduteam14.android_app.core.data.repository.MainRepository
import kotlinx.coroutines.launch

class OrganizationViewModel(
    private val repository: MainRepository,
) : ViewModel() {

    private var _createOrganizationRequest: CreateOrganizationRequest = CreateOrganizationRequest()

    val createOrganizationRequest get() = _createOrganizationRequest

    fun setOrganizationRequest(request: CreateOrganizationRequest) {
        this._createOrganizationRequest = request
    }


    fun saveOrganization(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            repository.createOrganization(_createOrganizationRequest, onResult)
        }
    }

    fun logout() {
        viewModelScope.launch {
            repository.logout()
        }

    }

    fun findOrganizationByAdminEmail(email: String, onResult: (RemoteOrganization) -> Unit) {
        viewModelScope.launch {
            repository.findOrganizationByAdminEmail(email, onResult)
        }
    }

    fun activeAdminEmail(onResult: (String) -> Unit) {
        viewModelScope.launch { repository.activeAdminEmail(onResult) }

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