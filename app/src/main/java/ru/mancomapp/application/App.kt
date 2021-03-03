package ru.mancomapp.application

import android.app.Application
import ru.mancomapp.util.Logger

class App: Application() {

    companion object {
        lateinit var application: App
    }

    override fun onCreate() {
        super.onCreate()

        Logger.init(applicationContext)
        Logger.log("App", "Starting application")

        application = this
    }
}