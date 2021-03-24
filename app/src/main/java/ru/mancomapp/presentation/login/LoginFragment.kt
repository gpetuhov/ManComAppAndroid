package ru.mancomapp.presentation.login

import android.content.Intent
import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.alcon.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_login.login_button
import kotlinx.android.synthetic.main.fragment_login.login_input
import kotlinx.android.synthetic.main.fragment_login.login_progress
import kotlinx.android.synthetic.main.fragment_login.password_input
import kotlinx.android.synthetic.main.fragment_login.privacy_policy_checkbox
import kotlinx.android.synthetic.main.fragment_login.welcome_text
import ru.mancomapp.R
import ru.mancomapp.domain.models.LoginCredentials
import ru.mancomapp.presentation.MainActivity
import ru.mancomapp.utils.extensions.hideSoftKeyboard
import ru.mancomapp.utils.extensions.setVisible
import ru.mancomapp.utils.extensions.toast

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeViewModel()

        welcome_text.text = getString(R.string.welcome_message, getString(R.string.app_name))
        login_button.setOnClickListener { onLoginButtonClick() }

        initConfirmPrivacyPolicy()
    }

    private fun onLoginButtonClick() {
        val credentials = LoginCredentials().apply {
            login = login_input.text.toString()
            password = password_input.text.toString()
            isPrivacyPolicyConfirmed = privacy_policy_checkbox.isChecked
        }

        viewModel.login(credentials)
    }

    private fun subscribeViewModel() {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)
        viewModel.isLoginStarted.observe(viewLifecycleOwner, { isStarted -> onLoginStarted(isStarted) })
        viewModel.loginError.observe(viewLifecycleOwner, { errorMessageId -> toast(errorMessageId) })
        viewModel.isLoginSuccess.observe(viewLifecycleOwner, { isSuccess -> onLoginSuccess(isSuccess) })
    }

    private fun onLoginStarted(isStarted: Boolean) {
        if (isStarted) hideSoftKeyboard()
        enableControls(!isStarted)
        showProgress(isStarted)
    }

    private fun onLoginSuccess(isSuccess: Boolean) {
        if (isSuccess) {
            val intent = Intent(requireContext(), MainActivity::class.java)
            startActivity(intent)
            activity?.finish()
        }
    }

    private fun enableControls(isEnabled: Boolean) {
        login_input.isEnabled = isEnabled
        password_input.isEnabled = isEnabled
        privacy_policy_checkbox.isEnabled = isEnabled
        privacy_policy_text.isEnabled = isEnabled
        login_button.isEnabled = isEnabled
    }

    private fun showProgress(isVisible: Boolean) {
        login_progress.setVisible(isVisible)
    }

    private fun initConfirmPrivacyPolicy() {
        val policyLink = "<b><a href=\"${getString(R.string.privacy_policy_url)}\">${getString(R.string.policy)}</a></b>"
        val conditionsLink = "<b><a href=\"${getString(R.string.confidentiality_url)}\">${getString(R.string.conditions)}</a></b>"

        val html = getString(R.string.privacy_policy_confirm_2, policyLink, conditionsLink)

        val result = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }

        privacy_policy_text.text = result
        privacy_policy_text.movementMethod = LinkMovementMethod.getInstance()
    }
}