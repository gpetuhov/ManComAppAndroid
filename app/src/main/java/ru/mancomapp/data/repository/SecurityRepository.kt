package ru.mancomapp.data.repository

import kotlinx.coroutines.delay
import ru.mancomapp.domain.models.pass.CarPassAccessType
import ru.mancomapp.domain.models.pass.PersonPassAccessType
import ru.mancomapp.domain.models.request.Request
import ru.mancomapp.domain.models.request.RequestStatus

class SecurityRepository {

    @Throws(Exception::class)
    suspend fun getRequests(): List<Request> {
        // TODO: implement
        // TODO: handle errors
        delay(5000)
        return getDummyRequests()
    }

    // TODO: remove this
    private fun getDummyRequests(): List<Request> {
        val requests = mutableListOf<Request>()

        (1..100).forEach { index ->
            val request: Request

            if (index % 2 == 0) {
                request = Request.PersonPass().apply {
                    id = index
                    personName = "Иванов Иван Иванович"
                    accessType = PersonPassAccessType.APARTMENT
                    status = RequestStatus.NEW
                }
            } else {
                request = Request.CarPass().apply {
                    id = index
                    carModel = "Мерседес"
                    carNumber = "X000XX000"
                    accessType = CarPassAccessType.GUEST_PARKING
                    status = RequestStatus.ON_REVIEW
                }
            }

            requests.add(request)
        }

        return requests
    }
}