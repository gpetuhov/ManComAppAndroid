package ru.mancomapp.pass

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.mancomapp.domain.models.pass.PersonPass
import ru.mancomapp.domain.models.pass.PersonPassAccessType
import ru.mancomapp.testdata.RequestTestData

class PersonPassTest {

    companion object {
        private const val NAME = "Name"
        private val PASS_DATE = RequestTestData.getRequestDate()
    }

    private lateinit var personPass: PersonPass

    @Before
    fun init() {
        personPass = PersonPass()
    }

    @Test
    fun isEmpty_allEmpty_true() {
        assertTrue(personPass.isEmpty())
    }

    @Test
    fun isEmpty_emptyName_true() {
        personPass.requestDate = PASS_DATE
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
        personPass.requestDate = PASS_DATE
        assertTrue(personPass.isEmpty())
    }

    @Test
    fun isEmpty_notEmpty_false() {
        personPass.personName = NAME
        personPass.requestDate = PASS_DATE
        personPass.accessType = PersonPassAccessType.OTHER
        assertFalse(personPass.isEmpty())
    }
}