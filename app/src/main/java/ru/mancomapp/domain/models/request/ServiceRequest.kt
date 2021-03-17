package ru.mancomapp.domain.models.request

import ru.mancomapp.domain.models.service.ServiceType

class ServiceRequest : Request() {
    var type: ServiceType = ServiceType.OTHER
    var comment: String = ""
}