package ru.mancomapp.requests

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.junit.MockitoJUnitRunner
import ru.mancomapp.App
import ru.mancomapp.data.repository.RequestRepository
import ru.mancomapp.di.components.DaggerTestAppComponent
import ru.mancomapp.domain.models.request.Request
import ru.mancomapp.domain.usecase.request.RequestUseCase
import ru.mancomapp.presentation.requests.RequestsViewModel

@RunWith(MockitoJUnitRunner::class)
class RequestsViewModelTest {

    @Mock lateinit var requestRepositoryMock: RequestRepository

    private lateinit var viewModel: RequestsViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun initViewModel() {
        App.appComponent = DaggerTestAppComponent.builder().build()
        Dispatchers.setMain(mainThreadSurrogate)

        viewModel = RequestsViewModel()
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun loadRequestHistory_noErrors_loadingFalse() {
        viewModel.loadRequestHistory()
        Thread.sleep(300)
        assertFalse(viewModel.isRequestHistoryLoading.value ?: false)
    }

    @Test
    fun loadRequestHistory_noErrors_resultNotNull() {
        runBlocking {
            val requests: List<Request> = emptyList()
            Mockito.`when`(requestRepositoryMock.getRequests()).thenReturn(requests)
            val requestUseCase = RequestUseCase(requestRepositoryMock)
            viewModel.requestUseCase = requestUseCase
            viewModel.loadRequestHistory()
            delay(300)
            assertEquals(requests, viewModel.requestHistory.value)
        }
    }
}