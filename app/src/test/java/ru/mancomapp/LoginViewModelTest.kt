package ru.mancomapp

import org.junit.Before
import org.junit.Test
import ru.mancomapp.ui.login.LoginViewModel

class LoginViewModelTest {

    private lateinit var viewModel: LoginViewModel
    private lateinit var loginCredentials: LoginViewModel.LoginCredentials

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