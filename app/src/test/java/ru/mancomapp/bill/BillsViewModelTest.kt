package ru.mancomapp.bill

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
import ru.mancomapp.data.repository.BillRepository
import ru.mancomapp.di.components.DaggerTestAppComponent
import ru.mancomapp.domain.models.bill.Bill
import ru.mancomapp.domain.usecase.bill.BillUseCase
import ru.mancomapp.presentation.bills.BillsViewModel

@RunWith(MockitoJUnitRunner::class)
class BillsViewModelTest {

    @Mock lateinit var billRepositoryMock: BillRepository

    private lateinit var viewModel: BillsViewModel

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun initViewModel() {
        App.appComponent = DaggerTestAppComponent.builder().build()
        Dispatchers.setMain(mainThreadSurrogate)

        viewModel = BillsViewModel()
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun loadBillHistory_noErrors_loadingFalse() {
        viewModel.loadBillHistory()
        Thread.sleep(300)
        assertFalse(viewModel.isBillHistoryLoading.value ?: false)
    }

    @Test
    fun loadBillHistory_noErrors_resultNotNull() {
        runBlocking {
            val bills: List<Bill> = emptyList()
            Mockito.`when`(billRepositoryMock.getBills()).thenReturn(bills)
            val billUseCase = BillUseCase(billRepositoryMock)
            viewModel.billUseCase = billUseCase
            viewModel.loadBillHistory()
            delay(300)
            assertEquals(bills, viewModel.billHistory.value)
        }
    }
}