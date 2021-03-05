package ru.mancomapp.ui.login

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.mancomapp.R
import ru.mancomapp.application.App

class LoginViewModel : ViewModel() {

    var isLoginStarted: LiveData<Boolean>
    var isLoginSuccess: LiveData<Boolean>
    var isLoginError: LiveData<String>

    private val isLoginStartedLiveDataMutable = MutableLiveData<Boolean>()
    private val isLoginSuccessLiveDataMutable = MutableLiveData<Boolean>()
    private val isLoginErrorLiveDataMutable = MutableLiveData<String>()

    private var loginJob: Job? = null

    init {
        isLoginStarted = isLoginStartedLiveDataMutable
        isLoginSuccess = isLoginSuccessLiveDataMutable
        isLoginError = isLoginErrorLiveDataMutable
    }

    override fun onCleared() {
        super.onCleared()
        loginJob?.cancel()
    }

    fun login(loginCredentials: LoginCredentials) {
        if (loginCredentials.isEmpty()) {
            postLoginError(R.string.login_input_empty)
            return
        }

        if (!loginCredentials.isPrivacyPolicyConfirmed) {
            postLoginError(R.string.privacy_policy_confirm_error)
            return
        }

        startLogin(loginCredentials)
    }

    private fun postLoginError(@StringRes errorMessageId: Int) {
        val errorMessage = App.application.getString(errorMessageId)
        isLoginErrorLiveDataMutable.postValue(errorMessage)
    }

    private fun startLogin(loginCredentials: LoginCredentials) {
        isLoginStartedLiveDataMutable.postValue(true)

        loginJob = viewModelScope.launch(Dispatchers.IO) {
            // TODO: implement
            delay(5000)

            // TODO: handle login error and no network (server unavailable)
            val isSuccess = true

            withContext(Dispatchers.Main) {
                isLoginSuccessLiveDataMutable.postValue(isSuccess)
            }
        }
    }

    class LoginCredentials {
        var login: String = ""
        var password: String = ""
        var isPrivacyPolicyConfirmed: Boolean = false

        fun isEmpty() = login.isEmpty() || password.isEmpty()
    }
}