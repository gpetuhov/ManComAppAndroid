package ru.mancomapp.presentation.requests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_management_request.view.*
import ru.mancomapp.R
import ru.mancomapp.domain.models.request.Request
import ru.mancomapp.domain.models.request.RequestStatus

class RequestsAdapter : ListAdapter<Request, RecyclerView.ViewHolder>(RequestsDiffCallback()) {

    companion object {
        private const val MANAGEMENT_REQUEST_TYPE = 0
        private const val SERVICE_REQUEST_TYPE = 1
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)

        return when (viewType) {
            MANAGEMENT_REQUEST_TYPE -> {
                val view = layoutInflater.inflate(R.layout.item_management_request, parent, false)
                ManagementRequestItemViewHolder(view)
            }
            SERVICE_REQUEST_TYPE -> {
                val view = layoutInflater.inflate(R.layout.item_management_request, parent, false)
                ServiceRequestItemViewHolder(view)
            }
            else -> throw IllegalStateException("Undefined ViewType")
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val request = getItem(position)

        when (holder) {
            is ManagementRequestItemViewHolder -> {
                holder.bind(request as Request.Management)
            }
            is ServiceRequestItemViewHolder -> {
                holder.bind(request as Request.Service)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Request.Management -> MANAGEMENT_REQUEST_TYPE
            is Request.Service -> SERVICE_REQUEST_TYPE
            else -> throw IllegalStateException("Undefined ViewType")
        }
    }

    private open class RequestItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        protected fun getRequestNumber(request: Request) =
            itemView.context.getString(R.string.request_number, request.id.toString())

        protected fun getRequestStatus(request: Request): String {
            val requestStatus = itemView.context.getString(
                when(request.status) {
                    RequestStatus.NEW -> R.string.request_status_new
                    RequestStatus.ON_REVIEW -> R.string.request_status_on_review
                    RequestStatus.COMPLETE -> R.string.request_status_complete
                }
            )
            return itemView.context.getString(R.string.request_status, requestStatus)
        }
    }

    private class ManagementRequestItemViewHolder(itemView: View) : RequestItemViewHolder(itemView) {
        fun bind(request: Request.Management) {
            val requestNumber = getRequestNumber(request)
            itemView.request_number.text = requestNumber
            itemView.request_title.text = request.title
            itemView.request_content.text = request.content
            itemView.request_status.text = getRequestStatus(request)
        }
    }

    private class ServiceRequestItemViewHolder(itemView: View) : RequestItemViewHolder(itemView) {
        fun bind(request: Request.Service) {
            val requestNumber = getRequestNumber(request)
            itemView.request_number.text = requestNumber
            itemView.request_title.text = itemView.context.getString(request.type.nameId)
            itemView.request_content.text = request.comment
            itemView.request_status.text = getRequestStatus(request)
        }
    }
}