package ru.mancomapp.di.components

import dagger.Component
import ru.mancomapp.di.modules.AppModule
import ru.mancomapp.presentation.feedback.FeedbackViewModel
import ru.mancomapp.presentation.login.LoginViewModel
import ru.mancomapp.presentation.passcar.CarPassViewModel
import ru.mancomapp.presentation.passperson.PersonPassViewModel
import ru.mancomapp.presentation.requests.RequestsViewModel
import ru.mancomapp.presentation.security.SecurityViewModel
import ru.mancomapp.presentation.service.ServiceViewModel
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(loginViewModel: LoginViewModel)
    fun inject(requestsViewModel: RequestsViewModel)
    fun inject(feedbackViewModel: FeedbackViewModel)
    fun inject(serviceViewModel: ServiceViewModel)
    fun inject(securityViewModel: SecurityViewModel)
    fun inject(personPassViewModel: PersonPassViewModel)
    fun inject(carPassViewModel: CarPassViewModel)
}