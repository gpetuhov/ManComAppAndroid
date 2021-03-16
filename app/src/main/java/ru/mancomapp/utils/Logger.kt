package ru.mancomapp.utils

import android.content.Context
import ru.mancomapp.BuildConfig
import timber.log.Timber
import zerobranch.androidremotedebugger.AndroidRemoteDebugger

class Logger {
    companion object {
        fun init(applicationContext: Context) {
            if (BuildConfig.DEBUG) {
                AndroidRemoteDebugger.init(applicationContext)

                Timber.plant(Timber.DebugTree(), object : Timber.Tree() {
                    override fun log(priority: Int, tag: String?, message: String, th: Throwable?) {
                        AndroidRemoteDebugger.Log.log(priority, tag, message, th)
                    }
                })
            }
        }

        fun log(tag: String, message: String) = Timber.tag(tag).d(message)

        fun error(tag: String, message: String) = Timber.tag(tag).e(message)
    }
}