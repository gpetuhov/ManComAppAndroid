package ru.mancomapp.login

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.mancomapp.App
import ru.mancomapp.R
import ru.mancomapp.di.components.DaggerTestAppComponent
import ru.mancomapp.domain.models.LoginCredentials
import ru.mancomapp.presentation.login.LoginViewModel

class LoginViewModelTest {

    companion object {
        private const val LOGIN = "login"
        private const val PASSWORD = "password"
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var loginCredentials: LoginCredentials

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun initViewModel() {
        App.appComponent = DaggerTestAppComponent.builder().build()

        viewModel = LoginViewModel()
        loginCredentials = LoginCredentials()
    }

    @Test
    fun login_emptyLoginEmptyPassword_errorLoginEmpty() {
        viewModel.login(loginCredentials)
        assertTrue(viewModel.isLoginStarted.value ?: false)
        assertFalse(viewModel.isLoginSuccess.value ?: false)
        assertEquals(R.string.login_input_empty, viewModel.isLoginError.value)
    }

    @Test
    fun login_emptyLogin_errorLoginEmpty() {
        loginCredentials.password = PASSWORD
        viewModel.login(loginCredentials)
        assertFalse(viewModel.isLoginStarted.value ?: false)
        assertFalse(viewModel.isLoginSuccess.value ?: false)
        assertEquals(R.string.login_input_empty, viewModel.isLoginError.value)
    }

    @Test
    fun login_emptyPassword_errorLoginEmpty() {
        loginCredentials.login = LOGIN
        viewModel.login(loginCredentials)
        assertFalse(viewModel.isLoginStarted.value ?: false)
        assertFalse(viewModel.isLoginSuccess.value ?: false)
        assertEquals(R.string.login_input_empty, viewModel.isLoginError.value)
    }

    @Test
    fun login_privacyPolicyNotConfirmed_errorLoginEmpty() {
        loginCredentials.login = LOGIN
        loginCredentials.password = PASSWORD
        viewModel.login(loginCredentials)
        assertFalse(viewModel.isLoginStarted.value ?: false)
        assertFalse(viewModel.isLoginSuccess.value ?: false)
        assertEquals(R.string.privacy_policy_confirm_error, viewModel.isLoginError.value)
    }

    @Test
    fun login_notEmptyLoginNotEmptyPasswordPrivacyPolicyConfirmed_loginStarted() {
        loginCredentials.login = LOGIN
        loginCredentials.password = PASSWORD
        loginCredentials.isPrivacyPolicyConfirmed = true
        viewModel.login(loginCredentials)
        assertTrue(viewModel.isLoginStarted.value ?: false)
    }
}