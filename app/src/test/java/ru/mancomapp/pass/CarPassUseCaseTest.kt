package ru.mancomapp.pass

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.mancomapp.di.components.DaggerTestAppComponent
import ru.mancomapp.domain.models.pass.CarPass
import ru.mancomapp.domain.models.pass.CarPassAccessType
import ru.mancomapp.domain.usecase.pass.*
import ru.mancomapp.testdata.RequestTestData
import javax.inject.Inject

class CarPassUseCaseTest {

    companion object {
        private const val CAR_MODEL = "Model"
        private const val CAR_NUMBER = "Number"
        private val PASS_DATE = RequestTestData.getRequestDate()
    }

    @Inject lateinit var carPassUseCase: CarPassUseCase

    private lateinit var carPass: CarPass

    @Before
    fun init() {
        val appComponent = DaggerTestAppComponent.builder().build()
        appComponent.inject(this)

        carPass = CarPass()
    }

    @Test(expected = CarModelEmptyException::class)
    fun sendRequest_carModelEmpty_throwsException() {
        runBlocking {
            carPass.carNumber = CAR_NUMBER
            carPass.requestDate = PASS_DATE
            carPass.accessType = CarPassAccessType.OTHER
            carPassUseCase.sendRequest(carPass) { /* Do nothing */ }
        }
    }

    @Test(expected = CarNumberEmptyException::class)
    fun sendRequest_carNumberEmpty_throwsException() {
        runBlocking {
            carPass.carModel = CAR_MODEL
            carPass.requestDate = PASS_DATE
            carPass.accessType = CarPassAccessType.OTHER
            carPassUseCase.sendRequest(carPass) { /* Do nothing */ }
        }
    }

    @Test(expected = PassDateEmptyException::class)
    fun sendRequest_dateEmpty_throwsException() {
        runBlocking {
            carPass.carModel = CAR_MODEL
            carPass.carNumber = CAR_NUMBER
            carPass.accessType = CarPassAccessType.OTHER
            carPassUseCase.sendRequest(carPass) { /* Do nothing */ }
        }
    }

    @Test(expected = AccessTypeNotSelectedException::class)
    fun sendRequest_accessTypeNotSelected_throwsException() {
        runBlocking {
            carPass.carModel = CAR_MODEL
            carPass.carNumber = CAR_NUMBER
            carPass.requestDate = PASS_DATE
            carPassUseCase.sendRequest(carPass) { /* Do nothing */ }
        }
    }

    @Test
    fun sendRequest_validCarPass_sendStarted() {
        runBlocking {
            carPass.carModel = CAR_MODEL
            carPass.carNumber = CAR_NUMBER
            carPass.requestDate = PASS_DATE
            carPass.accessType = CarPassAccessType.OTHER

            var isSendStarted = false
            carPassUseCase.sendRequest(carPass) { isSendStarted = true }
            assertTrue(isSendStarted)
        }
    }
}