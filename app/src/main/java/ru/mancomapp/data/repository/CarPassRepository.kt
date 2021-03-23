package ru.mancomapp.data.repository

import kotlinx.coroutines.delay
import ru.mancomapp.domain.models.pass.CarPass

class CarPassRepository {

    @Throws(Exception::class)
    suspend fun sendRequest(carPass: CarPass) {
        // TODO
        delay(5000)

        // TODO: handle send error and no network (server unavailable)
    }
}