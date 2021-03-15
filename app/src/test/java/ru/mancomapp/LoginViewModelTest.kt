package ru.mancomapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.mancomapp.ui.login.LoginViewModel

class LoginViewModelTest {

    companion object {
        private const val LOGIN = "login"
        private const val PASSWORD = "password"
    }

    private lateinit var viewModel: LoginViewModel
    private lateinit var loginCredentials: LoginViewModel.LoginCredentials

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @Before
    fun initViewModel() {
        viewModel = LoginViewModel()
        loginCredentials = LoginViewModel.LoginCredentials()
    }

    @Test
    fun login_emptyLoginEmptyPassword_errorLoginEmpty() {
        viewModel.login(loginCredentials)
        assertFalse(viewModel.isLoginStarted.value ?: false)
        assertFalse(viewModel.isLoginSuccess.value ?: false)
        assertEquals(viewModel.isLoginError.value, R.string.login_input_empty)
    }

    @Test
    fun login_emptyLogin_errorLoginEmpty() {
        loginCredentials.password = PASSWORD
        viewModel.login(loginCredentials)
        assertFalse(viewModel.isLoginStarted.value ?: false)
        assertFalse(viewModel.isLoginSuccess.value ?: false)
        assertEquals(viewModel.isLoginError.value, R.string.login_input_empty)
    }

    @Test
    fun login_emptyPassword_errorLoginEmpty() {
        loginCredentials.login = LOGIN
        viewModel.login(loginCredentials)
        assertFalse(viewModel.isLoginStarted.value ?: false)
        assertFalse(viewModel.isLoginSuccess.value ?: false)
        assertEquals(viewModel.isLoginError.value, R.string.login_input_empty)
    }

    @Test
    fun login_privacyPolicyNotConfirmed_errorLoginEmpty() {
        loginCredentials.login = LOGIN
        loginCredentials.password = PASSWORD
        viewModel.login(loginCredentials)
        assertFalse(viewModel.isLoginStarted.value ?: false)
        assertFalse(viewModel.isLoginSuccess.value ?: false)
        assertEquals(viewModel.isLoginError.value, R.string.privacy_policy_confirm_error)
    }
}