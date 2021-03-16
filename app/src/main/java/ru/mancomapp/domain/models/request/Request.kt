package ru.mancomapp.domain.models.request

class Request {
    var id: Int = 0
    var title: String = ""
    var content: String = ""
    var status: RequestStatus = RequestStatus.NEW
}