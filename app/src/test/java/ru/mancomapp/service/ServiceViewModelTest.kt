package ru.mancomapp.service

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
import ru.mancomapp.domain.models.service.Service
import ru.mancomapp.domain.models.service.ServiceType
import ru.mancomapp.presentation.service.ServiceViewModel

class ServiceViewModelTest {

    companion object {
        private const val COMMENT = "Comment"
    }

    private lateinit var viewModel: ServiceViewModel
    private lateinit var service: Service

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun initViewModel() {
        App.appComponent = DaggerTestAppComponent.builder().build()
        Dispatchers.setMain(mainThreadSurrogate)

        viewModel = ServiceViewModel()
        service = Service()
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun sendRequest_typeNotSelected_errorTypeNotSelected() {
        viewModel.sendRequest("")
        delay()
        assertFalse(viewModel.isSendStarted.value ?: false)
        assertFalse(viewModel.isSendSuccess.value ?: false)
        assertEquals(R.string.choose_service_error, viewModel.sendError.value)
    }

    @Test
    fun sendRequest_emptyComment_errorEmptyComment() {
        viewModel.saveServiceType(ServiceType.OTHER)
        viewModel.sendRequest("")
        delay()
        assertFalse(viewModel.isSendStarted.value ?: false)
        assertFalse(viewModel.isSendSuccess.value ?: false)
        assertEquals(R.string.write_comment_hint, viewModel.sendError.value)
    }

    @Test
    fun sendRequest_validService_sendStarted() {
        viewModel.saveServiceType(ServiceType.OTHER)
        viewModel.sendRequest(COMMENT)
        delay()
        assertTrue(viewModel.isSendStarted.value ?: false)
    }

    @Test
    fun sendRequest_validService_sendSuccess() {
        viewModel.saveServiceType(ServiceType.OTHER)
        viewModel.sendRequest(COMMENT)
        delay()
        assertTrue(viewModel.isSendSuccess.value ?: false)
    }

    private fun delay() = Thread.sleep(300)
}