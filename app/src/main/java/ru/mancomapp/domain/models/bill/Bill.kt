package ru.mancomapp.domain.models.bill

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import ru.mancomapp.domain.models.request.RequestDate

@Parcelize
class Bill : Parcelable {
    var id: String = ""
    var date: RequestDate = RequestDate()
    var title: String = ""
    var total: Int = 0
    var status: BillStatus = BillStatus.NOT_PAID
    var fileName: String = ""
    var fileUrl: String = ""

    fun isNotPaid() = status == BillStatus.NOT_PAID
}