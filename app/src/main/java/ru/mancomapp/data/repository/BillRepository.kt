package ru.mancomapp.data.repository

import kotlinx.coroutines.delay
import ru.mancomapp.domain.models.bill.Bill
import ru.mancomapp.domain.models.bill.BillStatus
import ru.mancomapp.domain.models.request.RequestDate

class BillRepository {

    // TODO: remove this
    companion object {
        const val YEAR = 2021
        const val MONTH = 2
        const val DAY = 18
        const val TIME_IN_MILLIS = 1616056739495
    }

    @Throws(Exception::class)
    suspend fun getBills(): List<Bill> {
        // TODO: implement
        // TODO: handle errors
        delay(5000)
        return getDummyBills()
    }

    // TODO: remove this
    private fun getDummyBills(): List<Bill> {
        val bills = mutableListOf<Bill>()

        (1..100).forEach { index ->
            val billDate = RequestDate().apply {
                year = YEAR
                month = MONTH
                day = DAY
                timeInMillis = TIME_IN_MILLIS
            }

            val bill = Bill().apply {
                id = index.toString()
                date = billDate
                title = "Ежемесячный счет за услуги УК"
                total = 1000
                status = if (index % 2 == 0) BillStatus.PAID else BillStatus.NOT_PAID
                fileName = "Dummy.pdf"
                fileUrl = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"
            }

            bills.add(bill)
        }

        return bills
    }
}