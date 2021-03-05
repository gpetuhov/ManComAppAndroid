package ru.mancomapp.ui.login

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

        isLoginStartedLiveDataMutable.postValue(false)
    }

    override fun onCleared() {
        super.onCleared()
        loginJob?.cancel()
    }

    fun login(loginCredentials: LoginCredentials) {
        if (loginCredentials.isEmpty()) {
            val errorMessage = App.application.getString(R.string.login_input_empty)
            isLoginErrorLiveDataMutable.postValue(errorMessage)
            return
        }

        if (!loginCredentials.isPrivacyPolicyConfirmed) {
            val errorMessage = App.application.getString(R.string.privacy_policy_confirm_error)
            isLoginErrorLiveDataMutable.postValue(errorMessage)
            return
        }

        isLoginStartedLiveDataMutable.postValue(true)

        loginJob = viewModelScope.launch(Dispatchers.IO) {
            // TODO: implement
            delay(5000)

            val isSuccess = true

            withContext(Dispatchers.Main) {
                isLoginStartedLiveDataMutable.postValue(false)
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