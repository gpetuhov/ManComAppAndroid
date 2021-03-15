package ru.mancomapp

import android.app.Application
import ru.mancomapp.di.components.AppComponent
import ru.mancomapp.di.components.DaggerAppComponent
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