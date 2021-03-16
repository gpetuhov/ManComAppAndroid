package ru.mancomapp.presentation.feedback

import android.net.Uri
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.mancomapp.R
import ru.mancomapp.App
import ru.mancomapp.domain.models.Attachment
import ru.mancomapp.data.source.local.AppConstants
import ru.mancomapp.domain.models.Feedback
import ru.mancomapp.domain.usecase.feedback.FeedbackEmptyException
import ru.mancomapp.domain.usecase.feedback.FeedbackUseCase
import ru.mancomapp.utils.getFileName
import javax.inject.Inject

class FeedbackViewModel : ViewModel() {

    @Inject lateinit var feedbackUseCase: FeedbackUseCase

    var isSendStarted: LiveData<Boolean>
    var isSendSuccess: LiveData<Boolean>
    var isSendError: LiveData<Int>
    var attachments: LiveData<List<Attachment>>

    private val isSendStartedLiveDataMutable = MutableLiveData<Boolean>()
    private val isSendSuccessLiveDataMutable = MutableLiveData<Boolean>()
    private val isSendErrorLiveDataMutable = MutableLiveData<Int>()
    private val attachmentsLiveDataMutable = MutableLiveData<List<Attachment>>()

    private var sendJob: Job? = null
    private val attachmentsList = mutableListOf<Attachment>()

    init {
        App.appComponent.inject(this)

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
        sendJob = viewModelScope.launch(Dispatchers.IO) {
            try {
                feedbackUseCase.sendFeedback(feedback) { postSendStarted() }
                postSendSuccess()
            } catch (e: FeedbackEmptyException) {
                postSendError(R.string.feedback_empty_error)
            } catch (e: Exception) {
                // TODO: handle send error and no network (server unavailable)
            }
        }
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
            isSendErrorLiveDataMutable.postValue(errorMessageId)
        }
    }
}