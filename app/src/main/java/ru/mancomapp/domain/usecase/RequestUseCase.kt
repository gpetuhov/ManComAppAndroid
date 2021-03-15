package ru.mancomapp.domain.usecase

import ru.mancomapp.data.repository.RequestRepository

class RequestUseCase(private val requestRepository: RequestRepository) {

    // TODO: handle errors
    suspend fun getRequests() = requestRepository.getRequests()
}