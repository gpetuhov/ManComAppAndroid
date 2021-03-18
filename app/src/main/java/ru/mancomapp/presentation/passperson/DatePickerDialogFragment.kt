package ru.mancomapp.presentation.passperson

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import ru.mancomapp.domain.models.pass.PassDate
import java.util.*

class DatePickerDialogFragment(
    private val callback: Callback,
    private val selectedPassDate: PassDate
) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    companion object {
        private const val TAG = "DatePickerDialogFragment"

        fun show(
            fragmentManager: FragmentManager,
            selectedPassDate: PassDate,
            callback: Callback
        ) {
            DatePickerDialogFragment(callback, selectedPassDate).show(fragmentManager, TAG)
        }
    }

    interface Callback {
        fun onDateSelected(passDate: PassDate)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val year: Int
            val month: Int
            val day: Int

            if (selectedPassDate.isEmpty()) {
                val calendar = Calendar.getInstance()
                year = calendar.get(Calendar.YEAR)
                month = calendar.get(Calendar.MONTH)
                day = calendar.get(Calendar.DAY_OF_MONTH)
            } else {
                year = selectedPassDate.year
                month = selectedPassDate.month
                day = selectedPassDate.day
            }

            DatePickerDialog(it, this, year, month, day)
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val timeInMillis = calendar.timeInMillis

        val passDate = PassDate().apply {
            this.year = year
            this.month = month
            this.day = day
            this.timeInMillis = timeInMillis
        }

        callback.onDateSelected(passDate)
    }
}
