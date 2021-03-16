package ru.mancomapp.presentation.service

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_service_type.view.*
import ru.mancomapp.R
import ru.mancomapp.domain.models.service.ServiceType

class ServiceTypeAdapter(
    private val callback: Callback
) : ListAdapter<ServiceType, ServiceTypeAdapter.ServiceTypeViewHolder>(ServiceTypeDiffCallback()) {

    interface Callback {
        fun onServiceTypeClick(serviceType: ServiceType)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceTypeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_service_type, parent, false)
        return ServiceTypeViewHolder(view, callback)
    }

    override fun onBindViewHolder(holder: ServiceTypeViewHolder, position: Int) {
        val serviceType = getItem(position)
        holder.bind(serviceType)
    }

    class ServiceTypeViewHolder(
        itemView: View,
        private val callback: Callback
    ) : RecyclerView.ViewHolder(itemView) {

        fun bind(serviceType: ServiceType) {
            itemView.service_type_name.text = itemView.context.getString(serviceType.nameId)
            itemView.service_type_root.setOnClickListener { callback.onServiceTypeClick(serviceType) }
        }
    }
}