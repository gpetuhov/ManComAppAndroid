package ru.mancomapp.util.extensions

import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.toast(@StringRes messageId: Int) = context?.let { Toast.makeText(context, messageId, Toast.LENGTH_SHORT).show() }

fun Fragment.toast(message: String) = context?.let { Toast.makeText(context, message, Toast.LENGTH_SHORT).show() }
