package ru.mancomapp.ui.requests

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_request.view.*
import ru.mancomapp.R
import ru.mancomapp.models.Request

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
            itemView.request_name.text = request.name
        }
    }
}