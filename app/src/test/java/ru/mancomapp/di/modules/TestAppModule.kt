package ru.mancomapp.di.modules

import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import ru.mancomapp.data.repository.*
import ru.mancomapp.domain.usecase.login.LoginUseCase
import ru.mancomapp.domain.usecase.request.RequestUseCase
import ru.mancomapp.domain.usecase.feedback.FeedbackUseCase
import ru.mancomapp.domain.usecase.security.SecurityUseCase
import ru.mancomapp.domain.usecase.service.ServiceUseCase
import javax.inject.Singleton

@Module
class TestAppModule {

    @Provides
    @Singleton
    fun providesLoginRepository(): LoginRepository =
        Mockito.mock(LoginRepository::class.java)

    @Provides
    @Singleton
    fun providesLoginUseCase(loginRepository: LoginRepository) =
        LoginUseCase(loginRepository)

    @Provides
    @Singleton
    fun providesRequestRepository(): RequestRepository =
        Mockito.mock(RequestRepository::class.java)

    @Provides
    @Singleton
    fun providesRequestUseCase(requestRepository: RequestRepository) =
        RequestUseCase(requestRepository)

    @Provides
    @Singleton
    fun providesFeedbackRepository(): FeedbackRepository =
        Mockito.mock(FeedbackRepository::class.java)

    @Provides
    @Singleton
    fun providesFeedbackUseCase(feedbackRepository: FeedbackRepository) =
        FeedbackUseCase(feedbackRepository)

    @Provides
    @Singleton
    fun providesServiceRepository(): ServiceRepository =
        Mockito.mock(ServiceRepository::class.java)

    @Provides
    @Singleton
    fun providesServiceUseCase(serviceRepository: ServiceRepository) =
        ServiceUseCase(serviceRepository)

    @Provides
    @Singleton
    fun providesSecurityRepository(): SecurityRepository =
        Mockito.mock(SecurityRepository::class.java)

    @Provides
    @Singleton
    fun providesSecurityUseCase(securityRepository: SecurityRepository) =
        SecurityUseCase(securityRepository)
}