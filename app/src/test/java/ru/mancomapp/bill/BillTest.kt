package ru.mancomapp.bill

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.mancomapp.domain.models.bill.Bill
import ru.mancomapp.domain.models.bill.BillStatus

class BillTest {

    private lateinit var bill: Bill

    @Before
    fun init() {
        bill = Bill()
    }

    @Test
    fun isNotPaid_notPaid_true() {
        bill.status = BillStatus.NOT_PAID
        assertTrue(bill.isNotPaid())
    }

    @Test
    fun isNotPaid_paid_false() {
        bill.status = BillStatus.PAID
        assertFalse(bill.isNotPaid())
    }

    @Test
    fun isNotPaid_inProgress_false() {
        bill.status = BillStatus.IN_PROGRESS
        assertFalse(bill.isNotPaid())
    }
}