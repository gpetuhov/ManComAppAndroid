package ru.mancomapp.pass

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.*
import ru.mancomapp.App
import ru.mancomapp.R
import ru.mancomapp.di.components.DaggerTestAppComponent
import ru.mancomapp.domain.models.pass.CarPassAccessType
import ru.mancomapp.presentation.passcar.CarPassViewModel
import ru.mancomapp.testdata.RequestTestData

class CarPassViewModelTest {

    companion object {
        private const val CAR_MODEL = "Model"
        private const val CAR_NUMBER = "Number"
        private val PASS_DATE = RequestTestData.getRequestDate()
    }

    private lateinit var viewModel: CarPassViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun initViewModel() {
        App.appComponent = DaggerTestAppComponent.builder().build()
        Dispatchers.setMain(mainThreadSurrogate)

        viewModel = CarPassViewModel()
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun sendRequest_carModelEmpty_errorCarModelEmpty() {
        viewModel.saveSelectedDate(PASS_DATE)
        viewModel.saveSelectedAccessType(CarPassAccessType.OTHER)
        viewModel.sendRequest("", CAR_NUMBER)
        delay()
        assertFalse(viewModel.isSendStarted.value ?: false)
        assertFalse(viewModel.isSendSuccess.value ?: false)
        assertEquals(R.string.car_model_empty_error, viewModel.sendError.value)
    }

    @Test
    fun sendRequest_carNumberEmpty_errorCarNumberEmpty() {
        viewModel.saveSelectedDate(PASS_DATE)
        viewModel.saveSelectedAccessType(CarPassAccessType.OTHER)
        viewModel.sendRequest(CAR_MODEL, "")
        delay()
        assertFalse(viewModel.isSendStarted.value ?: false)
        assertFalse(viewModel.isSendSuccess.value ?: false)
        assertEquals(R.string.car_number_empty_error, viewModel.sendError.value)
    }

    @Test
    fun sendRequest_passDateEmpty_errorDateEmpty() {
        viewModel.saveSelectedAccessType(CarPassAccessType.OTHER)
        viewModel.sendRequest(CAR_MODEL, CAR_NUMBER)
        delay()
        assertFalse(viewModel.isSendStarted.value ?: false)
        assertFalse(viewModel.isSendSuccess.value ?: false)
        assertEquals(R.string.pass_date_empty_error, viewModel.sendError.value)
    }

    // TODO: restore this, when access type implemented
//    @Test
//    fun sendRequest_accessTypeNotSelected_errorAccessTypeNotSelected() {
//        viewModel.saveSelectedDate(PASS_DATE)
//        viewModel.sendRequest(CAR_MODEL, CAR_NUMBER)
//        delay()
//        assertFalse(viewModel.isSendStarted.value ?: false)
//        assertFalse(viewModel.isSendSuccess.value ?: false)
//        assertEquals(R.string.access_type_empty_error, viewModel.sendError.value)
//    }

    @Test
    fun sendRequest_validCarPass_sendStarted() {
        viewModel.saveSelectedDate(PASS_DATE)
        viewModel.saveSelectedAccessType(CarPassAccessType.OTHER)
        viewModel.sendRequest(CAR_MODEL, CAR_NUMBER)
        delay()
        assertTrue(viewModel.isSendStarted.value ?: false)
    }

    @Test
    fun sendRequest_validCarPass_sendSuccess() {
        viewModel.saveSelectedDate(PASS_DATE)
        viewModel.saveSelectedAccessType(CarPassAccessType.OTHER)
        viewModel.sendRequest(CAR_MODEL, CAR_NUMBER)
        delay()
        assertTrue(viewModel.isSendSuccess.value ?: false)
    }

    private fun delay() = Thread.sleep(300)
}