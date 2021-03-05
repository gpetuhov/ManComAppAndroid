package ru.mancomapp.util.extensions

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment

fun Fragment.toast(@StringRes messageId: Int) = context?.let { Toast.makeText(context, messageId, Toast.LENGTH_SHORT).show() }

fun Fragment.toast(message: String) = context?.let { Toast.makeText(context, message, Toast.LENGTH_SHORT).show() }

fun Fragment.hideSoftKeyboard() = hideSoftKeyboard(activity, view)

private fun hideSoftKeyboard(activity: Activity?, view: View?) {
    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.hideSoftInputFromWindow(view?.windowToken, 0)
}