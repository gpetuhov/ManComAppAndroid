package ru.mancomapp.di.components

import dagger.Component
import ru.mancomapp.di.modules.AppModule
import ru.mancomapp.presentation.login.LoginViewModel
import ru.mancomapp.presentation.requests.RequestsViewModel
import javax.inject.Singleton

@Component(modules = [AppModule::class])
@Singleton
interface AppComponent {
    fun inject(loginViewModel: LoginViewModel)
    fun inject(requestsViewModel: RequestsViewModel)
}