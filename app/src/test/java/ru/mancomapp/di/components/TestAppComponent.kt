package ru.mancomapp.di.components

import dagger.Component
import ru.mancomapp.di.modules.TestAppModule
import ru.mancomapp.presentation.feedback.FeedbackViewModel
import ru.mancomapp.presentation.login.LoginViewModel
import ru.mancomapp.presentation.requests.RequestsViewModel
import javax.inject.Singleton

@Component(modules = [TestAppModule::class])
@Singleton
interface TestAppComponent : AppComponent {
    override fun inject(loginViewModel: LoginViewModel)
    override fun inject(requestsViewModel: RequestsViewModel)
    override fun inject(feedbackViewModel: FeedbackViewModel)
}