package ru.mancomapp.presentation.passperson

import android.app.DatePickerDialog
import android.app.Dialog
import android.os.Bundle
import android.widget.DatePicker
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import ru.mancomapp.domain.models.request.RequestDate
import java.util.*

class DatePickerDialogFragment(
    private val callback: Callback,
    private val selectedRequestDate: RequestDate
) : DialogFragment(), DatePickerDialog.OnDateSetListener {

    companion object {
        private const val TAG = "DatePickerDialogFragment"

        fun show(
            fragmentManager: FragmentManager,
            selectedRequestDate: RequestDate,
            callback: Callback
        ) {
            DatePickerDialogFragment(callback, selectedRequestDate).show(fragmentManager, TAG)
        }
    }

    interface Callback {
        fun onDateSelected(requestDate: RequestDate)
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val year: Int
            val month: Int
            val day: Int

            if (selectedRequestDate.isEmpty()) {
                val calendar = Calendar.getInstance()
                year = calendar.get(Calendar.YEAR)
                month = calendar.get(Calendar.MONTH)
                day = calendar.get(Calendar.DAY_OF_MONTH)
            } else {
                year = selectedRequestDate.year
                month = selectedRequestDate.month
                day = selectedRequestDate.day
            }

            DatePickerDialog(it, this, year, month, day)
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    override fun onDateSet(view: DatePicker, year: Int, month: Int, day: Int) {
        val calendar = Calendar.getInstance()
        calendar.set(year, month, day)
        val timeInMillis = calendar.timeInMillis

        val passDate = RequestDate().apply {
            this.year = year
            this.month = month
            this.day = day
            this.timeInMillis = timeInMillis
        }

        callback.onDateSelected(passDate)
    }
}
