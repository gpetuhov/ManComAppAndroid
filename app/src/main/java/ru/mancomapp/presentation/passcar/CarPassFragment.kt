package ru.mancomapp.presentation.passcar

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.alcon.fragment_car_pass.*
import kotlinx.android.synthetic.main.fragment_car_pass.back_button
import kotlinx.android.synthetic.main.fragment_car_pass.car_model_input
import kotlinx.android.synthetic.main.fragment_car_pass.car_number_input
import kotlinx.android.synthetic.main.fragment_car_pass.car_pass_date_input
import kotlinx.android.synthetic.main.fragment_car_pass.car_pass_send_button
import kotlinx.android.synthetic.main.fragment_car_pass.car_pass_send_progress
import ru.mancomapp.R
import ru.mancomapp.domain.models.pass.CarPassAccessType
import ru.mancomapp.domain.models.request.RequestDate
import ru.mancomapp.presentation.feedback.FeedbackSendSuccessDialogFragment
import ru.mancomapp.presentation.feedback.FeedbackSendSuccessDialogType
import ru.mancomapp.presentation.global.DatePickerDialogFragment
import ru.mancomapp.utils.extensions.hideSoftKeyboard
import ru.mancomapp.utils.extensions.setVisible
import ru.mancomapp.utils.extensions.toast
import ru.mancomapp.utils.getFormattedDate

class CarPassFragment : Fragment() {

    private lateinit var viewModel: CarPassViewModel

    private val passDateCallback = object : DatePickerDialogFragment.Callback {
        override fun onDateSelected(requestDate: RequestDate) = viewModel.saveSelectedDate(requestDate)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_car_pass, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeViewModel()

        back_button.setOnClickListener { navigateUp() }
        car_pass_date_input.setOnClickListener { onSelectDateClick() }
        car_pass_access_type_button?.setOnClickListener { onSelectAccessTypeClick() }
        car_pass_send_button.setOnClickListener { onSendButtonClick() }

        initBackPressedCallback()
    }

    private fun subscribeViewModel() {
        viewModel = ViewModelProvider(this).get(CarPassViewModel::class.java)
        viewModel.isSendStarted.observe(viewLifecycleOwner, { isStarted -> onSendStarted(isStarted) })
        viewModel.sendError.observe(viewLifecycleOwner, { errorMessage -> toast(errorMessage) })
        viewModel.isSendSuccess.observe(viewLifecycleOwner, { isSuccess -> onSendSuccess(isSuccess) })
        viewModel.requestDate.observe(viewLifecycleOwner, { passDate -> updatePassDateUI(passDate) })
        viewModel.accessType.observe(viewLifecycleOwner, { accessType -> updateAccessTypeUI(accessType) })
    }

    private fun onSendStarted(isStarted: Boolean) {
        if (isStarted) hideSoftKeyboard()
        enableControls(!isStarted)
        showProgress(isStarted)
    }

    private fun enableControls(isEnabled: Boolean) {
        back_button.isEnabled = isEnabled
        car_model_input.isEnabled = isEnabled
        car_number_input.isEnabled = isEnabled
        car_pass_date_input.isEnabled = isEnabled
        car_pass_access_type_button?.isEnabled = isEnabled
        car_pass_send_button.isEnabled = isEnabled
    }

    private fun showProgress(isVisible: Boolean) {
        car_pass_send_progress.setVisible(isVisible)
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

    private fun updatePassDateUI(requestDate: RequestDate) =
        car_pass_date_input.setText(getFormattedDate(requestDate))

    private fun updateAccessTypeUI(accessType: CarPassAccessType) {
        car_pass_access_type_button?.text = getString(accessType.nameId)
    }

    private fun onSelectDateClick() {
        DatePickerDialogFragment.show(
            parentFragmentManager,
            viewModel.selectedRequestDate,
            passDateCallback
        )
    }

    private fun onSelectAccessTypeClick() {
        // TODO: implement
        toast("Select access type")
    }

    private fun onSendButtonClick() {
        viewModel.sendRequest(
            carModel = car_model_input.text.toString(),
            carNumber = car_number_input.text.toString()
        )
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