package ru.mancomapp.presentation.service

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
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

        private val serviceTypeName: TextView = itemView.findViewById(R.id.service_type_name)
        private val serviceTypeRoot: View = itemView.findViewById(R.id.service_type_root)

        fun bind(serviceType: ServiceType) {
            serviceTypeName.text = itemView.context.getString(serviceType.nameId)
            serviceTypeRoot.setOnClickListener { callback.onServiceTypeClick(serviceType) }
        }
    }
}