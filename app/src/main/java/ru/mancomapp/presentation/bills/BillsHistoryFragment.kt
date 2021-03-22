package ru.mancomapp.presentation.bills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_bills_history.*
import ru.mancomapp.R
import ru.mancomapp.utils.extensions.setVisible

class BillsHistoryFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bills_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_button.setOnClickListener { navigateUp() }
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }

    private fun navigateToBillFragment() {
        val action = BillsHistoryFragmentDirections.actionBillsHistoryFragmentToBillFragment()
        navigate(action)
    }

    private fun navigate(action: NavDirections) {
        findNavController().navigate(action)
    }

    private fun showUnpaidBillsStatus(unpaidBillsCount: Int) {
        val message = if (unpaidBillsCount == 0) {
            getString(R.string.all_bills_paid)
        } else {
            getString(R.string.unpaid_bills_count, unpaidBillsCount)
        }

        unpaid_bills_status.text = message
        unpaid_bills_status.setVisible(true)
    }
}