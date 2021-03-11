package ru.mancomapp.ui.feedback

import android.app.Activity
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.provider.OpenableColumns
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_feedback.*
import ru.mancomapp.R
import ru.mancomapp.util.extensions.hideSoftKeyboard
import ru.mancomapp.util.extensions.setVisible
import ru.mancomapp.util.extensions.startPicker
import ru.mancomapp.util.extensions.toast

class FeedbackFragment : Fragment() {

    companion object {
        private const val RC_PICKER = 1001
    }

    private lateinit var viewModel: FeedbackViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feedback, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeViewModel()

        back_button.setOnClickListener { navigateUp() }
        add_files_button.setOnClickListener { onAddFilesButtonClick() }
        feedback_send_button.setOnClickListener { onSendButtonClick() }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)

        if (requestCode == RC_PICKER && resultCode == Activity.RESULT_OK) {
            val fileUri  = data?.data

            // TODO
            fileUri?.let { toast(getFileName(fileUri) ?: "") }
        }
    }

    private fun subscribeViewModel() {
        viewModel = ViewModelProvider(this).get(FeedbackViewModel::class.java)
        viewModel.isSendStarted.observe(viewLifecycleOwner, { isStarted -> onSendStarted(isStarted) })
        viewModel.isSendError.observe(viewLifecycleOwner, { errorMessage -> toast(errorMessage) })
        viewModel.isSendSuccess.observe(viewLifecycleOwner, { isSuccess -> onSendSuccess(isSuccess) })
    }

    private fun onSendStarted(isStarted: Boolean) {
        if (isStarted) hideSoftKeyboard()
        enableControls(!isStarted)
        showProgress(isStarted)
    }

    private fun enableControls(isEnabled: Boolean) {
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
            toast(R.string.feedback_send_success)
            navigateUp()
        }
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }

    private fun onAddFilesButtonClick() = startPicker(RC_PICKER)

    private fun onSendButtonClick() {
        val feedback = FeedbackViewModel.Feedback().apply {
            title = feedback_title_input.text.toString()
            content = feedback_content_input.text.toString()

            // TODO: add files list
        }

        viewModel.send(feedback)
    }

    // TODO: refactor this into separate class
    private fun getFileName(uri: Uri): String? {
        var result: String? = null
        if (uri.scheme == "content") {
            val cursor = context?.contentResolver?.query(uri, null, null, null, null)
            cursor.use { cursor1 ->
                if (cursor1 != null && cursor1.moveToFirst()) {
                    result = cursor1.getString(cursor1.getColumnIndex(OpenableColumns.DISPLAY_NAME))
                }
            }
        }
        if (result == null) {
            result = uri.path
            val cut = result?.lastIndexOf('/') ?: -1
            if (cut != -1) {
                result = result?.substring(cut + 1)
            }
        }
        return result
    }
}