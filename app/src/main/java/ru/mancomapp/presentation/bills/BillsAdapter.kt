package ru.mancomapp.presentation.bills

import android.graphics.Typeface
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.style.StyleSpan
import android.text.style.UnderlineSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_bill.view.*
import ru.mancomapp.R
import ru.mancomapp.domain.models.bill.Bill
import ru.mancomapp.utils.getLongFormattedDate

class BillsAdapter(
    private val callback: Callback
) : ListAdapter<Bill, BillsAdapter.BillViewHolder>(BillsDiffCallback()) {

    interface Callback {
        fun onBillClick(bill: Bill)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BillViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_bill, parent, false)
        return BillViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: BillViewHolder, position: Int) {
        val bill = getItem(position)
        holder.bind(bill)
    }

    class BillViewHolder(
        itemView: View,
        private val callback: Callback
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(bill: Bill) {
            itemView.bill_number.text = getBillNumber(bill)
            itemView.bill_date.text = getLongFormattedDate(bill.date)
            itemView.bill_title.text = bill.title
            itemView.bill_total.text = itemView.context.getString(R.string.bill_total_rub, bill.total)
            itemView.bill_status.text = getBillStatus(bill)
            itemView.bill_root.setOnClickListener { callback.onBillClick(bill) }
        }

        private fun getBillNumber(bill: Bill): SpannableStringBuilder {
            val text = itemView.context.getString(R.string.bills_number, bill.id)

            val spannableStringBuilder = SpannableStringBuilder(text)
            val underlineSpan = UnderlineSpan()
            spannableStringBuilder.setSpan(underlineSpan, 0, text.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            return spannableStringBuilder
        }

        private fun getBillStatus(bill: Bill): SpannableStringBuilder {
            val part1 = itemView.context.getString(R.string.status)
            val part2 = itemView.context.getString(bill.status.detailsId).toLowerCase()
            val text = "$part1 $part2"

            val spannableStringBuilder = SpannableStringBuilder(text)
            val bold = StyleSpan(Typeface.BOLD)
            spannableStringBuilder.setSpan(bold, part1.length + 1, text.length, Spannable.SPAN_INCLUSIVE_INCLUSIVE)
            return spannableStringBuilder
        }
    }
}