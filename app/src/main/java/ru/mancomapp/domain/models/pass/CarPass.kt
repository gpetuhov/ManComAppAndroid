package ru.mancomapp.domain.models.pass

import ru.mancomapp.domain.models.request.RequestDate

class CarPass {
    var carModel: String = ""
    var carNumber: String = ""
    var requestDate: RequestDate = RequestDate()
    var accessType: CarPassAccessType = CarPassAccessType.NOT_SELECTED

    fun isEmpty(): Boolean {
        return carModel.isEmpty()
                || carNumber.isEmpty()
                || requestDate.isEmpty()
                || accessType == CarPassAccessType.NOT_SELECTED
    }
}