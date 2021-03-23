package ru.mancomapp.domain.models.pass

import ru.mancomapp.domain.models.request.RequestDate

class PersonPass {
    var personName: String = ""
    var requestDate: RequestDate = RequestDate()
    var accessType: PersonPassAccessType = PersonPassAccessType.NOT_SELECTED

    fun isEmpty(): Boolean {
        return personName.isEmpty()
                || requestDate.isEmpty()
                || accessType == PersonPassAccessType.NOT_SELECTED
    }
}