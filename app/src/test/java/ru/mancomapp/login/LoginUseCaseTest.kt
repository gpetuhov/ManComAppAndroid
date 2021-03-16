package ru.mancomapp.login

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.mancomapp.di.components.DaggerTestAppComponent
import ru.mancomapp.domain.models.LoginCredentials
import ru.mancomapp.domain.usecase.login.LoginCredentialsEmptyException
import ru.mancomapp.domain.usecase.login.LoginUseCase
import ru.mancomapp.domain.usecase.login.PrivacyPolicyNotConfirmedException
import javax.inject.Inject

class LoginUseCaseTest {

    companion object {
        private const val LOGIN = "login"
        private const val PASSWORD = "password"
    }

    @Inject lateinit var loginUseCase: LoginUseCase

    private lateinit var loginCredentials: LoginCredentials

    @Before
    fun initLoginCredentials() {
        val appComponent = DaggerTestAppComponent.builder().build()
        appComponent.inject(this)

        loginCredentials = LoginCredentials()
    }

    @Test(expected = LoginCredentialsEmptyException::class)
    fun login_emptyLoginCredentials_throwsException() {
        runBlocking {
            loginUseCase.login(loginCredentials) { /* Do nothing */ }
        }
    }

    @Test(expected = PrivacyPolicyNotConfirmedException::class)
    fun login_privacyPolicyNotConfirmed_throwsException() {
        runBlocking {
            loginCredentials.login = LOGIN
            loginCredentials.password = PASSWORD
            loginUseCase.login(loginCredentials) { /* Do nothing */ }
        }
    }

    @Test
    fun login_validCredentials_loginRequestStarted() {
        runBlocking {
            loginCredentials.login = LOGIN
            loginCredentials.password = PASSWORD
            loginCredentials.isPrivacyPolicyConfirmed = true

            var isLoginRequestStarted = false
            loginUseCase.login(loginCredentials) { isLoginRequestStarted = true }
            assertTrue(isLoginRequestStarted)
        }
    }
}