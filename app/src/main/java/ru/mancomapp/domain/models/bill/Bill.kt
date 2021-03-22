package ru.mancomapp.domain.models.bill

import ru.mancomapp.domain.models.request.RequestDate

class Bill {
    var id: String = ""
    var date: RequestDate = RequestDate()
    var title: String = ""
    var total: Int = 0
    var status: BillStatus = BillStatus.NOT_PAID
    var fileName: String = ""
    var fileUrl: String = ""
}