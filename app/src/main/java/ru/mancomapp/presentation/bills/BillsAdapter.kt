package ru.mancomapp.presentation.bills

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_bill.view.*
import ru.mancomapp.R
import ru.mancomapp.domain.models.bill.Bill
import ru.mancomapp.utils.getFormattedDate

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
            itemView.bill_number.text = itemView.context.getString(R.string.bills_number, bill.id)
            itemView.bill_date.text = getFormattedDate(bill.date)
            itemView.bill_title.text = bill.title
            itemView.bill_total.text = itemView.context.getString(R.string.bill_total_rub, bill.total)
            itemView.bill_status.text = itemView.context.getString(bill.status.nameId)
            itemView.bill_root.setOnClickListener { callback.onBillClick(bill) }
        }
    }
}