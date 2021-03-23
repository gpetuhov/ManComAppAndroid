package ru.mancomapp.presentation.bills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import kotlinx.android.synthetic.main.fragment_bill.*
import ru.mancomapp.R
import ru.mancomapp.utils.extensions.openWebsite
import ru.mancomapp.utils.extensions.setVisible
import ru.mancomapp.utils.extensions.toast
import ru.mancomapp.utils.getFormattedDate

class BillFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bill, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val args = BillFragmentArgs.fromBundle(requireArguments())
        val bill = args.bill

        bill_number.text = getString(R.string.bill_number, bill.id)
        bill_status.text = getString(bill.status.detailsId)
        bill_date.text = getFormattedDate(bill.date)
        bill_total.text = getString(R.string.bill_total_rub, bill.total)
        bill_title.text = bill.title
        bill_details.text = bill.fileName

        bill_details.setOnClickListener { openWebsite(bill.fileUrl) }

        bill_payment_button.setVisible(bill.isNotPaid())
        bill_payment_button.setOnClickListener { onPaymentButtonClick() }
    }

    private fun onPaymentButtonClick() {
        // TODO: implement
        toast("Payment")
    }
}