package ru.mancomapp.presentation.bills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_bills_history.*
import ru.mancomapp.R
import ru.mancomapp.domain.models.bill.Bill
import ru.mancomapp.domain.models.bill.BillStatus
import ru.mancomapp.utils.extensions.setVisible
import ru.mancomapp.utils.extensions.toast

class BillsHistoryFragment : Fragment() {

    private lateinit var billsAdapter: BillsAdapter
    private lateinit var viewModel: BillsViewModel

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

        initBillList()
        subscribeViewModel()

        back_button.setOnClickListener { navigateUp() }

        viewModel.loadBillHistory()
    }

    private fun initBillList() {
        bills_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        billsAdapter = BillsAdapter(onBillClickCallback)
        bills_list.adapter = billsAdapter
    }

    private fun subscribeViewModel() {
        viewModel = ViewModelProvider(this).get(BillsViewModel::class.java)
        viewModel.isBillHistoryLoading.observe(viewLifecycleOwner, { isLoading -> showProgress(isLoading) })
        viewModel.billHistoryError.observe(viewLifecycleOwner, { errorMessageId -> toast(errorMessageId) })
        viewModel.billHistory.observe(viewLifecycleOwner, { bills -> updateBillsUI(bills) })
    }

    private fun showProgress(isVisible: Boolean) = load_bills_progress.setVisible(isVisible)

    private fun updateBillsUI(bills: List<Bill>) {
        showUnpaidBillsStatus(bills)
        billsAdapter.submitList(bills)
        showBills(bills.isNotEmpty())
        showBillsEmpty(bills.isEmpty())
    }

    private fun showUnpaidBillsStatus(bills: List<Bill>) {
        val unpaidBillsCount = bills.count { it.status == BillStatus.NOT_PAID }

        val message = if (unpaidBillsCount == 0) {
            getString(R.string.all_bills_paid)
        } else {
            getString(R.string.unpaid_bills_count, unpaidBillsCount)
        }

        unpaid_bills_status.text = message
        unpaid_bills_status.setVisible(true)
    }

    private fun showBills(isVisible: Boolean) = bills_list.setVisible(isVisible)

    private fun showBillsEmpty(isVisible: Boolean) = bills_empty.setVisible(isVisible)

    private fun navigateUp() = findNavController().navigateUp()

    private fun navigateToBillFragment() {
        val action = BillsHistoryFragmentDirections.actionBillsHistoryFragmentToBillFragment()
        navigate(action)
    }

    private fun navigate(action: NavDirections) = findNavController().navigate(action)
}