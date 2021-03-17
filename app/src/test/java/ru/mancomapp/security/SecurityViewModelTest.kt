package ru.mancomapp.security

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.mockito.Mockito
import ru.mancomapp.App
import ru.mancomapp.data.repository.SecurityRepository
import ru.mancomapp.di.components.DaggerTestAppComponent
import ru.mancomapp.domain.models.request.Request
import ru.mancomapp.domain.usecase.security.SecurityUseCase
import ru.mancomapp.presentation.security.SecurityViewModel

class SecurityViewModelTest {

    private lateinit var viewModel: SecurityViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun initViewModel() {
        App.appComponent = DaggerTestAppComponent.builder().build()
        Dispatchers.setMain(mainThreadSurrogate)

        viewModel = SecurityViewModel()
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
            val securityRepositoryMock = Mockito.mock(SecurityRepository::class.java)
            val requests: List<Request> = emptyList()
            Mockito.`when`(securityRepositoryMock.getRequests()).thenReturn(requests)
            val securityUseCase = SecurityUseCase(securityRepositoryMock)
            viewModel.securityUseCase = securityUseCase
            viewModel.loadRequestHistory()
            delay(300)
            assertEquals(requests, viewModel.requestHistory.value)
        }
    }
}