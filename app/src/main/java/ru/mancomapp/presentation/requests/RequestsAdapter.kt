package ru.mancomapp.presentation.requests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_car_pass_request.view.*
import kotlinx.android.synthetic.main.item_management_request.view.*
import kotlinx.android.synthetic.main.item_person_pass_request.view.*
import ru.mancomapp.R
import ru.mancomapp.domain.models.request.Request
import ru.mancomapp.domain.models.request.RequestStatus
import ru.mancomapp.utils.getFormattedDate
import ru.mancomapp.utils.getLongFormattedDate

class RequestsAdapter : ListAdapter<Request, RecyclerView.ViewHolder>(RequestsDiffCallback()) {

    companion object {
        private const val MANAGEMENT_REQUEST_TYPE = 0
        private const val SERVICE_REQUEST_TYPE = 1
        private const val PERSON_PASS_REQUEST_TYPE = 2
        private const val CAR_PASS_REQUEST_TYPE = 3
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
            PERSON_PASS_REQUEST_TYPE -> {
                val view = layoutInflater.inflate(R.layout.item_person_pass_request, parent, false)
                PersonPassRequestItemViewHolder(view)
            }
            CAR_PASS_REQUEST_TYPE -> {
                val view = layoutInflater.inflate(R.layout.item_car_pass_request, parent, false)
                CarPassRequestItemViewHolder(view)
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
            is PersonPassRequestItemViewHolder -> {
                holder.bind(request as Request.PersonPass)
            }
            is CarPassRequestItemViewHolder -> {
                holder.bind(request as Request.CarPass)
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is Request.Management -> MANAGEMENT_REQUEST_TYPE
            is Request.Service -> SERVICE_REQUEST_TYPE
            is Request.PersonPass -> PERSON_PASS_REQUEST_TYPE
            is Request.CarPass -> CAR_PASS_REQUEST_TYPE
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
            with(itemView) {
                request_number.text = requestNumber
                request_date.text = getLongFormattedDate(request.date)
                request_title.text = request.title
                request_content.text = request.content
                request_status.text = getRequestStatus(request)
            }
        }
    }

    private class ServiceRequestItemViewHolder(itemView: View) : RequestItemViewHolder(itemView) {
        fun bind(request: Request.Service) {
            val requestNumber = getRequestNumber(request)
            with(itemView) {
                request_number.text = requestNumber
                request_date.text = getLongFormattedDate(request.date)
                request_title.text = itemView.context.getString(request.type.nameId)
                request_content.text = request.comment
                request_status.text = getRequestStatus(request)
            }
        }
    }

    private class PersonPassRequestItemViewHolder(itemView: View) : RequestItemViewHolder(itemView) {
        fun bind(request: Request.PersonPass) {
            val requestNumber = getRequestNumber(request)
            with(itemView) {
                request_person_pass_number.text = requestNumber
                request_person_pass_date.text = getFormattedDate(request.date)
                request_person_name.text = request.personName
                request_person_pass_access_type.text = itemView.context.getString(request.accessType.nameId)
                request_person_pass_status.text = getRequestStatus(request)
            }
        }
    }

    private class CarPassRequestItemViewHolder(itemView: View) : RequestItemViewHolder(itemView) {
        fun bind(request: Request.CarPass) {
            val requestNumber = getRequestNumber(request)
            with(itemView) {
                request_car_pass_number.text = requestNumber
                request_car_pass_date.text = getFormattedDate(request.date)
                request_car_model.text = request.carModel
                request_car_number.text = request.carNumber
                request_car_pass_access_type.text = itemView.context.getString(request.accessType.nameId)
                request_car_pass_status.text = getRequestStatus(request)
            }
        }
    }
}