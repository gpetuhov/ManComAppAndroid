package ru.mancomapp.presentation.feedback

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import ru.mancomapp.R

class FeedbackSendSuccessDialogFragment : DialogFragment() {

    companion object {
        private const val TAG = "FeedbackSendSuccessDialogFragment"

        fun show(fragmentManager: FragmentManager) {
            FeedbackSendSuccessDialogFragment().show(fragmentManager, TAG)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val layout: View = LayoutInflater.from(it).inflate(R.layout.dialog_feedback_send_success, null, false)
            builder
                .setView(layout)
                .setPositiveButton(R.string.ok) { dialog, id -> /* Do nothing */ }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
