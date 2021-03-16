package ru.mancomapp.presentation.service

import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.dialog_choose_service_type.view.*
import ru.mancomapp.R
import ru.mancomapp.domain.models.service.ServiceType

class ChooseServiceDialogFragment(private val callback: Callback) : DialogFragment() {

    companion object {
        private const val TAG = "ChooseServiceDialogFragment"

        fun show(fragmentManager: FragmentManager, callback: Callback) {
            ChooseServiceDialogFragment(callback).show(fragmentManager, TAG)
        }
    }

    interface Callback {
        fun onSelectServiceType(serviceType: ServiceType)
    }

    private lateinit var serviceTypeAdapter: ServiceTypeAdapter
    private val serviceTypes = mutableListOf<ServiceType>()

    private val selectTypeCallback = object : ServiceTypeAdapter.Callback {
        override fun onServiceTypeClick(serviceType: ServiceType) {
            dialog?.dismiss()
            callback.onSelectServiceType(serviceType)
        }
    }

    init {
        initServiceTypes()
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        return activity?.let {
            val builder = AlertDialog.Builder(it)
            val layout: View = LayoutInflater.from(it).inflate(R.layout.dialog_choose_service_type, null, false)
            initLayout(layout)
            builder
                .setView(layout)
                .setNegativeButton(R.string.close) { dialog, id -> /* Do nothing */ }
                .create()
        } ?: throw IllegalStateException("Activity cannot be null")
    }

    private fun initServiceTypes() {
        serviceTypes.add(ServiceType.PLUMBER)
        serviceTypes.add(ServiceType.ELECTRICIAN)
        serviceTypes.add(ServiceType.CARPENTER)
        serviceTypes.add(ServiceType.OTHER)
    }

    private fun initLayout(layout: View) {
        with(layout) {
            choose_service_type_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
            serviceTypeAdapter = ServiceTypeAdapter(selectTypeCallback)
            choose_service_type_list.adapter = serviceTypeAdapter
            serviceTypeAdapter.submitList(serviceTypes)
        }
    }
}
