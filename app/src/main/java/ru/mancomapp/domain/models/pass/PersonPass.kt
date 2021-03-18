package ru.mancomapp.domain.models.pass

class PersonPass {
    var personName: String = ""
    var passDate: PassDate = PassDate()
    var accessType: PersonPassAccessType = PersonPassAccessType.NOT_SELECTED

    fun isEmpty(): Boolean {
        return personName.isEmpty()
                || passDate.isEmpty()
                || accessType == PersonPassAccessType.NOT_SELECTED
    }
}