package ru.mancomapp.domain.usecase.security

import ru.mancomapp.data.repository.SecurityRepository

class SecurityUseCase(private val securityRepository: SecurityRepository) {

    // TODO: handle errors
    @Throws(Exception::class)
    suspend fun getRequests() = securityRepository.getRequests()
}