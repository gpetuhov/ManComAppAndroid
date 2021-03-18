package ru.mancomapp.domain.models.pass

class PersonPass {
    var personName: String = ""
    val passDate: PassDate = PassDate()
    var accessType: PersonPassAccessType = PersonPassAccessType.NOT_SELECTED
}