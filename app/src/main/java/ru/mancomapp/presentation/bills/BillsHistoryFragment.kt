package ru.mancomapp.presentation.bills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_bills_history.*
import ru.mancomapp.R
import ru.mancomapp.domain.models.bill.Bill
import ru.mancomapp.domain.models.bill.BillStatus
import ru.mancomapp.domain.models.request.RequestDate
import ru.mancomapp.utils.extensions.setVisible
import ru.mancomapp.utils.extensions.toast

class BillsHistoryFragment : Fragment() {

    // TODO: remove this
    companion object {
        const val YEAR = 2021
        const val MONTH = 2
        const val DAY = 18
        const val TIME_IN_MILLIS = 1616056739495
    }

    private lateinit var billsAdapter: BillsAdapter

    private val onBillClickCallback = object : BillsAdapter.Callback {
        override fun onBillClick(bill: Bill) {
            // TODO: implement
            toast("Bill ${bill.id}")
        }
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bills_history, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        back_button.setOnClickListener { navigateUp() }

        bills_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        billsAdapter = BillsAdapter(onBillClickCallback)
        bills_list.adapter = billsAdapter

        // TODO: remove this
        val bills = getDummyBills()
        val unpaidBillsCount = bills.count { it.status == BillStatus.NOT_PAID }
        billsAdapter.submitList(bills)
        showUnpaidBillsStatus(unpaidBillsCount)
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

    // TODO: remove this
    private fun getDummyBills(): List<Bill> {
        val bills = mutableListOf<Bill>()

        (1..100).forEach { index ->
            val billDate = RequestDate().apply {
                year = YEAR
                month = MONTH
                day = DAY
                timeInMillis = TIME_IN_MILLIS
            }

            val bill = Bill().apply {
                id = index.toString()
                date = billDate
                title = "Ежемесячный счет за услуги УК"
                total = 1000
                status = BillStatus.PAID
                fileName = "Dummy.pdf"
                fileUrl = "https://www.w3.org/WAI/ER/tests/xhtml/testfiles/resources/pdf/dummy.pdf"
            }

            bills.add(bill)
        }

        return bills
    }
}