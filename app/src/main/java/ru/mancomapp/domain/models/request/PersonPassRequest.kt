package ru.mancomapp.domain.models.request

class PersonPassRequest : Request() {
    var personName: String = ""
    var accessType: PersonPassAccessType = PersonPassAccessType.OTHER
}