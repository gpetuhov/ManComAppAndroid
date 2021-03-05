package ru.mancomapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class LoginViewModel : ViewModel() {

    var isLoginEnabledLiveData: LiveData<Boolean>
    var isLoginStarted: LiveData<Boolean>
    var isLoginSuccess: LiveData<Boolean>
    var isLoginError: LiveData<String>

    private val isLoginEnabledLiveDataMutable = MutableLiveData<Boolean>()
    private val isLoginStartedLiveDataMutable = MutableLiveData<Boolean>()
    private val isLoginSuccessLiveDataMutable = MutableLiveData<Boolean>()
    private val isLoginErrorLiveDataMutable = MutableLiveData<String>()

    private var login = ""
    private var password = ""
    private var isPrivacyPolicyConfirmed = false

    init {
        isLoginEnabledLiveData = isLoginEnabledLiveDataMutable
        isLoginStarted = isLoginStartedLiveDataMutable
        isLoginSuccess = isLoginSuccessLiveDataMutable
        isLoginError = isLoginErrorLiveDataMutable

        isLoginStartedLiveDataMutable.postValue(false)
        updateLoginButton()
    }

    fun login() {
        isLoginStartedLiveDataMutable.postValue(true)
    }

    override fun onCleared() {
        super.onCleared()

        // TODO: cancel login request here
    }

    fun onLoginChanged(login: String) {
        this.login = login
        updateLoginButton()
    }

    fun onPasswordChanged(password: String) {
        this.password = password
        updateLoginButton()
    }

    fun onPrivacyPolicyConfirmChanged(isConfirmed: Boolean) {
        isPrivacyPolicyConfirmed = isConfirmed
        updateLoginButton()
    }

    private fun updateLoginButton() {
        val isEnabled = login.isNotEmpty() && password.isNotEmpty() && isPrivacyPolicyConfirmed
        isLoginEnabledLiveDataMutable.postValue(isEnabled)
    }
}