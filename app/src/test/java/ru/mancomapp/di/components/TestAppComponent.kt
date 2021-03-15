package ru.mancomapp.di.components

import dagger.Component
import ru.mancomapp.di.modules.TestAppModule
import ru.mancomapp.presentation.login.LoginViewModel
import javax.inject.Singleton

@Component(modules = [TestAppModule::class])
@Singleton
interface TestAppComponent : AppComponent {
    override fun inject(loginViewModel: LoginViewModel)
}