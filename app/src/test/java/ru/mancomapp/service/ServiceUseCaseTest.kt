package ru.mancomapp.service

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import ru.mancomapp.data.repository.ServiceRepository
import ru.mancomapp.domain.models.service.Service
import ru.mancomapp.domain.models.service.ServiceType
import ru.mancomapp.domain.usecase.service.ServiceCommentEmptyException
import ru.mancomapp.domain.usecase.service.ServiceTypeNotSelectedException
import ru.mancomapp.domain.usecase.service.ServiceUseCase

class ServiceUseCaseTest {

    companion object {
        private const val COMMENT = "Comment"
    }

    private val serviceRepositoryMock = Mockito.mock(ServiceRepository::class.java)
    private lateinit var serviceUseCase: ServiceUseCase
    private lateinit var service: Service

    @Before
    fun initFeedback() {
        service = Service()
        serviceUseCase = ServiceUseCase(serviceRepositoryMock)
    }

    @Test(expected = ServiceTypeNotSelectedException::class)
    fun sendRequest_typeNotSelected_throwsException() {
        runBlocking {
            serviceUseCase.sendRequest(service) { /* Do nothing */ }
        }
    }

    @Test(expected = ServiceCommentEmptyException::class)
    fun sendRequest_emptyComment_throwsException() {
        runBlocking {
            service.type = ServiceType.OTHER
            serviceUseCase.sendRequest(service) { /* Do nothing */ }
        }
    }

    @Test
    fun sendRequest_validService_sendStarted() {
        runBlocking {
            service.type = ServiceType.OTHER
            service.comment = COMMENT

            var isSendStarted = false
            serviceUseCase.sendRequest(service) { isSendStarted = true }
            assertTrue(isSendStarted)
        }
    }
}