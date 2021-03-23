package ru.mancomapp.presentation.login

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.NavDirections
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_welcome.*
import ru.mancomapp.BuildConfig
import ru.mancomapp.R
import ru.mancomapp.presentation.MainActivity

class WelcomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        welcome_text.text = getString(R.string.welcome_message, getString(R.string.app_name))
        app_version.text = getString(R.string.app_version, BuildConfig.VERSION_NAME)

        enter_button.setOnClickListener { navigateToLoginFragment() }
        get_credentials_button.setOnClickListener { navigateToGetCredentialsFragment() }
        demo_version_button.setOnClickListener { onDemoVersionButtonClick() }
    }

    private fun navigateToLoginFragment() {
        val action = WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment()
        navigate(action)
    }

    private fun navigateToGetCredentialsFragment() {
        val action = WelcomeFragmentDirections.actionWelcomeFragmentToGetCredentialsFragment()
        navigate(action)
    }

    private fun onDemoVersionButtonClick() {
        val intent = Intent(requireContext(), MainActivity::class.java)
        startActivity(intent)
        activity?.finish()
    }

    private fun navigate(action: NavDirections) {
        findNavController().navigate(action)
    }
}