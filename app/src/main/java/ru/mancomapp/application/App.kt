package ru.mancomapp.application

import android.app.Application
import ru.mancomapp.application.dagger.components.AppComponent
import ru.mancomapp.application.dagger.components.DaggerAppComponent
import ru.mancomapp.util.Logger

class App: Application() {

    companion object {
        lateinit var application: App
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()

        Logger.init(applicationContext)

        application = this
        appComponent = DaggerAppComponent.builder().build()
    }
}