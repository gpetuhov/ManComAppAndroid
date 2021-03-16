package ru.mancomapp.di.modules

import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import ru.mancomapp.data.repository.LoginRepository
import ru.mancomapp.data.repository.RequestRepository
import ru.mancomapp.domain.usecase.LoginUseCase
import ru.mancomapp.domain.usecase.RequestUseCase
import javax.inject.Singleton

@Module
class TestAppModule {

    @Provides
    @Singleton
    fun providesLoginRepository() = LoginRepository()

    @Provides
    @Singleton
    fun providesLoginUseCase(loginRepository: LoginRepository) =
        Mockito.mock(LoginUseCase::class.java)

    @Provides
    @Singleton
    fun providesRequestRepository() = RequestRepository()

    @Provides
    @Singleton
    fun providesRequestUseCase(requestRepository: RequestRepository) =
        RequestUseCase(requestRepository)
}