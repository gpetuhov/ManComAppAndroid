package ru.mancomapp.ui.feedback

import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.mancomapp.R
import ru.mancomapp.application.App

class FeedbackViewModel : ViewModel() {

    var isSendStarted: LiveData<Boolean>
    var isSendSuccess: LiveData<Boolean>
    var isSendError: LiveData<String>

    private val isSendStartedLiveDataMutable = MutableLiveData<Boolean>()
    private val isSendSuccessLiveDataMutable = MutableLiveData<Boolean>()
    private val isSendErrorLiveDataMutable = MutableLiveData<String>()

    private var sendJob: Job? = null

    init {
        isSendStarted = isSendStartedLiveDataMutable
        isSendSuccess = isSendSuccessLiveDataMutable
        isSendError = isSendErrorLiveDataMutable
    }

    override fun onCleared() {
        super.onCleared()
        sendJob?.cancel()
    }

    fun send(feedback: Feedback) {
        if (feedback.isEmpty()) {
            postSendError(R.string.feedback_empty_error)
            return
        }

        startSend(feedback)
    }

    private fun postSendError(@StringRes errorMessageId: Int) {
        val errorMessage = App.application.getString(errorMessageId)
        isSendErrorLiveDataMutable.postValue(errorMessage)
    }

    private fun startSend(feedback: Feedback) {
        isSendStartedLiveDataMutable.postValue(true)

        sendJob = viewModelScope.launch(Dispatchers.IO) {
            // TODO: implement
            delay(5000)

            // TODO: handle send error and no network (server unavailable)
            val isSuccess = true

            withContext(Dispatchers.Main) {
                isSendSuccessLiveDataMutable.postValue(isSuccess)
            }
        }
    }

    class Feedback {
        var title: String = ""
        var content: String = ""

        // TODO: add files list

        fun isEmpty() = title.isEmpty() || content.isEmpty()
    }
}