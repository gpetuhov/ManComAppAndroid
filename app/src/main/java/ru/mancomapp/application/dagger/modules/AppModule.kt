package ru.mancomapp.application.dagger.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.mancomapp.application.App
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(): Context = App.application.applicationContext
}