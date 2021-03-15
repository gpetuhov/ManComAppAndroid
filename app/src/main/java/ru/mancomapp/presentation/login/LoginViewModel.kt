package ru.mancomapp.presentation.login

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.mancomapp.App
import ru.mancomapp.R
import ru.mancomapp.domain.models.LoginCredentials
import ru.mancomapp.domain.usecase.LoginUseCase
import javax.inject.Inject

class LoginViewModel : ViewModel() {

    @Inject lateinit var loginUseCase: LoginUseCase

    var isLoginStarted: LiveData<Boolean>
    var isLoginSuccess: LiveData<Boolean>
    var isLoginError: LiveData<Int>

    private val isLoginStartedLiveDataMutable = MutableLiveData<Boolean>()
    private val isLoginSuccessLiveDataMutable = MutableLiveData<Boolean>()
    private val isLoginErrorLiveDataMutable = MutableLiveData<Int>()

    private var loginJob: Job? = null

    init {
        App.appComponent.inject(this)

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

    private fun startLogin(loginCredentials: LoginCredentials) {
        isLoginStartedLiveDataMutable.postValue(true)

        loginJob = viewModelScope.launch(Dispatchers.IO) {
            try {
                loginUseCase.login(loginCredentials)
                postLoginSuccess()
            } catch (e: Exception) {
                // TODO: handle error
            }
        }
    }

    private suspend fun postLoginSuccess() {
        withContext(Dispatchers.Main) {
            isLoginSuccessLiveDataMutable.postValue(true)
        }
    }

    private fun postLoginError(@StringRes errorMessageId: Int) {
        isLoginStartedLiveDataMutable.postValue(false)
        isLoginSuccessLiveDataMutable.postValue(false)
        isLoginErrorLiveDataMutable.postValue(errorMessageId)
    }
}