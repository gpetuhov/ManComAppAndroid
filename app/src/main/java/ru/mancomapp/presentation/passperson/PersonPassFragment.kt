package ru.mancomapp.presentation.passperson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_person_pass.*
import ru.mancomapp.R
import ru.mancomapp.domain.models.pass.PassDate
import ru.mancomapp.utils.extensions.toast

class PersonPassFragment : Fragment() {

    private val passDateCallback = object : DatePickerDialogFragment.Callback {
        override fun onDateSelected(passDate: PassDate) {
            // TODO: save selected date
            toast("${passDate.year}.${passDate.month}.${passDate.day}")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_person_pass, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_button.setOnClickListener { navigateUp() }
        person_pass_date_input.setOnClickListener { onSelectDateClick() }
        person_pass_access_type.setOnClickListener { onSelectAccessTypeClick() }
        person_pass_send_button.setOnClickListener { onSendButtonClick() }
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }

    private fun onSelectDateClick() {
        DatePickerDialogFragment.show(parentFragmentManager, passDateCallback)

        // TODO: handle result
    }

    private fun onSelectAccessTypeClick() {
        // TODO: implement
        toast("Select access type")
    }

    private fun onSendButtonClick() {
        // TODO: implement
        toast("Send")
    }
}