package ru.mancomapp.presentation.service

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.mancomapp.App
import ru.mancomapp.R
import ru.mancomapp.domain.models.service.Service
import ru.mancomapp.domain.models.service.ServiceType
import ru.mancomapp.domain.usecase.service.ServiceCommentEmptyException
import ru.mancomapp.domain.usecase.service.ServiceTypeNotSelectedException
import ru.mancomapp.domain.usecase.service.ServiceUseCase
import ru.mancomapp.presentation.global.FeedbackBaseViewModel
import javax.inject.Inject

class ServiceViewModel : FeedbackBaseViewModel() {

    @Inject lateinit var serviceUseCase: ServiceUseCase

    var serviceType: LiveData<ServiceType>

    private val serviceTypeLiveDataMutable = MutableLiveData<ServiceType>()

    private var selectedServiceType: ServiceType = ServiceType.NOT_SELECTED

    init {
        App.appComponent.inject(this)
        serviceType = serviceTypeLiveDataMutable
        serviceTypeLiveDataMutable.postValue(ServiceType.NOT_SELECTED)
    }

    fun saveServiceType(serviceType: ServiceType) {
        selectedServiceType = serviceType
        serviceTypeLiveDataMutable.postValue(serviceType)
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
}