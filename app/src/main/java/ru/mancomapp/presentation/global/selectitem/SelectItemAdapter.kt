package ru.mancomapp.presentation.global.selectitem

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.mancomapp.R

class SelectItemAdapter(
    private val callback: Callback
) : ListAdapter<SelectItem, SelectItemAdapter.SelectItemViewHolder>(SelectItemDiffCallback()) {

    interface Callback {
        fun onItemClick(item: SelectItem)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_select, parent, false)
        return SelectItemViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: SelectItemViewHolder, position: Int) {
        val serviceType = getItem(position)
        holder.bind(serviceType)
    }

    class SelectItemViewHolder(
        itemView: View,
        private val callback: Callback
    ) : RecyclerView.ViewHolder(itemView) {

        private val name: TextView = itemView.findViewById(R.id.select_item_name)
        private val root: View = itemView.findViewById(R.id.select_item_root)

        fun bind(item: SelectItem) {
            name.text = item.name
            root.setOnClickListener { callback.onItemClick(item) }
        }
    }
}