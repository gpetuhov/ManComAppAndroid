package ru.mancomapp.domain.usecase.service

import ru.mancomapp.data.repository.ServiceRepository
import ru.mancomapp.domain.models.service.Service
import ru.mancomapp.domain.models.service.ServiceType

class ServiceUseCase(private val serviceRepository: ServiceRepository) {

    @Throws(
        ServiceTypeNotSelectedException::class,
        ServiceCommentEmptyException::class,
        Exception::class
    )
    suspend fun sendRequest(
        service: Service,
        onSendStarted: suspend () -> Unit
    ) {
        if (service.type == ServiceType.NOT_SELECTED) throw ServiceTypeNotSelectedException()
        if (service.comment.isEmpty()) throw ServiceCommentEmptyException()

        onSendStarted()
        serviceRepository.sendRequest(service)

        // TODO: handle login error and no network (server unavailable)
    }
}