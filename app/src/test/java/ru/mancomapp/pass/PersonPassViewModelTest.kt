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
import ru.mancomapp.domain.models.pass.PersonPassAccessType
import ru.mancomapp.presentation.passperson.PersonPassViewModel

class PersonPassViewModelTest {

    companion object {
        private const val NAME = "Name"
        private val PASS_DATE = PersonPassTestData.getPassDate()
    }

    private lateinit var viewModel: PersonPassViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun initViewModel() {
        App.appComponent = DaggerTestAppComponent.builder().build()
        Dispatchers.setMain(mainThreadSurrogate)

        viewModel = PersonPassViewModel()
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun sendRequest_nameEmpty_errorNameEmpty() {
        viewModel.saveSelectedDate(PASS_DATE)
        viewModel.saveSelectedAccessType(PersonPassAccessType.OTHER)
        viewModel.sendRequest("")
        delay()
        assertFalse(viewModel.isSendStarted.value ?: false)
        assertFalse(viewModel.isSendSuccess.value ?: false)
        assertEquals(R.string.guest_name_empty_error, viewModel.sendError.value)
    }

    @Test
    fun sendRequest_passDateEmpty_errorDateEmpty() {
        viewModel.saveSelectedAccessType(PersonPassAccessType.OTHER)
        viewModel.sendRequest(NAME)
        delay()
        assertFalse(viewModel.isSendStarted.value ?: false)
        assertFalse(viewModel.isSendSuccess.value ?: false)
        assertEquals(R.string.pass_date_empty_error, viewModel.sendError.value)
    }

    // TODO: restore this, when access type implemented
//    @Test
//    fun sendRequest_accessTypeNotSelected_errorAccessTypeNotSelected() {
//        viewModel.saveSelectedDate(passDate)
//        viewModel.sendRequest(NAME)
//        delay()
//        assertFalse(viewModel.isSendStarted.value ?: false)
//        assertFalse(viewModel.isSendSuccess.value ?: false)
//        assertEquals(R.string.access_type_empty_error, viewModel.sendError.value)
//    }

    @Test
    fun sendRequest_validPersonPass_sendStarted() {
        viewModel.saveSelectedDate(PASS_DATE)
        viewModel.saveSelectedAccessType(PersonPassAccessType.OTHER)
        viewModel.sendRequest(NAME)
        delay()
        assertTrue(viewModel.isSendStarted.value ?: false)
    }

    @Test
    fun sendRequest_validPersonPass_sendSuccess() {
        viewModel.saveSelectedDate(PASS_DATE)
        viewModel.saveSelectedAccessType(PersonPassAccessType.OTHER)
        viewModel.sendRequest(NAME)
        delay()
        assertTrue(viewModel.isSendSuccess.value ?: false)
    }

    private fun delay() = Thread.sleep(300)
}