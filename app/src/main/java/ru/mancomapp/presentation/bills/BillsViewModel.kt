package ru.mancomapp.presentation.bills

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import ru.mancomapp.App
import ru.mancomapp.R
import ru.mancomapp.domain.models.bill.Bill
import ru.mancomapp.domain.usecase.bill.BillUseCase
import javax.inject.Inject

class BillsViewModel : ViewModel() {

    @Inject lateinit var billUseCase: BillUseCase

    var isBillHistoryLoading: LiveData<Boolean>
    var billHistoryError: LiveData<Int>
    var billHistory: LiveData<List<Bill>>

    private var isBillHistoryLoadingMutable = MutableLiveData<Boolean>()
    private var billHistoryErrorMutable = MutableLiveData<Int>()
    private var billHistoryMutable = MutableLiveData<List<Bill>>()

    private var isBillHistoryLoaded = false
    private var isBillHistoryLoadingStarted = false

    private var loadBillHistoryJob: Job? = null

    init {
        App.appComponent.inject(this)

        isBillHistoryLoading = isBillHistoryLoadingMutable
        billHistoryError = billHistoryErrorMutable
        billHistory = billHistoryMutable
    }

    override fun onCleared() {
        super.onCleared()
        loadBillHistoryJob?.cancel()
    }

    fun loadBillHistory() {
        if (!isBillHistoryLoaded && !isBillHistoryLoadingStarted) {
            isBillHistoryLoadingStarted = true
            isBillHistoryLoadingMutable.postValue(true)
            startLoadBillHistory()
        }
    }

    private fun startLoadBillHistory() {
        loadBillHistoryJob = viewModelScope.launch(Dispatchers.IO) {
            try {
                val bills = billUseCase.getBills()

                isBillHistoryLoaded = true
                isBillHistoryLoadingStarted = false

                postLoadBillHistory(bills)
            } catch (e: Exception) {
                isBillHistoryLoadingStarted = false
                postLoadBillHistoryError(R.string.load_bill_history_error)
                // TODO: handle load error and no network (server unavailable)
            }
        }
    }

    private suspend fun postLoadBillHistory(requests: List<Bill>) {
        withContext(Dispatchers.Main) {
            isBillHistoryLoadingMutable.postValue(false)
            billHistoryMutable.postValue(requests)
        }
    }

    private suspend fun postLoadBillHistoryError(@StringRes errorMessageId: Int) {
        withContext(Dispatchers.Main) {
            isBillHistoryLoadingMutable.postValue(false)
            billHistoryErrorMutable.postValue(errorMessageId)
        }
    }
}