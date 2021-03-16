package ru.mancomapp.domain.usecase.request

import ru.mancomapp.data.repository.RequestRepository

class RequestUseCase(private val requestRepository: RequestRepository) {

    // TODO: handle errors
    @Throws(Exception::class)
    suspend fun getRequests() = requestRepository.getRequests()
}