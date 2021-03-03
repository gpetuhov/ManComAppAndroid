package ru.mancomapp.application

import android.app.Application
import ru.mancomapp.BuildConfig
import timber.log.Timber
import zerobranch.androidremotedebugger.AndroidRemoteDebugger

class App: Application() {

    companion object {
        lateinit var application: App
    }

    override fun onCreate() {
        super.onCreate()

        // TODO: refactor this into Logger
        if (BuildConfig.DEBUG) {
            AndroidRemoteDebugger.init(applicationContext)

            Timber.plant(Timber.DebugTree(), object : Timber.Tree() {
                override fun log(priority: Int, tag: String?, message: String, th: Throwable?) {
                    AndroidRemoteDebugger.Log.log(priority, tag, message, th)
                }
            })
        }

        application = this
    }
}