package ru.mancomapp.application.dagger.modules

import android.content.Context
import dagger.Module
import dagger.Provides
import ru.mancomapp.application.App
import ru.mancomapp.source.local.AppPrefs
import javax.inject.Singleton

@Module
class AppModule {

    @Provides
    @Singleton
    fun providesContext(): Context = App.application.applicationContext

    @Provides
    @Singleton
    fun providesSettings(context: Context) = AppPrefs(context)
}