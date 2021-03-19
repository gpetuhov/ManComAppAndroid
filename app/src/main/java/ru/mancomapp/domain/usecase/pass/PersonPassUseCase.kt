package ru.mancomapp.domain.usecase.pass

import ru.mancomapp.data.repository.PersonPassRepository
import ru.mancomapp.domain.models.pass.PersonPass
import ru.mancomapp.domain.models.pass.PersonPassAccessType

class PersonPassUseCase(private val personPassRepository: PersonPassRepository) {

    @Throws(
        PersonNameEmptyException::class,
        PassDateEmptyException::class,
        AccessTypeNotSelectedException::class,
        Exception::class
    )
    suspend fun sendRequest(
        personPass: PersonPass,
        onSendStarted: suspend () -> Unit
    ) {
        if (personPass.personName.isEmpty()) throw PersonNameEmptyException()
        if (personPass.requestDate.isEmpty()) throw PassDateEmptyException()
        if (personPass.accessType == PersonPassAccessType.NOT_SELECTED) throw AccessTypeNotSelectedException()

        onSendStarted()
        personPassRepository.sendRequest(personPass)

        // TODO: handle send error and no network (server unavailable)
    }
}