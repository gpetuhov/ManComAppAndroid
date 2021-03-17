package ru.mancomapp.domain.models.request

open class Request {
    var id: Int = 0
    var status: RequestStatus = RequestStatus.NEW
}