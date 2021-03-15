package ru.mancomapp.di.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.mancomapp.App
import ru.mancomapp.data.repository.LoginRepository
import ru.mancomapp.data.source.local.AppPrefs
import ru.mancomapp.domain.usecase.login.LoginUseCase
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
    fun providesLoginUseRepository() = LoginRepository()

    @Provides
    @Singleton
    fun providesLoginUseCase(loginRepository: LoginRepository) = LoginUseCase()
}