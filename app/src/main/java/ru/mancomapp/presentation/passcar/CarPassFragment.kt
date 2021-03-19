package ru.mancomapp.presentation.passcar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_car_pass.*
import ru.mancomapp.R
import ru.mancomapp.utils.extensions.toast

class CarPassFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_car_pass, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_button.setOnClickListener { navigateUp() }
        car_pass_date_input.setOnClickListener { onSelectDateClick() }
        car_pass_access_type.setOnClickListener { onSelectAccessTypeClick() }
        car_pass_send_button.setOnClickListener { onSendButtonClick() }
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }

    private fun onSelectDateClick() {
        // TODO
        toast("Select date")
    }

    private fun onSelectAccessTypeClick() {
        // TODO: implement
        toast("Select access type")
    }

    private fun onSendButtonClick() {
        // TODO
        toast("Send")
    }
}