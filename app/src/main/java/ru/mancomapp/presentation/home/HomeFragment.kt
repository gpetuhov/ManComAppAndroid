package ru.mancomapp.presentation.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_home.*
import ru.mancomapp.R

class HomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        request_button.setOnClickListener { navigateToRequestsFragment() }
        security_button.setOnClickListener { navigateToSecurityFragment() }
        payments_button.setOnClickListener { navigateToPaymentsFragment() }
    }

    private fun navigateToRequestsFragment() {
        val action = HomeFragmentDirections.actionHomeFragmentToRequestsFragment()
        navigate(action)
    }

    private fun navigateToSecurityFragment() {
        val action = HomeFragmentDirections.actionHomeFragmentToSecurityFragment()
        navigate(action)
    }

    private fun navigateToPaymentsFragment() {
        val action = HomeFragmentDirections.actionHomeFragmentToPaymentsFragment()
        navigate(action)
    }

    private fun navigate(action: NavDirections) {
        findNavController().navigate(action)
    }
}