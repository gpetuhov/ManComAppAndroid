package ru.mancomapp.util

import android.content.Context
import androidx.core.content.edit
import androidx.preference.PreferenceManager

class Settings(context: Context) {

    companion object {
        private const val IS_LOGGED_IN = "isLoggedIn"
    }

    private val prefs = PreferenceManager.getDefaultSharedPreferences(context)

    fun isLoggedIn() = prefs.getBoolean(IS_LOGGED_IN, false)

    fun setIsLoggedIn(value: Boolean) = prefs.edit { putBoolean(IS_LOGGED_IN, value) }
}