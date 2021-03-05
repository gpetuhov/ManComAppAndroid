package ru.mancomapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_login.*
import ru.mancomapp.R
import ru.mancomapp.util.extensions.hideSoftKeyboard
import ru.mancomapp.util.extensions.setVisible
import ru.mancomapp.util.extensions.toast

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
    }

    private fun onLoginButtonClick() {
        val credentials = LoginViewModel.LoginCredentials().apply {
            login = login_input.text.toString()
            password = password_input.text.toString()
            isPrivacyPolicyConfirmed = privacy_policy_checkbox.isChecked
        }

        viewModel.login(credentials)
    }

    private fun subscribeViewModel() {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.isLoginStarted.observe(viewLifecycleOwner, { isStarted ->
            if (isStarted) hideSoftKeyboard()
            enableLoginButton(!isStarted)
            showProgress(isStarted)
        })

        viewModel.isLoginError.observe(viewLifecycleOwner, { errorMessage ->
            toast(errorMessage)
        })
    }

    private fun enableLoginButton(isEnabled: Boolean) {
        login_button.isEnabled = isEnabled
    }

    private fun showProgress(isVisible: Boolean) {
        login_progress.setVisible(isVisible)
    }
}