package ru.mancomapp.domain.usecase

import kotlinx.coroutines.delay
import ru.mancomapp.domain.models.LoginCredentials

open class LoginUseCase {

    @Throws(Exception::class)
    open suspend fun login(loginCredentials: LoginCredentials) {
        // TODO
        delay(5000)

        // TODO: handle login error and no network (server unavailable)
    }
}