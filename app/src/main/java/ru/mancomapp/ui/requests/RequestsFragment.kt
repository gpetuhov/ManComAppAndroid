package ru.mancomapp.ui.requests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_requests.*
import ru.mancomapp.R
import ru.mancomapp.models.Request
import ru.mancomapp.util.extensions.toast

class RequestsFragment : Fragment() {

    private lateinit var requestsAdapter: RequestsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_requests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back_button.setOnClickListener { navigateUp() }

        feedback_button.setOnClickListener {
            // TODO
            toast(R.string.write_feedback)
        }

        service_button.setOnClickListener {
            // TODO
            toast(R.string.order_service)
        }

        requests_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        requestsAdapter = RequestsAdapter()
        requests_list.adapter = requestsAdapter

        loadDummyRequests()
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }

    // TODO: remove this
    private fun loadDummyRequests() {
        val requests = mutableListOf<Request>()

        (1..100).forEach {
            requests.add(Request(it, "Заявка $it"))
        }

        requestsAdapter.submitList(requests)
    }
}