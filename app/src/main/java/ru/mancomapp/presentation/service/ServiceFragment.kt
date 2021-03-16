package ru.mancomapp.presentation.service

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_service.*
import ru.mancomapp.R
import ru.mancomapp.domain.models.Attachment
import ru.mancomapp.domain.models.service.ServiceType
import ru.mancomapp.presentation.feedback.AttachmentsAdapter
import ru.mancomapp.presentation.feedback.FeedbackSendSuccessDialogFragment
import ru.mancomapp.utils.extensions.hideSoftKeyboard
import ru.mancomapp.utils.extensions.setVisible
import ru.mancomapp.utils.extensions.startPicker
import ru.mancomapp.utils.extensions.toast

class ServiceFragment : Fragment() {

    companion object {
        private const val RC_PICKER = 1001
    }

    private lateinit var viewModel: ServiceViewModel
    private lateinit var attachmentsAdapter: AttachmentsAdapter

    private val serviceTypeCallback = object : ChooseServiceDialogFragment.Callback {
        override fun onSelectServiceType(serviceType: ServiceType) =
            viewModel.saveServiceType(serviceType)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_service, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeViewModel()

        back_button.setOnClickListener { navigateUp() }
        service_type.setOnClickListener { onServiceTypeClick() }
        add_files_button.setOnClickListener { onAddFilesButtonClick() }
        service_send_button.setOnClickListener { onSendButtonClick() }

        files_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        files_list.isNestedScrollingEnabled = false
        attachmentsAdapter = AttachmentsAdapter()
        files_list.adapter = attachmentsAdapter
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_PICKER && resultCode == Activity.RESULT_OK) {
            val fileUri  = data?.data
            viewModel.addAttachment(fileUri)
        }
    }

    private fun subscribeViewModel() {
        viewModel = ViewModelProvider(this).get(ServiceViewModel::class.java)
        viewModel.serviceType.observe(viewLifecycleOwner, { serviceType -> updateServiceTypeUI(serviceType) })
        viewModel.isSendStarted.observe(viewLifecycleOwner, { isStarted -> onSendStarted(isStarted) })
        viewModel.sendError.observe(viewLifecycleOwner, { errorMessageId -> toast(errorMessageId) })
        viewModel.isSendSuccess.observe(viewLifecycleOwner, { isSuccess -> onSendSuccess(isSuccess) })
        viewModel.attachments.observe(viewLifecycleOwner, { attachments -> updateAttachmentsUI(attachments) })
    }

    private fun updateServiceTypeUI(serviceType: ServiceType) {
        service_type_name.text = getString(serviceType.nameId)
    }

    private fun onSendStarted(isStarted: Boolean) {
        if (isStarted) hideSoftKeyboard()
        enableControls(!isStarted)
        showProgress(isStarted)
    }

    private fun enableControls(isEnabled: Boolean) {
        service_type.isEnabled = isEnabled
        service_comment_input.isEnabled = isEnabled
        add_files_button.isEnabled = isEnabled
        service_send_button.isEnabled = isEnabled
    }

    private fun showProgress(isVisible: Boolean) {
        service_send_progress.setVisible(isVisible)
    }

    private fun onSendSuccess(isSuccess: Boolean) {
        if (isSuccess) {
            FeedbackSendSuccessDialogFragment.show(parentFragmentManager)
            navigateUp()
        }
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }

    private fun updateAttachmentsUI(attachments: List<Attachment>) =
        attachmentsAdapter.submitList(attachments)

    private fun onServiceTypeClick() =
        ChooseServiceDialogFragment.show(parentFragmentManager, serviceTypeCallback)

    private fun onAddFilesButtonClick() {
        if (viewModel.isAddAttachmentsAllowed()) {
            startPicker(RC_PICKER)
        } else {
            toast(R.string.max_files_error)
        }
    }

    private fun onSendButtonClick() {
        // TODO: implement
        toast("Service request send")

        viewModel.send()
    }
}