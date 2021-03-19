package ru.mancomapp.requests

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.mancomapp.domain.models.request.RequestDate
import ru.mancomapp.testdata.RequestTestData

class RequestDateTest {

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
}