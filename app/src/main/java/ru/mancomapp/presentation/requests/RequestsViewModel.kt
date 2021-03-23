package ru.mancomapp.presentation.requests

import ru.mancomapp.App
import ru.mancomapp.domain.models.request.Request
import ru.mancomapp.domain.usecase.request.RequestUseCase
import ru.mancomapp.presentation.global.RequestsBaseViewModel
import javax.inject.Inject

class RequestsViewModel : RequestsBaseViewModel() {

    @Inject lateinit var requestUseCase: RequestUseCase

    init {
        App.appComponent.inject(this)
    }

    override suspend fun getRequests(): List<Request> = requestUseCase.getRequests()
}