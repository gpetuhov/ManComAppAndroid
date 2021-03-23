package ru.mancomapp.domain.models.request

import ru.mancomapp.domain.models.pass.CarPassAccessType
import ru.mancomapp.domain.models.pass.PersonPassAccessType
import ru.mancomapp.domain.models.service.ServiceType

sealed class Request {
    var id: Int = 0
    var date = RequestDate()
    var status: RequestStatus = RequestStatus.NEW

    class Management : Request() {
        var title: String = ""
        var content: String = ""
    }

    class Service : Request() {
        var type: ServiceType = ServiceType.OTHER
        var comment: String = ""
    }

    class PersonPass : Request() {
        var personName: String = ""
        var accessType: PersonPassAccessType = PersonPassAccessType.OTHER
    }

    class CarPass : Request() {
        var carModel: String = ""
        var carNumber: String = ""
        var accessType: CarPassAccessType = CarPassAccessType.OTHER
    }
}