package ru.mancomapp.domain.usecase

import kotlinx.coroutines.delay
import ru.mancomapp.domain.models.LoginCredentials

class LoginUseCase {

    @Throws(Exception::class)
    suspend fun login(loginCredentials: LoginCredentials) {
        // TODO
        delay(5000)

        // TODO: handle login error and no network (server unavailable)
    }
}