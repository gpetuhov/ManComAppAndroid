package ru.mancomapp.presentation.security

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
import kotlinx.android.synthetic.main.fragment_security.*
import ru.mancomapp.R
import ru.mancomapp.domain.models.request.Request
import ru.mancomapp.presentation.requests.RequestsAdapter
import ru.mancomapp.utils.extensions.setVisible
import ru.mancomapp.utils.extensions.toast

class SecurityFragment : Fragment() {

    private lateinit var viewModel: SecurityViewModel
    private lateinit var requestsAdapter: RequestsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_security, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeViewModel()

        back_button.setOnClickListener { navigateUp() }
        person_pass_button.setOnClickListener { navigateToPersonPassFragment() }
        car_pass_button.setOnClickListener { navigateToCarPassFragment() }

        security_requests_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        requestsAdapter = RequestsAdapter()
        security_requests_list.adapter = requestsAdapter

        viewModel.loadRequestHistory()
    }

    private fun subscribeViewModel() {
        viewModel = ViewModelProvider(this).get(SecurityViewModel::class.java)
        viewModel.isRequestHistoryLoading.observe(viewLifecycleOwner, { isLoading -> showProgress(isLoading) })
        viewModel.requestHistoryError.observe(viewLifecycleOwner, { errorMessageId -> toast(errorMessageId) })
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
        load_security_requests_progress.setVisible(isVisible)
    }

    private fun showRequests(isVisible: Boolean) {
        security_requests_list.setVisible(isVisible)
    }

    private fun showRequestsEmpty(isVisible: Boolean) {
        security_requests_empty.setVisible(isVisible)
    }

    private fun navigateToPersonPassFragment() {
        val action = SecurityFragmentDirections.actionSecurityFragmentToPersonPassFragment()
        navigate(action)
    }

    private fun navigateToCarPassFragment() {
        val action = SecurityFragmentDirections.actionSecurityFragmentToCarPassFragment()
        navigate(action)
    }

    private fun navigate(action: NavDirections) {
        findNavController().navigate(action)
    }
}