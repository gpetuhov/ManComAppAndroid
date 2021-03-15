package ru.mancomapp.data.repository

import kotlinx.coroutines.delay
import ru.mancomapp.domain.models.LoginCredentials

class LoginRepository {

    @Throws(Exception::class)
    suspend fun login(loginCredentials: LoginCredentials) {
        // TODO
        delay(5000)

        // TODO: handle login error and no network (server unavailable)
    }
}