package ru.mancomapp.domain.usecase

import ru.mancomapp.data.repository.LoginRepository
import ru.mancomapp.domain.models.LoginCredentials

class LoginUseCase(private val loginRepository: LoginRepository) {

    @Throws(Exception::class)
    suspend fun login(loginCredentials: LoginCredentials) =
        loginRepository.login(loginCredentials)
}