package ru.mancomapp.login

import ru.mancomapp.data.repository.LoginRepository
import ru.mancomapp.domain.models.LoginCredentials
import ru.mancomapp.domain.usecase.login.LoginUseCase

class LoginUseCaseMock(loginRepository: LoginRepository) : LoginUseCase(loginRepository) {

    override suspend fun login(
        loginCredentials: LoginCredentials,
        onLoginRequestStarted: suspend () -> Unit
    ) {
        // TODO
        println("Mock login")
    }
}