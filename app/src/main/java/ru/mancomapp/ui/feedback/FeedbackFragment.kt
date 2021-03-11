package ru.mancomapp.ui.feedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_feedback.*
import kotlinx.android.synthetic.main.fragment_login.*
import ru.mancomapp.R
import ru.mancomapp.util.extensions.hideSoftKeyboard
import ru.mancomapp.util.extensions.setVisible
import ru.mancomapp.util.extensions.toast

class FeedbackFragment : Fragment() {

    private lateinit var viewModel: FeedbackViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feedback, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeViewModel()

        back_button.setOnClickListener { navigateUp() }

        add_files_button.setOnClickListener {
            // TODO
            toast("Add files")
        }

        feedback_send_button.setOnClickListener { onSendButtonClick() }
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }

    private fun subscribeViewModel() {
        viewModel = ViewModelProvider(this).get(FeedbackViewModel::class.java)
        viewModel.isSendStarted.observe(viewLifecycleOwner, { isStarted -> onSendStarted(isStarted) })
        viewModel.isSendError.observe(viewLifecycleOwner, { errorMessage -> toast(errorMessage) })
        viewModel.isSendSuccess.observe(viewLifecycleOwner, { isSuccess -> onSendSuccess(isSuccess) })
    }

    private fun onSendStarted(isStarted: Boolean) {
        if (isStarted) hideSoftKeyboard()

        // TODO
//        enableControls(!isStarted)

        showProgress(isStarted)
    }

    private fun onSendSuccess(isStarted: Boolean) {
        // TODO
        toast("Send success")
    }

    private fun onSendButtonClick() {
        val feedback = FeedbackViewModel.Feedback().apply {
            title = feedback_title_input.text.toString()
            content = feedback_content_input.text.toString()

            // TODO: add files list
        }

        viewModel.send(feedback)
    }

    private fun showProgress(isVisible: Boolean) {
        feedback_send_progress.setVisible(isVisible)
    }
}