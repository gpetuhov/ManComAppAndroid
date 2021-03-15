package ru.mancomapp.domain.usecase.login

import kotlinx.coroutines.delay
import ru.mancomapp.domain.models.LoginCredentials

open class LoginUseCase {

    @Throws(
        LoginCredentialsEmptyException::class,
        PrivacyPolicyNotConfirmedException::class
    )
    open suspend fun login(loginCredentials: LoginCredentials) {
        if (loginCredentials.isEmpty()) throw LoginCredentialsEmptyException()
        if (!loginCredentials.isPrivacyPolicyConfirmed) throw PrivacyPolicyNotConfirmedException()

        // TODO
        delay(5000)

        // TODO: handle login error and no network (server unavailable)
    }
}