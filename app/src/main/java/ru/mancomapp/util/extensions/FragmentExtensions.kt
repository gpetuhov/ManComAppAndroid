package ru.mancomapp.util.extensions

import android.app.Activity
import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import ru.mancomapp.R
import ru.mancomapp.source.local.AppConstants

fun Fragment.toast(@StringRes messageId: Int) = context?.let { Toast.makeText(context, messageId, Toast.LENGTH_SHORT).show() }

fun Fragment.toast(message: String) = context?.let { Toast.makeText(context, message, Toast.LENGTH_SHORT).show() }

fun Fragment.hideSoftKeyboard() = hideSoftKeyboard(activity, view)

fun Fragment.startPicker(requestCode: Int) {
    val intent = getPickerIntent(AppConstants.Files.IMAGE, arrayOf(AppConstants.Files.PDF))

    try {
        startActivityForResult(Intent.createChooser(intent, getString(R.string.complete_action_using)), requestCode)
        // Result will be passed into FRAGMENT onActivityResult()
    } catch (e: ActivityNotFoundException) {
        toast(R.string.add_file_error)
    }
}
private fun hideSoftKeyboard(activity: Activity?, view: View?) {
    val imm = activity?.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
    imm?.hideSoftInputFromWindow(view?.windowToken, 0)
}

private fun getPickerIntent(type: String, extraTypes: Array<String>): Intent {
    val intent = Intent(Intent.ACTION_GET_CONTENT)
    intent.type = type

    if (extraTypes.isNotEmpty()) intent.putExtra(Intent.EXTRA_MIME_TYPES, extraTypes)

    intent.putExtra(Intent.EXTRA_LOCAL_ONLY, true)
    return intent
}