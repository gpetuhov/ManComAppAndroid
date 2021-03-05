package ru.mancomapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var isLoginStarted: LiveData<Boolean>
    var isLoginSuccess: LiveData<Boolean>
    var isLoginError: LiveData<String>

    private val isLoginStartedLiveDataMutable = MutableLiveData<Boolean>()
    private val isLoginSuccessLiveDataMutable = MutableLiveData<Boolean>()
    private val isLoginErrorLiveDataMutable = MutableLiveData<String>()

    init {
        isLoginStarted = isLoginStartedLiveDataMutable
        isLoginSuccess = isLoginSuccessLiveDataMutable
        isLoginError = isLoginErrorLiveDataMutable

        isLoginStartedLiveDataMutable.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()

        // TODO: cancel login request here
    }

    fun login(loginCredentials: LoginCredentials) {
        if (loginCredentials.isEmpty()) {
            // TODO: change this
            isLoginErrorLiveDataMutable.postValue("Empty")
            return
        }

        isLoginStartedLiveDataMutable.postValue(true)
    }

    class LoginCredentials {
        var login: String = ""
        var password: String = ""
        var isPrivacyPolicyConfirmed: Boolean = false

        fun isEmpty() = login.isEmpty() || password.isEmpty()
    }
}