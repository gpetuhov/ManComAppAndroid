package ru.mancomapp.presentation.security

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_security.*
import kotlinx.android.synthetic.main.fragment_security.back_button
import ru.mancomapp.R
import ru.mancomapp.presentation.requests.RequestsAdapter

class SecurityFragment : Fragment() {

    private lateinit var requestsAdapter: RequestsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_security, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_button.setOnClickListener { navigateUp() }
        person_pass_button.setOnClickListener { navigateToPersonPassFragment() }
        car_pass_button.setOnClickListener { navigateToCarPassFragment() }

        security_requests_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        requestsAdapter = RequestsAdapter()
        security_requests_list.adapter = requestsAdapter
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }

    private fun navigateToPersonPassFragment() {
        val action = SecurityFragmentDirections.actionSecurityFragmentToPersonPassFragment()
        navigate(action)
    }

    private fun navigateToCarPassFragment() {
        val action = SecurityFragmentDirections.actionSecurityFragmentToCarPassFragment()
        navigate(action)
    }

    private fun navigate(action: NavDirections) {
        findNavController().navigate(action)
    }
}