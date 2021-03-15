package ru.mancomapp.presentation.login

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.mancomapp.R
import ru.mancomapp.domain.models.LoginCredentials
import ru.mancomapp.domain.usecase.login.LoginCredentialsEmptyException
import ru.mancomapp.domain.usecase.login.LoginUseCase
import ru.mancomapp.domain.usecase.login.PrivacyPolicyNotConfirmedException
import ru.mancomapp.utils.Logger

class LoginViewModel : ViewModel() {

    var isLoginStarted: LiveData<Boolean>
    var isLoginSuccess: LiveData<Boolean>
    var isLoginError: LiveData<Int>

    private val isLoginStartedLiveDataMutable = MutableLiveData<Boolean>()
    private val isLoginSuccessLiveDataMutable = MutableLiveData<Boolean>()
    private val isLoginErrorLiveDataMutable = MutableLiveData<Int>()

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

    private fun postLoginError(@StringRes errorMessageId: Int) =
        isLoginErrorLiveDataMutable.postValue(errorMessageId)

    private fun startLogin(loginCredentials: LoginCredentials) {
        isLoginStartedLiveDataMutable.postValue(true)

        loginJob = viewModelScope.launch(Dispatchers.IO) {
            try {
                LoginUseCase().login(loginCredentials)
            } catch (e: LoginCredentialsEmptyException) {
                // TODO: handle error
                Logger.log("Login", "Credentials empty")
            } catch (e: PrivacyPolicyNotConfirmedException) {
                // TODO: handle error
                Logger.log("Login", "Privacy policy not confirmed")
            }

            // TODO: implement
            delay(5000)

            // TODO: handle login error and no network (server unavailable)
            val isSuccess = true

            withContext(Dispatchers.Main) {
                isLoginSuccessLiveDataMutable.postValue(isSuccess)
            }
        }
    }
}