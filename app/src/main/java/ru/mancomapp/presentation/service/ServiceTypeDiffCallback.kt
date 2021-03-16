package ru.mancomapp.presentation.service

import androidx.recyclerview.widget.DiffUtil
import ru.mancomapp.domain.models.service.ServiceType

class ServiceTypeDiffCallback : DiffUtil.ItemCallback<ServiceType>() {
    override fun areItemsTheSame(oldItem: ServiceType, newItem: ServiceType): Boolean {
        return oldItem.name == newItem.name
    }

    override fun areContentsTheSame(oldItem: ServiceType, newItem: ServiceType): Boolean {
        return oldItem.nameId == newItem.nameId
    }
}