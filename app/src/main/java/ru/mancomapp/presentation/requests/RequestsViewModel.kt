package ru.mancomapp.presentation.requests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.mancomapp.App
import ru.mancomapp.domain.models.request.Request
import ru.mancomapp.domain.usecase.RequestUseCase
import javax.inject.Inject

class RequestsViewModel : ViewModel() {

    @Inject lateinit var requestUseCase: RequestUseCase

    var isRequestHistoryLoading: LiveData<Boolean>
    var isRequestHistoryError: LiveData<String>
    var requestHistory: LiveData<List<Request>>

    private var isRequestHistoryLoadingMutable = MutableLiveData<Boolean>()
    private var isRequestHistoryErrorMutable = MutableLiveData<String>()
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

            loadRequestHistoryJob = viewModelScope.launch(Dispatchers.IO) {
                try {
                    val requests = requestUseCase.getRequests()

                    isRequestHistoryLoaded = true
                    isRequestHistoryLoadingStarted = false

                    withContext(Dispatchers.Main) {
                        isRequestHistoryLoadingMutable.postValue(false)
                        requestHistoryMutable.postValue(requests)
                    }
                } catch (e: Exception) {
                    isRequestHistoryLoadingStarted = false
                    // TODO: handle load error and no network (server unavailable)
                }
            }
        }
    }
}