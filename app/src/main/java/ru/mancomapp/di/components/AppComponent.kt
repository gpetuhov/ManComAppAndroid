package ru.mancomapp.di.components

import dagger.Component
import ru.mancomapp.di.modules.AppModule
import ru.mancomapp.presentation.feedback.FeedbackViewModel
import ru.mancomapp.presentation.login.LoginViewModel
import ru.mancomapp.presentation.requests.RequestsViewModel
import ru.mancomapp.presentation.service.ServiceViewModel
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(loginViewModel: LoginViewModel)
    fun inject(requestsViewModel: RequestsViewModel)
    fun inject(feedbackViewModel: FeedbackViewModel)
    fun inject(serviceViewModel: ServiceViewModel)
}