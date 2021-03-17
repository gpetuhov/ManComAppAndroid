package ru.mancomapp.presentation.global

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.mancomapp.R
import ru.mancomapp.domain.models.request.Request

abstract class RequestsBaseViewModel : ViewModel() {

    var isRequestHistoryLoading: LiveData<Boolean>
    var requestHistoryError: LiveData<Int>
    var requestHistory: LiveData<List<Request>>

    private var isRequestHistoryLoadingMutable = MutableLiveData<Boolean>()
    private var requestHistoryErrorMutable = MutableLiveData<Int>()
    private var requestHistoryMutable = MutableLiveData<List<Request>>()

    private var isRequestHistoryLoaded = false
    private var isRequestHistoryLoadingStarted = false

    private var loadRequestHistoryJob: Job? = null

    init {
        isRequestHistoryLoading = isRequestHistoryLoadingMutable
        requestHistoryError = requestHistoryErrorMutable
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
                val requests = getRequests()

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

    @Throws(Exception::class)
    protected abstract suspend fun getRequests(): List<Request>

    private suspend fun postLoadRequestHistory(requests: List<Request>) {
        withContext(Dispatchers.Main) {
            isRequestHistoryLoadingMutable.postValue(false)
            requestHistoryMutable.postValue(requests)
        }
    }

    private suspend fun postLoadRequestHistoryError(@StringRes errorMessageId: Int) {
        withContext(Dispatchers.Main) {
            isRequestHistoryLoadingMutable.postValue(false)
            requestHistoryErrorMutable.postValue(errorMessageId)
        }
    }
}