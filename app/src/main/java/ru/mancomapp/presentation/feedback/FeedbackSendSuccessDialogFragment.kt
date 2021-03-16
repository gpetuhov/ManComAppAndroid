package ru.mancomapp.presentation.feedback

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import kotlinx.android.synthetic.main.dialog_feedback_send_success.view.*
import ru.mancomapp.R

class FeedbackSendSuccessDialogFragment(private val type: FeedbackSendSuccessDialogType) : DialogFragment() {

    companion object {
        private const val TAG = "FeedbackSendSuccessDialogFragment"

        fun show(fragmentManager: FragmentManager, type: FeedbackSendSuccessDialogType) {
            FeedbackSendSuccessDialogFragment(type).show(fragmentManager, TAG)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val layout: View = LayoutInflater.from(it).inflate(R.layout.dialog_feedback_send_success, null, false)
            initLayout(layout)
            builder
                .setView(layout)
                .setPositiveButton(R.string.ok) { dialog, id -> /* Do nothing */ }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun initLayout(layout: View) {
        val message = getString(
            when(type) {
                FeedbackSendSuccessDialogType.FEEDBACK -> R.string.feedback_send_success
                FeedbackSendSuccessDialogType.SERVICE_REQUEST -> R.string.service_request_send_success
            }
        )
        layout.feedback_send_success_text.text = message
    }
}
