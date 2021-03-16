package ru.mancomapp.domain.usecase.login

import ru.mancomapp.data.repository.LoginRepository
import ru.mancomapp.domain.models.LoginCredentials

class LoginUseCase(private val loginRepository: LoginRepository) {

    @Throws(
        LoginCredentialsEmptyException::class,
        PrivacyPolicyNotConfirmedException::class
    )
    suspend fun login(
        loginCredentials: LoginCredentials,
        onLoginRequestStarted: suspend () -> Unit
    ) {
        if (loginCredentials.isEmpty()) throw LoginCredentialsEmptyException()
        if (!loginCredentials.isPrivacyPolicyConfirmed) throw PrivacyPolicyNotConfirmedException()

        onLoginRequestStarted()
        loginRepository.login(loginCredentials)

        // TODO: handle login error and no network (server unavailable)
    }
}