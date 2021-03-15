package ru.mancomapp.presentation.feedback

import androidx.recyclerview.widget.DiffUtil
import ru.mancomapp.models.Attachment

class AttachmentsDiffCallback : DiffUtil.ItemCallback<Attachment>() {
    override fun areItemsTheSame(oldItem: Attachment, newItem: Attachment): Boolean {
        return oldItem.uri == newItem.uri
    }

    override fun areContentsTheSame(oldItem: Attachment, newItem: Attachment): Boolean {
        return oldItem.name == newItem.name
    }
}