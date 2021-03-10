package ru.mancomapp.ui.requests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.mancomapp.models.Request

class RequestsViewModel : ViewModel() {

    var isRequestHistoryLoading: LiveData<Boolean>
    var isRequestHistoryError: LiveData<String>
    var requestHistory: LiveData<List<Request>>

    private var isRequestHistoryLoadingMutable = MutableLiveData<Boolean>()
    private var isRequestHistoryErrorMutable = MutableLiveData<String>()
    private var requestHistoryMutable = MutableLiveData<List<Request>>()

    private var loadRequestHistoryJob: Job? = null

    init {
        isRequestHistoryLoading = isRequestHistoryLoadingMutable
        isRequestHistoryError = isRequestHistoryErrorMutable
        requestHistory = requestHistoryMutable
    }

    override fun onCleared() {
        super.onCleared()
        loadRequestHistoryJob?.cancel()
    }

    fun loadRequestHistory() {
        isRequestHistoryLoadingMutable.postValue(true)

        loadRequestHistoryJob = viewModelScope.launch(Dispatchers.IO) {
            // TODO: implement
            delay(5000)
            val requests = getDummyRequests()

            // TODO: handle load error and no network (server unavailable)

            withContext(Dispatchers.Main) {
                isRequestHistoryLoadingMutable.postValue(false)
                requestHistoryMutable.postValue(requests)
            }
        }
    }

    // TODO: remove this
    private fun getDummyRequests(): List<Request> {
        val requests = mutableListOf<Request>()

        (1..100).forEach {
            requests.add(Request(it, "Заявка $it"))
        }

        return requests
    }
}