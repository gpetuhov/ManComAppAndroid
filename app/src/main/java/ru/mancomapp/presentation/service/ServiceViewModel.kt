package ru.mancomapp.presentation.service

import android.net.Uri
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.*
import ru.mancomapp.App
import ru.mancomapp.domain.models.Attachment
import ru.mancomapp.data.source.local.AppConstants
import ru.mancomapp.utils.getFileName

class ServiceViewModel : ViewModel() {

    var isSendStarted: LiveData<Boolean>
    var isSendSuccess: LiveData<Boolean>
    var sendError: LiveData<Int>
    var attachments: LiveData<List<Attachment>>

    private val isSendStartedLiveDataMutable = MutableLiveData<Boolean>()
    private val isSendSuccessLiveDataMutable = MutableLiveData<Boolean>()
    private val sendErrorLiveDataMutable = MutableLiveData<Int>()
    private val attachmentsLiveDataMutable = MutableLiveData<List<Attachment>>()

    private var sendJob: Job? = null
    private val attachmentsList = mutableListOf<Attachment>()

    init {
        isSendStarted = isSendStartedLiveDataMutable
        isSendSuccess = isSendSuccessLiveDataMutable
        sendError = sendErrorLiveDataMutable
        attachments = attachmentsLiveDataMutable
    }

    override fun onCleared() {
        super.onCleared()
        sendJob?.cancel()
    }

    fun isAddAttachmentsAllowed() = attachmentsList.size < AppConstants.Files.SERVICE_MAX_FILES

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

    fun send() {
        // TODO: implement
    }

    private suspend fun postSendStarted() {
        withContext(Dispatchers.Main) {
            isSendStartedLiveDataMutable.postValue(true)
        }
    }

    private suspend fun postSendSuccess() {
        withContext(Dispatchers.Main) {
            isSendSuccessLiveDataMutable.postValue(true)
        }
    }

    private suspend fun postSendError(@StringRes errorMessageId: Int) {
        withContext(Dispatchers.Main) {
            isSendStartedLiveDataMutable.postValue(false)
            isSendSuccessLiveDataMutable.postValue(false)
            sendErrorLiveDataMutable.postValue(errorMessageId)
        }
    }
}