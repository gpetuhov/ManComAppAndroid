package ru.mancomapp.pass

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.mancomapp.domain.models.pass.PassDate
import ru.mancomapp.domain.models.pass.PersonPass
import ru.mancomapp.domain.models.pass.PersonPassAccessType

class PersonPassTest {

    companion object {
        private const val NAME = "Name"
        private const val YEAR = 2021
        private const val MONTH = 3
        private const val DAY = 18
        private const val TIME_IN_MILLIS = 1616056739495
    }

    private lateinit var personPass: PersonPass
    private lateinit var passDate: PassDate

    @Before
    fun initFeedback() {
        personPass = PersonPass()
        initPassDate()
    }

    @Test
    fun isEmpty_allEmpty_true() {
        assertTrue(personPass.isEmpty())
    }

    @Test
    fun isEmpty_emptyName_true() {
        personPass.passDate = passDate
        personPass.accessType = PersonPassAccessType.OTHER
        assertTrue(personPass.isEmpty())
    }

    @Test
    fun isEmpty_emptyDate_true() {
        personPass.personName = NAME
        personPass.accessType = PersonPassAccessType.OTHER
        assertTrue(personPass.isEmpty())
    }

    @Test
    fun isEmpty_accessTypeNotSelected_true() {
        personPass.personName = NAME
        personPass.passDate = passDate
        assertTrue(personPass.isEmpty())
    }

    @Test
    fun isEmpty_notEmpty_false() {
        personPass.personName = NAME
        personPass.passDate = passDate
        personPass.accessType = PersonPassAccessType.OTHER
        assertFalse(personPass.isEmpty())
    }

    private fun initPassDate() {
        passDate = PassDate()
        passDate.year = YEAR
        passDate.month = MONTH
        passDate.day = DAY
        passDate.timeInMillis = TIME_IN_MILLIS
    }
}