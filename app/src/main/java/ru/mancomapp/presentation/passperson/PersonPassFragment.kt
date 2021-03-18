package ru.mancomapp.presentation.passperson

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_person_pass.*
import ru.mancomapp.R
import ru.mancomapp.domain.models.pass.PassDate
import ru.mancomapp.presentation.feedback.FeedbackSendSuccessDialogFragment
import ru.mancomapp.presentation.feedback.FeedbackSendSuccessDialogType
import ru.mancomapp.utils.extensions.hideSoftKeyboard
import ru.mancomapp.utils.extensions.setVisible
import ru.mancomapp.utils.extensions.toast

class PersonPassFragment : Fragment() {

    private lateinit var viewModel: PersonPassViewModel

    private val passDateCallback = object : DatePickerDialogFragment.Callback {
        override fun onDateSelected(passDate: PassDate) = viewModel.saveSelectedDate(passDate)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_person_pass, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeViewModel()

        back_button.setOnClickListener { navigateUp() }
        person_pass_date_input.setOnClickListener { onSelectDateClick() }
        person_pass_access_type.setOnClickListener { onSelectAccessTypeClick() }
        person_pass_send_button.setOnClickListener { onSendButtonClick() }
    }

    private fun subscribeViewModel() {
        viewModel = ViewModelProvider(this).get(PersonPassViewModel::class.java)
        viewModel.isSendStarted.observe(viewLifecycleOwner, { isStarted -> onSendStarted(isStarted) })
        viewModel.sendError.observe(viewLifecycleOwner, { errorMessage -> toast(errorMessage) })
        viewModel.isSendSuccess.observe(viewLifecycleOwner, { isSuccess -> onSendSuccess(isSuccess) })
        viewModel.passDate.observe(viewLifecycleOwner, { passDate -> updatePassDateUI(passDate) })
    }

    private fun onSendStarted(isStarted: Boolean) {
        if (isStarted) hideSoftKeyboard()
        enableControls(!isStarted)
        showProgress(isStarted)
    }

    private fun enableControls(isEnabled: Boolean) {
        person_pass_name_input.isEnabled = isEnabled
        person_pass_date_input.isEnabled = isEnabled
        person_pass_access_type.isEnabled = isEnabled
        person_pass_send_button.isEnabled = isEnabled
    }

    private fun showProgress(isVisible: Boolean) {
        person_pass_send_progress.setVisible(isVisible)
    }

    private fun onSendSuccess(isSuccess: Boolean) {
        if (isSuccess) {
            FeedbackSendSuccessDialogFragment.show(
                parentFragmentManager,
                FeedbackSendSuccessDialogType.SERVICE_REQUEST
            )
            navigateUp()
        }
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }

    private fun updatePassDateUI(passDate: PassDate) {
        // TODO: implement
        val dateString = "${passDate.year}.${passDate.month}.${passDate.day}"
        person_pass_date_input.setText(dateString)
    }

    private fun onSelectDateClick() =
        DatePickerDialogFragment.show(parentFragmentManager, passDateCallback)

    private fun onSelectAccessTypeClick() {
        // TODO: implement
        toast("Select access type")
    }

    private fun onSendButtonClick() {
        // TODO: implement
        toast("Send")
    }
}