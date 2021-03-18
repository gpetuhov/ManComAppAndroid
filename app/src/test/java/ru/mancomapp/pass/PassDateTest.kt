package ru.mancomapp.pass

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.mancomapp.domain.models.pass.PassDate

class PassDateTest {

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
        passDate.month = PersonPassTestData.MONTH
        passDate.day = PersonPassTestData.DAY
        passDate.timeInMillis = PersonPassTestData.TIME_IN_MILLIS
        assertTrue(passDate.isEmpty())
    }

    @Test
    fun isEmpty_emptyMonth_true() {
        passDate.year = PersonPassTestData.YEAR
        passDate.day = PersonPassTestData.DAY
        passDate.timeInMillis = PersonPassTestData.TIME_IN_MILLIS
        assertTrue(passDate.isEmpty())
    }

    @Test
    fun isEmpty_emptyDay_true() {
        passDate.year = PersonPassTestData.YEAR
        passDate.month = PersonPassTestData.MONTH
        passDate.timeInMillis = PersonPassTestData.TIME_IN_MILLIS
        assertTrue(passDate.isEmpty())
    }

    @Test
    fun isEmpty_emptyTimeInMillis_true() {
        passDate.year = PersonPassTestData.YEAR
        passDate.month = PersonPassTestData.MONTH
        passDate.day = PersonPassTestData.DAY
        assertTrue(passDate.isEmpty())
    }

    @Test
    fun isEmpty_notEmpty_false() {
        passDate = PersonPassTestData.getPassDate()
        assertFalse(passDate.isEmpty())
    }
}