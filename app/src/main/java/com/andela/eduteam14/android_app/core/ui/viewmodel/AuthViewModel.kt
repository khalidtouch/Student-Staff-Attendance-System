package com.andela.eduteam14.android_app.core.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.andela.eduteam14.android_app.core.data.models.CreateAdminRequest
import com.andela.eduteam14.android_app.core.data.models.LoginAdminRequest
import com.andela.eduteam14.android_app.core.data.repository.MainRepository
import kotlinx.coroutines.launch

class AuthViewModel(
    private val repository: MainRepository,
) : ViewModel() {

    private var _createAdminRequest: CreateAdminRequest = CreateAdminRequest()
    private var _loginAdminRequest: LoginAdminRequest = LoginAdminRequest()

    val createAdminRequest get() = _createAdminRequest
    val loginAdminRequest get() = _loginAdminRequest


    fun createAdmin(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            repository.createAdmin(_createAdminRequest, onResult)
        }
    }


    fun setEmailForLogin(email: String) {
        this._loginAdminRequest.AdminEmail = email
    }


    fun setPasswordForLogin(password: String) {
        this._loginAdminRequest.Password = password
    }


    fun createAccount(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            repository.createAccount(_loginAdminRequest, onResult)
        }
    }

    fun login(onResult: (Boolean) -> Unit) {
        viewModelScope.launch {
            repository.login(_loginAdminRequest, onResult)
        }
    }

    fun logout() {
        viewModelScope.launch { repository.logout() }
    }

    fun setLoginAdminRequest(request: LoginAdminRequest) {
        this._loginAdminRequest = request
    }
}

@Suppress("UNCHECKED_CAST")
class AuthViewModelFactory(
    private val repository: MainRepository,
) : ViewModelProvider.Factory {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return if (modelClass.isAssignableFrom(AuthViewModel::class.java)) {
            AuthViewModel(repository) as T
        } else throw IllegalArgumentException("Unknown ViewModel class")
    }
}

