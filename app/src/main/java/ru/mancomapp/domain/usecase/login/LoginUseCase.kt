package ru.mancomapp.domain.usecase.login

import ru.mancomapp.domain.models.LoginCredentials

class LoginUseCase {

    @Throws(
        LoginCredentialsEmptyException::class,
        PrivacyPolicyNotConfirmedException::class
    )
    suspend fun login(loginCredentials: LoginCredentials) {
        if (loginCredentials.isEmpty()) throw LoginCredentialsEmptyException()
        if (!loginCredentials.isPrivacyPolicyConfirmed) throw PrivacyPolicyNotConfirmedException()

        // TODO
    }
}