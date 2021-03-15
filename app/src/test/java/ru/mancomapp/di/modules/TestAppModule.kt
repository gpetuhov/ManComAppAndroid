package ru.mancomapp.di.modules

import dagger.Module
import dagger.Provides
import org.mockito.Mockito.mock
import ru.mancomapp.data.repository.LoginRepository
import ru.mancomapp.domain.usecase.login.LoginUseCase
import javax.inject.Singleton

@Module
class TestAppModule {

    @Provides
    @Singleton
    fun providesLoginUseRepository() = LoginRepository()

    @Provides
    @Singleton
    fun providesLoginUseCase(loginRepository: LoginRepository) = mock(LoginUseCase::class.java)
}