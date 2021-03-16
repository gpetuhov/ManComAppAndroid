package ru.mancomapp.presentation.service

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_service_type.view.*
import ru.mancomapp.R
import ru.mancomapp.domain.models.service.ServiceType

class ServiceTypeAdapter : ListAdapter<ServiceType, ServiceTypeAdapter.ServiceTypeViewHolder>(ServiceTypeDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ServiceTypeViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_service_type, parent, false)
        return ServiceTypeViewHolder(view)
    }

    override fun onBindViewHolder(holder: ServiceTypeViewHolder, position: Int) {
        val attachment = getItem(position)
        holder.bind(attachment)
    }

    class ServiceTypeViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(serviceType: ServiceType) {
            itemView.service_type.text = itemView.context.getString(serviceType.nameId)
        }
    }
}