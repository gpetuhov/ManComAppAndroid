package ru.mancomapp.presentation.bills

import android.os.Bundle
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.alcon.fragment_bill.*
import kotlinx.android.synthetic.main.fragment_bill.back_button
import kotlinx.android.synthetic.main.fragment_bill.bill_date
import kotlinx.android.synthetic.main.fragment_bill.bill_details
import kotlinx.android.synthetic.main.fragment_bill.bill_number
import kotlinx.android.synthetic.main.fragment_bill.bill_payment_button
import kotlinx.android.synthetic.main.fragment_bill.bill_status
import kotlinx.android.synthetic.main.fragment_bill.bill_title
import kotlinx.android.synthetic.main.fragment_bill.bill_total
import ru.mancomapp.R
import ru.mancomapp.domain.models.bill.Bill
import ru.mancomapp.utils.extensions.openWebsite
import ru.mancomapp.utils.extensions.setVisible
import ru.mancomapp.utils.extensions.toast
import ru.mancomapp.utils.getLongFormattedDate

class BillFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_bill, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initBill()

        back_button.setOnClickListener { navigateUp() }
    }

    private fun initBill() {
        val bill = getBill()

        bill_number.text = getString(R.string.bill_number, bill.id)
        bill_status.text = getString(bill.status.detailsId)
        bill_date.text = getLongFormattedDate(bill.date)
        bill_total.text = getString(R.string.bill_total_rub, bill.total)
        bill_title.text = bill.title
        bill_details.text = bill.details
        bill_file_button?.text = getBillFileName(bill)

        bill_file_button?.setOnClickListener { openWebsite(bill.fileUrl) }

        bill_payment_button.setVisible(bill.isNotPaid())
        bill_payment_button.setOnClickListener { onPaymentButtonClick() }
    }

    private fun getBill(): Bill {
        val args = BillFragmentArgs.fromBundle(requireArguments())
        return args.bill
    }

    private fun onPaymentButtonClick() {
        // TODO: implement
        toast("Payment")
    }

    private fun navigateUp() = findNavController().navigateUp()

    private fun getBillFileName(bill: Bill): SpannableStringBuilder {
        val text = bill.fileName

        val spannableStringBuilder = SpannableStringBuilder(text)
        val underlineSpan = UnderlineSpan()
        spannableStringBuilder.setSpan(underlineSpan, 0, text.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
        return spannableStringBuilder
    }
}