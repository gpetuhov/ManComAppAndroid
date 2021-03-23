package ru.mancomapp.presentation.security

import ru.mancomapp.App
import ru.mancomapp.domain.models.request.Request
import ru.mancomapp.domain.usecase.security.SecurityUseCase
import ru.mancomapp.presentation.global.RequestsBaseViewModel
import javax.inject.Inject

class SecurityViewModel : RequestsBaseViewModel() {

    @Inject lateinit var securityUseCase: SecurityUseCase

    init {
        App.appComponent.inject(this)
    }

    override suspend fun getRequests(): List<Request> = securityUseCase.getRequests()
}