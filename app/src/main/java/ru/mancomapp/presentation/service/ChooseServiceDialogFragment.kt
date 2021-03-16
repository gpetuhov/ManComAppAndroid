package ru.mancomapp.presentation.service

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import ru.mancomapp.R

class ChooseServiceDialogFragment : DialogFragment() {

    companion object {
        private const val TAG = "ChooseServiceDialogFragment"

        fun show(fragmentManager: FragmentManager) {
            ChooseServiceDialogFragment().show(fragmentManager, TAG)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val layout: View = LayoutInflater.from(it).inflate(R.layout.dialog_choose_service_type, null, false)
            builder
                .setView(layout)
                .setNegativeButton(R.string.close) { dialog, id -> /* Do nothing */ }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }
}
