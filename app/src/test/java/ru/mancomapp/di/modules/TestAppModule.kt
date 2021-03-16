package ru.mancomapp.di.modules

import dagger.Module
import dagger.Provides
import org.mockito.Mockito
import ru.mancomapp.data.repository.LoginRepository
import ru.mancomapp.data.repository.RequestRepository
import ru.mancomapp.domain.usecase.login.LoginUseCase
import ru.mancomapp.domain.usecase.RequestUseCase
import javax.inject.Singleton

@Module
class TestAppModule {

    @Provides
    @Singleton
    fun providesLoginRepository(): LoginRepository = Mockito.mock(LoginRepository::class.java)

    @Provides
    @Singleton
    fun providesLoginUseCase(loginRepository: LoginRepository) = LoginUseCase(loginRepository)

    @Provides
    @Singleton
    fun providesRequestRepository() = RequestRepository()

    @Provides
    @Singleton
    fun providesRequestUseCase(requestRepository: RequestRepository) =
        RequestUseCase(requestRepository)
}