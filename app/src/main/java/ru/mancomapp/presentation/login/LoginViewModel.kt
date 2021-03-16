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
import ru.mancomapp.domain.usecase.login.LoginCredentialsEmptyException
import ru.mancomapp.domain.usecase.login.LoginUseCase
import ru.mancomapp.domain.usecase.login.PrivacyPolicyNotConfirmedException
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
        loginJob = viewModelScope.launch(Dispatchers.IO) {
            try {
                loginUseCase.login(loginCredentials) { postLoginStarted() }
                postLoginSuccess()
            } catch (e: LoginCredentialsEmptyException) {
                postLoginError(R.string.login_input_empty)
            } catch (e: PrivacyPolicyNotConfirmedException) {
                postLoginError(R.string.privacy_policy_confirm_error)
            } catch (e: Exception) {
                // TODO: handle login error and no network (server unavailable)
            }
        }
    }

    private suspend fun postLoginStarted() {
        withContext(Dispatchers.Main) {
            isLoginStartedLiveDataMutable.postValue(true)
        }
    }

    private suspend fun postLoginSuccess() {
        withContext(Dispatchers.Main) {
            isLoginSuccessLiveDataMutable.postValue(true)
        }
    }

    private suspend fun postLoginError(@StringRes errorMessageId: Int) {
        withContext(Dispatchers.Main) {
            isLoginStartedLiveDataMutable.postValue(false)
            isLoginSuccessLiveDataMutable.postValue(false)
            isLoginErrorLiveDataMutable.postValue(errorMessageId)
        }
    }
}