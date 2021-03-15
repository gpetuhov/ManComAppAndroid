package ru.mancomapp.ui.feedback

import android.net.Uri
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.mancomapp.R
import ru.mancomapp.App
import ru.mancomapp.models.Attachment
import ru.mancomapp.source.local.AppConstants
import ru.mancomapp.util.getFileName

class FeedbackViewModel : ViewModel() {

    var isSendStarted: LiveData<Boolean>
    var isSendSuccess: LiveData<Boolean>
    var isSendError: LiveData<String>
    var attachments: LiveData<List<Attachment>>

    private val isSendStartedLiveDataMutable = MutableLiveData<Boolean>()
    private val isSendSuccessLiveDataMutable = MutableLiveData<Boolean>()
    private val isSendErrorLiveDataMutable = MutableLiveData<String>()
    private val attachmentsLiveDataMutable = MutableLiveData<List<Attachment>>()

    private var sendJob: Job? = null
    private val attachmentsList = mutableListOf<Attachment>()

    init {
        isSendStarted = isSendStartedLiveDataMutable
        isSendSuccess = isSendSuccessLiveDataMutable
        isSendError = isSendErrorLiveDataMutable
        attachments = attachmentsLiveDataMutable
    }

    override fun onCleared() {
        super.onCleared()
        sendJob?.cancel()
    }

    fun isAddAttachmentsAllowed() = attachmentsList.size < AppConstants.Files.FEEDBACK_MAX_FILES

    fun addAttachment(uri: Uri?) {
        uri?.let {
            val fileName = getFileName(App.application.applicationContext, uri) ?: ""
            val attachment = Attachment(uri, fileName)
            attachmentsList.add(attachment)

            val newAttachments = mutableListOf<Attachment>()
            newAttachments.addAll(attachmentsList)
            attachmentsLiveDataMutable.postValue(newAttachments)
        }
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

        fun isEmpty() = title.isEmpty() || content.isEmpty()
    }
}