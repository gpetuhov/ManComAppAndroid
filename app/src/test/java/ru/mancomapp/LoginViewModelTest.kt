package ru.mancomapp

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import ru.mancomapp.ui.login.LoginViewModel

class LoginViewModelTest {

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
    fun login_emptyLoginEmptyPassword_error() {
        viewModel.login(loginCredentials)
    }
}