package ru.mancomapp.presentation.service

import android.net.Uri
import androidx.annotation.StringRes
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.mancomapp.App
import ru.mancomapp.R
import ru.mancomapp.domain.models.Attachment
import ru.mancomapp.data.source.local.AppConstants
import ru.mancomapp.domain.models.service.Service
import ru.mancomapp.domain.models.service.ServiceType
import ru.mancomapp.domain.usecase.service.ServiceCommentEmptyException
import ru.mancomapp.domain.usecase.service.ServiceTypeNotSelectedException
import ru.mancomapp.domain.usecase.service.ServiceUseCase
import ru.mancomapp.utils.getFileName
import javax.inject.Inject

class ServiceViewModel : ViewModel() {

    @Inject lateinit var serviceUseCase: ServiceUseCase

    var serviceType: LiveData<ServiceType>
    var isSendStarted: LiveData<Boolean>
    var isSendSuccess: LiveData<Boolean>
    var sendError: LiveData<Int>
    var attachments: LiveData<List<Attachment>>

    private val serviceTypeLiveDataMutable = MutableLiveData<ServiceType>()
    private val isSendStartedLiveDataMutable = MutableLiveData<Boolean>()
    private val isSendSuccessLiveDataMutable = MutableLiveData<Boolean>()
    private val sendErrorLiveDataMutable = MutableLiveData<Int>()
    private val attachmentsLiveDataMutable = MutableLiveData<List<Attachment>>()

    private var selectedServiceType: ServiceType = ServiceType.NOT_SELECTED
    private var sendJob: Job? = null
    private val attachmentsList = mutableListOf<Attachment>()

    init {
        App.appComponent.inject(this)

        serviceType = serviceTypeLiveDataMutable
        isSendStarted = isSendStartedLiveDataMutable
        isSendSuccess = isSendSuccessLiveDataMutable
        sendError = sendErrorLiveDataMutable
        attachments = attachmentsLiveDataMutable

        serviceTypeLiveDataMutable.postValue(ServiceType.NOT_SELECTED)
    }

    override fun onCleared() {
        super.onCleared()
        sendJob?.cancel()
    }

    fun saveServiceType(serviceType: ServiceType) {
        selectedServiceType = serviceType
        serviceTypeLiveDataMutable.postValue(serviceType)
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

    fun sendRequest(serviceComment: String) {
        val service = Service().apply {
            type = selectedServiceType
            comment = serviceComment
        }

        sendJob = viewModelScope.launch(Dispatchers.IO) {
            try {
                serviceUseCase.sendRequest(service) { postSendStarted() }
                postSendSuccess()
            } catch (e: ServiceTypeNotSelectedException) {
                postSendError(R.string.choose_service_error)
            } catch (e: ServiceCommentEmptyException) {
                postSendError(R.string.write_comment_hint)
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
            sendErrorLiveDataMutable.postValue(errorMessageId)
        }
    }
}