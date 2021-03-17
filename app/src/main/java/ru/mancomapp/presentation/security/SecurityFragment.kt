package ru.mancomapp.presentation.security

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_security.*
import ru.mancomapp.R
import ru.mancomapp.utils.extensions.toast

class SecurityFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_security, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_button.setOnClickListener { navigateUp() }
        person_pass_button.setOnClickListener { navigateToPersonPassFragment() }
        car_pass_button.setOnClickListener { navigateToCarPassFragment() }
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }

    private fun navigateToPersonPassFragment() {
        // TODO
        toast("Person pass")
    }

    private fun navigateToCarPassFragment() {
        // TODO
        toast("Car pass")
    }
}