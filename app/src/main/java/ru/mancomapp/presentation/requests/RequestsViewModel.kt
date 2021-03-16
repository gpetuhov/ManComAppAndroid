package ru.mancomapp.presentation.requests

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.mancomapp.App
import ru.mancomapp.R
import ru.mancomapp.domain.models.request.Request
import ru.mancomapp.domain.usecase.RequestUseCase
import javax.inject.Inject

class RequestsViewModel : ViewModel() {

    @Inject lateinit var requestUseCase: RequestUseCase

    var isRequestHistoryLoading: LiveData<Boolean>
    var isRequestHistoryError: LiveData<Int>
    var requestHistory: LiveData<List<Request>>

    private var isRequestHistoryLoadingMutable = MutableLiveData<Boolean>()
    private var isRequestHistoryErrorMutable = MutableLiveData<Int>()
    private var requestHistoryMutable = MutableLiveData<List<Request>>()

    private var isRequestHistoryLoaded = false
    private var isRequestHistoryLoadingStarted = false

    private var loadRequestHistoryJob: Job? = null

    init {
        App.appComponent.inject(this)

        isRequestHistoryLoading = isRequestHistoryLoadingMutable
        isRequestHistoryError = isRequestHistoryErrorMutable
        requestHistory = requestHistoryMutable
    }

    override fun onCleared() {
        super.onCleared()
        loadRequestHistoryJob?.cancel()
    }

    fun loadRequestHistory() {
        if (!isRequestHistoryLoaded && !isRequestHistoryLoadingStarted) {
            isRequestHistoryLoadingStarted = true
            isRequestHistoryLoadingMutable.postValue(true)
            startLoadRequestHistory()
        }
    }

    private fun startLoadRequestHistory() {
        loadRequestHistoryJob = viewModelScope.launch(Dispatchers.IO) {
            try {
                val requests = requestUseCase.getRequests()

                isRequestHistoryLoaded = true
                isRequestHistoryLoadingStarted = false

                postLoadRequestHistory(requests)
            } catch (e: Exception) {
                isRequestHistoryLoadingStarted = false
                postLoadRequestHistoryError(R.string.load_request_history_error)
                // TODO: handle load error and no network (server unavailable)
            }
        }
    }

    private suspend fun postLoadRequestHistory(requests: List<Request>) {
        withContext(Dispatchers.Main) {
            isRequestHistoryLoadingMutable.postValue(false)
            requestHistoryMutable.postValue(requests)
        }
    }

    private suspend fun postLoadRequestHistoryError(@StringRes errorMessageId: Int) {
        withContext(Dispatchers.Main) {
            isRequestHistoryLoadingMutable.postValue(false)
            isRequestHistoryErrorMutable.postValue(errorMessageId)
        }
    }
}