package ru.mancomapp.presentation.global.selectitem

import androidx.recyclerview.widget.DiffUtil

class SelectItemDiffCallback : DiffUtil.ItemCallback<SelectItem>() {
    override fun areItemsTheSame(oldItem: SelectItem, newItem: SelectItem): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: SelectItem, newItem: SelectItem): Boolean {
        return oldItem.name == newItem.name
    }

}