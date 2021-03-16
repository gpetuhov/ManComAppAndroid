package ru.mancomapp.di.modules

import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import ru.mancomapp.data.repository.FeedbackRepository
import ru.mancomapp.data.repository.LoginRepository
import ru.mancomapp.data.repository.RequestRepository
import ru.mancomapp.domain.usecase.login.LoginUseCase
import ru.mancomapp.domain.usecase.request.RequestUseCase
import ru.mancomapp.domain.usecase.feedback.FeedbackUseCase
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
}