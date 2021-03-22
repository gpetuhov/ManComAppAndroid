package ru.mancomapp.presentation.bills

import androidx.recyclerview.widget.DiffUtil
import ru.mancomapp.domain.models.bill.Bill

class BillsDiffCallback : DiffUtil.ItemCallback<Bill>() {
    override fun areItemsTheSame(oldItem: Bill, newItem: Bill): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Bill, newItem: Bill): Boolean {
        return oldItem.title == newItem.title
                && oldItem.total == newItem.total
                && oldItem.status == newItem.status
                && oldItem.fileName == newItem.fileName
                && oldItem.fileUrl == newItem.fileUrl
                && oldItem.date.equals(newItem.date)
    }
}