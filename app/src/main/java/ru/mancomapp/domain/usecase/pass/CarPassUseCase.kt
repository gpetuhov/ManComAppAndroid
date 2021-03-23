package ru.mancomapp.domain.usecase.pass

import ru.mancomapp.data.repository.CarPassRepository
import ru.mancomapp.domain.models.pass.CarPass
import ru.mancomapp.domain.models.pass.CarPassAccessType

class CarPassUseCase(private val carPassRepository: CarPassRepository) {

    @Throws(
        CarModelEmptyException::class,
        CarNumberEmptyException::class,
        PassDateEmptyException::class,
        AccessTypeNotSelectedException::class,
        Exception::class
    )
    suspend fun sendRequest(
        carPass: CarPass,
        onSendStarted: suspend () -> Unit
    ) {
        if (carPass.carModel.isEmpty()) throw CarModelEmptyException()
        if (carPass.carNumber.isEmpty()) throw CarNumberEmptyException()
        if (carPass.requestDate.isEmpty()) throw PassDateEmptyException()
        if (carPass.accessType == CarPassAccessType.NOT_SELECTED) throw AccessTypeNotSelectedException()

        onSendStarted()
        carPassRepository.sendRequest(carPass)

        // TODO: handle send error and no network (server unavailable)
    }
}