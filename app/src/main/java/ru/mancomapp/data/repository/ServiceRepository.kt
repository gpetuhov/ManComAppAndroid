package ru.mancomapp.data.repository

import kotlinx.coroutines.delay
import ru.mancomapp.domain.models.service.Service

class ServiceRepository {

    @Throws(Exception::class)
    suspend fun sendRequest(service: Service) {
        // TODO
        delay(5000)

        // TODO: handle login error and no network (server unavailable)
    }
}