package ru.mancomapp.presentation.global

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*

open class SendRequestBaseViewModel : ViewModel() {

    var isSendStarted: LiveData<Boolean>
    var isSendSuccess: LiveData<Boolean>
    var sendError: LiveData<Int>

    var isBackPressedAllowed = true

    private val isSendStartedLiveDataMutable = MutableLiveData<Boolean>()
    private val isSendSuccessLiveDataMutable = MutableLiveData<Boolean>()
    private val sendErrorLiveDataMutable = MutableLiveData<Int>()

    protected var sendJob: Job? = null

    init {
        isSendStarted = isSendStartedLiveDataMutable
        isSendSuccess = isSendSuccessLiveDataMutable
        sendError = sendErrorLiveDataMutable
    }

    override fun onCleared() {
        super.onCleared()
        sendJob?.cancel()
    }

    protected suspend fun postSendStarted() {
        withContext(Dispatchers.Main) {
            isBackPressedAllowed = false
            isSendStartedLiveDataMutable.postValue(true)
        }
    }

    protected suspend fun postSendSuccess() {
        withContext(Dispatchers.Main) {
            isBackPressedAllowed = true
            isSendSuccessLiveDataMutable.postValue(true)
        }
    }

    protected suspend fun postSendError(@StringRes errorMessageId: Int) {
        withContext(Dispatchers.Main) {
            isBackPressedAllowed = true
            isSendStartedLiveDataMutable.postValue(false)
            isSendSuccessLiveDataMutable.postValue(false)
            sendErrorLiveDataMutable.postValue(errorMessageId)
        }
    }
}