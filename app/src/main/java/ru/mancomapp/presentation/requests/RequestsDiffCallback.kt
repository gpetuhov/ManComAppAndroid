package ru.mancomapp.presentation.requests

import androidx.recyclerview.widget.DiffUtil
import ru.mancomapp.domain.models.request.Request

class RequestsDiffCallback : DiffUtil.ItemCallback<Request>() {
    override fun areItemsTheSame(oldItem: Request, newItem: Request): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Request, newItem: Request): Boolean {
        return oldItem.title == newItem.title
                && oldItem.content == newItem.content
                && oldItem.status == newItem.status
    }
}