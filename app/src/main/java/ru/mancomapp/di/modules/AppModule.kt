package ru.mancomapp.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.mancomapp.App
import ru.mancomapp.data.repository.*
import ru.mancomapp.data.source.local.AppPrefs
import ru.mancomapp.domain.usecase.login.LoginUseCase
import ru.mancomapp.domain.usecase.request.RequestUseCase
import ru.mancomapp.domain.usecase.feedback.FeedbackUseCase
import ru.mancomapp.domain.usecase.pass.PersonPassUseCase
import ru.mancomapp.domain.usecase.security.SecurityUseCase
import ru.mancomapp.domain.usecase.service.ServiceUseCase
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(): Context = App.application.applicationContext

    @Provides
    @Singleton
    fun providesSettings(context: Context) = AppPrefs(context)

    @Provides
    @Singleton
    fun providesLoginRepository() = LoginRepository()

    @Provides
    @Singleton
    fun providesLoginUseCase(loginRepository: LoginRepository) =
        LoginUseCase(loginRepository)

    @Provides
    @Singleton
    fun providesRequestRepository() = RequestRepository()

    @Provides
    @Singleton
    fun providesRequestUseCase(requestRepository: RequestRepository) =
        RequestUseCase(requestRepository)

    @Provides
    @Singleton
    fun providesFeedbackRepository() = FeedbackRepository()

    @Provides
    @Singleton
    fun providesFeedbackUseCase(feedbackRepository: FeedbackRepository) =
        FeedbackUseCase(feedbackRepository)

    @Provides
    @Singleton
    fun providesServiceRepository() = ServiceRepository()

    @Provides
    @Singleton
    fun providesServiceUseCase(serviceRepository: ServiceRepository) =
        ServiceUseCase(serviceRepository)

    @Provides
    @Singleton
    fun providesSecurityRepository() = SecurityRepository()

    @Provides
    @Singleton
    fun providesSecurityUseCase(securityRepository: SecurityRepository) =
        SecurityUseCase(securityRepository)

    @Provides
    @Singleton
    fun providesPersonPassRepository() = PersonPassRepository()

    @Provides
    @Singleton
    fun providesPersonPassUseCase(personPassRepository: PersonPassRepository) =
        PersonPassUseCase(personPassRepository)
}