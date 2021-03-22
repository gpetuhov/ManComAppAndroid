package ru.mancomapp.requests

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.mancomapp.domain.models.request.RequestDate
import ru.mancomapp.testdata.RequestTestData

class RequestDateTest {

    companion object {
        const val YEAR = 2022
        const val MONTH = 3
        const val DAY = 22
        const val TIME_IN_MILLIS = 1616419296000
    }

    private lateinit var requestDate: RequestDate

    @Before
    fun initFeedback() {
        requestDate = RequestDate()
    }

    @Test
    fun isEmpty_allEmpty_true() {
        assertTrue(requestDate.isEmpty())
    }

    @Test
    fun isEmpty_emptyYear_true() {
        requestDate.month = RequestTestData.MONTH
        requestDate.day = RequestTestData.DAY
        requestDate.timeInMillis = RequestTestData.TIME_IN_MILLIS
        assertTrue(requestDate.isEmpty())
    }

    @Test
    fun isEmpty_emptyMonth_true() {
        requestDate.year = RequestTestData.YEAR
        requestDate.day = RequestTestData.DAY
        requestDate.timeInMillis = RequestTestData.TIME_IN_MILLIS
        assertTrue(requestDate.isEmpty())
    }

    @Test
    fun isEmpty_emptyDay_true() {
        requestDate.year = RequestTestData.YEAR
        requestDate.month = RequestTestData.MONTH
        requestDate.timeInMillis = RequestTestData.TIME_IN_MILLIS
        assertTrue(requestDate.isEmpty())
    }

    @Test
    fun isEmpty_emptyTimeInMillis_true() {
        requestDate.year = RequestTestData.YEAR
        requestDate.month = RequestTestData.MONTH
        requestDate.day = RequestTestData.DAY
        assertTrue(requestDate.isEmpty())
    }

    @Test
    fun isEmpty_notEmpty_false() {
        requestDate = RequestTestData.getRequestDate()
        assertFalse(requestDate.isEmpty())
    }

    @Test
    fun equals_notEqualYear_false() {
        requestDate = RequestTestData.getRequestDate()
        val otherRequestDate = RequestTestData.getRequestDate()
        otherRequestDate.year = YEAR
        assertFalse(requestDate.equals(otherRequestDate))
    }

    @Test
    fun equals_notEqualMonth_false() {
        requestDate = RequestTestData.getRequestDate()
        val otherRequestDate = RequestTestData.getRequestDate()
        otherRequestDate.month = MONTH
        assertFalse(requestDate.equals(otherRequestDate))
    }

    @Test
    fun equals_notEqualDay_false() {
        requestDate = RequestTestData.getRequestDate()
        val otherRequestDate = RequestTestData.getRequestDate()
        otherRequestDate.day = DAY
        assertFalse(requestDate.equals(otherRequestDate))
    }

    @Test
    fun equals_notEqualTimeInMillis_false() {
        requestDate = RequestTestData.getRequestDate()
        val otherRequestDate = RequestTestData.getRequestDate()
        otherRequestDate.timeInMillis = TIME_IN_MILLIS
        assertFalse(requestDate.equals(otherRequestDate))
    }

    @Test
    fun equals_notEqualAll_false() {
        requestDate = RequestTestData.getRequestDate()
        val otherRequestDate = getOtherRequestDate()
        assertFalse(requestDate.equals(otherRequestDate))
    }

    @Test
    fun equals_equalAll_true() {
        requestDate = RequestTestData.getRequestDate()
        val otherRequestDate = RequestTestData.getRequestDate()
        assertTrue(requestDate.equals(otherRequestDate))
    }
    
    private fun getOtherRequestDate(): RequestDate {
        return RequestDate().apply {
            year = YEAR
            month = MONTH
            day = DAY
            timeInMillis = TIME_IN_MILLIS
        }
    }
}