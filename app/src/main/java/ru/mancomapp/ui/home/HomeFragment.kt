package ru.mancomapp.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_home.*
import ru.mancomapp.R
import ru.mancomapp.util.extensions.toast

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        request_button.setOnClickListener {
            // TODO
            toast(R.string.management_company_requests)
        }

        security_button.setOnClickListener {
            // TODO
            toast(R.string.pass_and_security)
        }

        payments_button.setOnClickListener {
            // TODO
            toast(R.string.payments)
        }
    }
}