package ru.mancomapp.presentation.requests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_request.view.*
import ru.mancomapp.R
import ru.mancomapp.domain.models.request.Request
import ru.mancomapp.domain.models.request.RequestStatus

class RequestsAdapter : ListAdapter<Request, RequestsAdapter.RequestItemViewHolder>(RequestsDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RequestItemViewHolder {
        val layoutInflater = LayoutInflater.from(parent.context)
        val view = layoutInflater.inflate(R.layout.item_request, parent, false)
        return RequestItemViewHolder(view)
    }

    override fun onBindViewHolder(holder: RequestItemViewHolder, position: Int) {
        val request = getItem(position)
        holder.bind(request)
    }

    class RequestItemViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        fun bind(request: Request) {
            val requestNumber = itemView.context.getString(R.string.request_number, request.id.toString())
            itemView.request_number.text = requestNumber
            itemView.request_title.text = request.title
            itemView.request_content.text = request.content

            val requestStatus = itemView.context.getString(
                when(request.status) {
                    RequestStatus.NEW -> R.string.request_status_new
                    RequestStatus.ON_REVIEW -> R.string.request_status_on_review
                    RequestStatus.COMPLETE -> R.string.request_status_complete
                }
            )
            val requestStatusText = itemView.context.getString(R.string.request_status, requestStatus)
            itemView.request_status.text = requestStatusText
        }
    }
}