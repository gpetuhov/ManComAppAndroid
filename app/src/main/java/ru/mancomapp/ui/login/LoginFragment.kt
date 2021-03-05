package ru.mancomapp.ui.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.widget.doOnTextChanged
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_login.*
import ru.mancomapp.R
import ru.mancomapp.util.extensions.toast

class LoginFragment : Fragment() {

    private lateinit var viewModel: LoginViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_login, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        subscribeViewModel()
        initInputs()

        welcome_text.text = getString(R.string.welcome_message, getString(R.string.app_name))
        login_button.setOnClickListener { onLoginButtonClick() }
    }

    private fun initInputs() {
        login_input.doOnTextChanged { text, start, before, count ->
            viewModel.onLoginChanged(text.toString())
        }

        password_input.doOnTextChanged { text, start, before, count ->
            viewModel.onPasswordChanged(text.toString())
        }

        privacy_policy_checkbox.setOnCheckedChangeListener { buttonView, isChecked ->
            viewModel.onPrivacyPolicyConfirmChanged(isChecked)
        }
    }

    private fun onLoginButtonClick() {
        // TODO
        toast("Login")
    }

    private fun subscribeViewModel() {
        viewModel = ViewModelProvider(this).get(LoginViewModel::class.java)

        viewModel.isLoginEnabledLiveData.observe(viewLifecycleOwner, Observer { isEnabled ->
            login_button.isEnabled = isEnabled
        })
    }
}