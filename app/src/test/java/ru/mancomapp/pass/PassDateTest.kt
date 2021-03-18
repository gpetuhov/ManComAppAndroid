package ru.mancomapp.pass

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.mancomapp.domain.models.pass.PassDate

class FeedbackTest {

    companion object {
        private const val YEAR = 2021
        private const val MONTH = 3
        private const val DAY = 18
        private const val TIME_IN_MILLIS = 1616056739495
    }

    private lateinit var passDate: PassDate

    @Before
    fun initFeedback() {
        passDate = PassDate()
    }

    @Test
    fun isEmpty_allEmpty_true() {
        assertTrue(passDate.isEmpty())
    }

    @Test
    fun isEmpty_emptyYear_true() {
        passDate.month = MONTH
        passDate.day = DAY
        passDate.timeInMillis = TIME_IN_MILLIS
        assertTrue(passDate.isEmpty())
    }

    @Test
    fun isEmpty_emptyMonth_true() {
        passDate.year = YEAR
        passDate.day = DAY
        passDate.timeInMillis = TIME_IN_MILLIS
        assertTrue(passDate.isEmpty())
    }

    @Test
    fun isEmpty_emptyDay_true() {
        passDate.year = YEAR
        passDate.month = MONTH
        passDate.timeInMillis = TIME_IN_MILLIS
        assertTrue(passDate.isEmpty())
    }

    @Test
    fun isEmpty_emptyTimeInMillis_true() {
        passDate.year = YEAR
        passDate.month = MONTH
        passDate.day = DAY
        assertTrue(passDate.isEmpty())
    }

    @Test
    fun isEmpty_notEmpty_false() {
        passDate.year = YEAR
        passDate.month = MONTH
        passDate.day = DAY
        passDate.timeInMillis = TIME_IN_MILLIS
        assertFalse(passDate.isEmpty())
    }
}