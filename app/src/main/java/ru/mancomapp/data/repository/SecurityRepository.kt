package ru.mancomapp.data.repository

import kotlinx.coroutines.delay
import ru.mancomapp.domain.models.pass.CarPassAccessType
import ru.mancomapp.domain.models.pass.PersonPassAccessType
import ru.mancomapp.domain.models.request.Request
import ru.mancomapp.domain.models.request.RequestDate
import ru.mancomapp.domain.models.request.RequestStatus

class SecurityRepository {

    // TODO: remove this
    companion object {
        const val YEAR = 2021
        const val MONTH = 2
        const val DAY = 18
        const val TIME_IN_MILLIS = 1616056739495
    }

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

        val requestDate = RequestDate().apply {
            year = YEAR
            month = MONTH
            day = DAY
            timeInMillis = TIME_IN_MILLIS
        }

        (1..100).forEach { index ->
            val request: Request

            if (index % 2 == 0) {
                request = Request.PersonPass().apply {
                    id = index
                    date = requestDate
                    personName = "Иванов Иван Иванович"
                    accessType = PersonPassAccessType.ONE_TIME
                    status = RequestStatus.NEW
                }
            } else {
                request = Request.CarPass().apply {
                    id = index
                    date = requestDate
                    carModel = "Мерседес"
                    carNumber = "X000XX000"
                    accessType = CarPassAccessType.DAY
                    status = RequestStatus.ON_REVIEW
                }
            }

            requests.add(request)
        }

        return requests
    }
}