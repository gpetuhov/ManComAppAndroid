package ru.mancomapp.ui.feedback

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_attachment.view.*
import ru.mancomapp.R
import ru.mancomapp.models.Attachment

class AttachmentsAdapter : ListAdapter<Attachment, AttachmentsAdapter.AttachmentViewHolder>(AttachmentsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AttachmentViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_attachment, parent, false)
        return AttachmentViewHolder(view)
    }

    override fun onBindViewHolder(holder: AttachmentViewHolder, position: Int) {
        val attachment = getItem(position)
        holder.bind(attachment)
    }

    class AttachmentViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(attachment: Attachment) {
            itemView.attachment_name.text = attachment.name
        }
    }
}