package ru.mancomapp.pass

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.mancomapp.domain.models.pass.CarPass
import ru.mancomapp.domain.models.pass.CarPassAccessType
import ru.mancomapp.testdata.RequestTestData

class CarPassTest {

    companion object {
        private const val CAR_MODEL = "Model"
        private const val CAR_NUMBER = "Number"
        private val PASS_DATE = RequestTestData.getRequestDate()
    }

    private lateinit var carPass: CarPass

    @Before
    fun init() {
        carPass = CarPass()
    }

    @Test
    fun isEmpty_allEmpty_true() {
        assertTrue(carPass.isEmpty())
    }

    @Test
    fun isEmpty_emptyCarModel_true() {
        carPass.carNumber = CAR_NUMBER
        carPass.requestDate = PASS_DATE
        carPass.accessType = CarPassAccessType.OTHER
        assertTrue(carPass.isEmpty())
    }

    @Test
    fun isEmpty_emptyCarNumber_true() {
        carPass.carModel = CAR_MODEL
        carPass.requestDate = PASS_DATE
        carPass.accessType = CarPassAccessType.OTHER
        assertTrue(carPass.isEmpty())
    }

    @Test
    fun isEmpty_emptyDate_true() {
        carPass.carModel = CAR_MODEL
        carPass.carNumber = CAR_NUMBER
        carPass.accessType = CarPassAccessType.OTHER
        assertTrue(carPass.isEmpty())
    }

    @Test
    fun isEmpty_accessTypeNotSelected_true() {
        carPass.carModel = CAR_MODEL
        carPass.carNumber = CAR_NUMBER
        carPass.requestDate = PASS_DATE
        assertTrue(carPass.isEmpty())
    }

    @Test
    fun isEmpty_notEmpty_false() {
        carPass.carModel = CAR_MODEL
        carPass.carNumber = CAR_NUMBER
        carPass.requestDate = PASS_DATE
        carPass.accessType = CarPassAccessType.OTHER
        assertFalse(carPass.isEmpty())
    }
}