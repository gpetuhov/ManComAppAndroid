package ru.mancomapp.domain.models.request

class CarPassRequest : Request() {
    var carModel: String = ""
    var carNumber: String = ""
    var accessType: CarPassAccessType = CarPassAccessType.OTHER
}