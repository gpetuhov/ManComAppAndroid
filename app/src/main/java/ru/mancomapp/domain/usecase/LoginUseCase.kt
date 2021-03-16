package ru.mancomapp.domain.usecase

import ru.mancomapp.data.repository.LoginRepository
import ru.mancomapp.domain.models.LoginCredentials

open class LoginUseCase(private val loginRepository: LoginRepository) {

    @Throws(Exception::class)
    open suspend fun login(loginCredentials: LoginCredentials) =
        loginRepository.login(loginCredentials)
}