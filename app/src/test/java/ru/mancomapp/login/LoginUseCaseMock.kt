package ru.mancomapp.login

import ru.mancomapp.domain.models.LoginCredentials
import ru.mancomapp.domain.usecase.login.LoginCredentialsEmptyException
import ru.mancomapp.domain.usecase.login.LoginUseCase
import ru.mancomapp.domain.usecase.login.PrivacyPolicyNotConfirmedException

class LoginUseCaseMock : LoginUseCase() {

    var isCredentialsEmpty = false
    var isPrivacyPolicyNotConfirmed = false

    override suspend fun login(loginCredentials: LoginCredentials) {
        if (isCredentialsEmpty) throw LoginCredentialsEmptyException()
        if (isPrivacyPolicyNotConfirmed) throw PrivacyPolicyNotConfirmedException()
    }
}