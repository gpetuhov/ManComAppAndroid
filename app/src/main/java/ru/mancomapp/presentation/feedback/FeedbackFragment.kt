package ru.mancomapp.presentation.feedback

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_feedback.*
import ru.mancomapp.R
import ru.mancomapp.domain.models.Attachment
import ru.mancomapp.domain.models.Feedback
import ru.mancomapp.utils.extensions.hideSoftKeyboard
import ru.mancomapp.utils.extensions.setVisible
import ru.mancomapp.utils.extensions.startPicker
import ru.mancomapp.utils.extensions.toast

class FeedbackFragment : Fragment() {

    companion object {
        private const val RC_PICKER = 1001
    }

    private lateinit var viewModel: FeedbackViewModel
    private lateinit var attachmentsAdapter: AttachmentsAdapter

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feedback, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeViewModel()

        back_button.setOnClickListener { navigateUp() }
        add_files_button.setOnClickListener { onAddFilesButtonClick() }
        feedback_send_button.setOnClickListener { onSendButtonClick() }

        initAttachmentList()
        initBackPressedCallback()
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_PICKER && resultCode == Activity.RESULT_OK) {
            val fileUri  = data?.data
            viewModel.addAttachment(fileUri)
        }
    }

    private fun subscribeViewModel() {
        viewModel = ViewModelProvider(this).get(FeedbackViewModel::class.java)
        viewModel.isSendStarted.observe(viewLifecycleOwner, { isStarted -> onSendStarted(isStarted) })
        viewModel.sendError.observe(viewLifecycleOwner, { errorMessage -> toast(errorMessage) })
        viewModel.isSendSuccess.observe(viewLifecycleOwner, { isSuccess -> onSendSuccess(isSuccess) })
        viewModel.attachments.observe(viewLifecycleOwner, { attachments -> updateAttachmentsUI(attachments) })
    }

    private fun onSendStarted(isStarted: Boolean) {
        if (isStarted) hideSoftKeyboard()
        enableControls(!isStarted)
        showProgress(isStarted)
    }

    private fun enableControls(isEnabled: Boolean) {
        back_button.isEnabled = isEnabled
        feedback_title_input.isEnabled = isEnabled
        feedback_content_input.isEnabled = isEnabled
        add_files_button.isEnabled = isEnabled
        feedback_send_button.isEnabled = isEnabled
    }

    private fun showProgress(isVisible: Boolean) {
        feedback_send_progress.setVisible(isVisible)
    }

    private fun onSendSuccess(isSuccess: Boolean) {
        if (isSuccess) {
            FeedbackSendSuccessDialogFragment.show(
                parentFragmentManager,
                FeedbackSendSuccessDialogType.FEEDBACK
            )
            navigateUp()
        }
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }

    private fun updateAttachmentsUI(attachments: List<Attachment>) =
        attachmentsAdapter.submitList(attachments)

    private fun onAddFilesButtonClick() {
        if (viewModel.isAddAttachmentsAllowed()) {
            startPicker(RC_PICKER)
        } else {
            toast(R.string.max_files_error)
        }
    }

    private fun onSendButtonClick() {
        val feedback = Feedback().apply {
            title = feedback_title_input.text.toString()
            content = feedback_content_input.text.toString()
        }

        viewModel.send(feedback)
    }

    private fun initAttachmentList() {
        files_list.layoutManager = LinearLayoutManager(context, RecyclerView.VERTICAL, false)
        files_list.isNestedScrollingEnabled = false
        attachmentsAdapter = AttachmentsAdapter()
        files_list.adapter = attachmentsAdapter
    }

    private fun initBackPressedCallback() {
        val onBackPressedCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                if (viewModel.isBackPressedAllowed) navigateUp()
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, onBackPressedCallback)
    }
}