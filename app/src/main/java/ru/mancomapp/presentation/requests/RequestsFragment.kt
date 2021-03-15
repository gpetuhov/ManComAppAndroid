package ru.mancomapp.presentation.requests

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_requests.*
import ru.mancomapp.R
import ru.mancomapp.domain.models.request.Request
import ru.mancomapp.utils.extensions.setVisible
import ru.mancomapp.utils.extensions.toast

class RequestsFragment : Fragment() {

    private lateinit var viewModel: RequestsViewModel
    private lateinit var requestsAdapter: RequestsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_requests, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeViewModel()

        back_button.setOnClickListener { navigateUp() }
        feedback_button.setOnClickListener { navigateToFeedbackFragment() }
        service_button.setOnClickListener { navigateToServiceFragment() }

        requests_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        requestsAdapter = RequestsAdapter()
        requests_list.adapter = requestsAdapter

        viewModel.loadRequestHistory()
    }

    private fun subscribeViewModel() {
        viewModel = ViewModelProvider(this).get(RequestsViewModel::class.java)
        viewModel.isRequestHistoryLoading.observe(viewLifecycleOwner, { isLoading -> showProgress(isLoading) })
        viewModel.isRequestHistoryError.observe(viewLifecycleOwner, { errorMessage -> toast(errorMessage) })
        viewModel.requestHistory.observe(viewLifecycleOwner, { requests -> updateRequestsUI(requests) })
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }

    private fun updateRequestsUI(requests: List<Request>) {
        requestsAdapter.submitList(requests)
        showRequests(requests.isNotEmpty())
        showRequestsEmpty(requests.isEmpty())
    }

    private fun showProgress(isVisible: Boolean) {
        load_requests_progress.setVisible(isVisible)
    }

    private fun showRequests(isVisible: Boolean) {
        requests_list.setVisible(isVisible)
    }

    private fun showRequestsEmpty(isVisible: Boolean) {
        requests_empty.setVisible(isVisible)
    }

    private fun navigateToFeedbackFragment() {
        val action = RequestsFragmentDirections.actionRequestsFragmentToFeedbackFragment()
        navigate(action)
    }

    private fun navigateToServiceFragment() {
        val action = RequestsFragmentDirections.actionRequestsFragmentToServiceFragment()
        navigate(action)
    }

    private fun navigate(action: NavDirections) {
        findNavController().navigate(action)
    }
}