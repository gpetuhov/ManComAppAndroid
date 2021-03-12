package ru.mancomapp.ui.requests

import androidx.recyclerview.widget.DiffUtil
import ru.mancomapp.models.request.Request

class RequestsDiffCallback : DiffUtil.ItemCallback<Request>() {
    override fun areItemsTheSame(oldItem: Request, newItem: Request): Boolean {
        return oldItem.id == newItem.id
    }

    // TODO: change this, when Request fields added
    override fun areContentsTheSame(oldItem: Request, newItem: Request): Boolean {
        return oldItem.title == newItem.title
    }
}