package ru.mancomapp.data.repository

import kotlinx.coroutines.delay
import ru.mancomapp.domain.models.pass.PersonPass

class PersonPassRepository {

    @Throws(Exception::class)
    suspend fun sendRequest(personPass: PersonPass) {
        // TODO
        delay(5000)

        // TODO: handle send error and no network (server unavailable)
    }
}