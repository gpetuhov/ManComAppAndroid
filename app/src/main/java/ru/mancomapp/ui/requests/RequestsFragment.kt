package ru.mancomapp.ui.requests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_requests.*
import ru.mancomapp.R
import ru.mancomapp.util.extensions.toast

class RequestsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_requests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back_button.setOnClickListener { navigateUp() }

        feedback_button.setOnClickListener {
            // TODO
            toast(R.string.write_feedback)
        }

        service_button.setOnClickListener {
            // TODO
            toast(R.string.order_service)
        }
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }
}