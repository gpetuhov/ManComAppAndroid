package ru.mancomapp.login

import ru.mancomapp.domain.models.LoginCredentials
import ru.mancomapp.domain.usecase.LoginUseCase

class LoginUseCaseMock : LoginUseCase() {

    var isCredentialsEmpty = false
    var isPrivacyPolicyNotConfirmed = false

    override suspend fun login(loginCredentials: LoginCredentials) {
    }
}