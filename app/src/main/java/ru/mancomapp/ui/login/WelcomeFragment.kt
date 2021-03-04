package ru.mancomapp.ui.login

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
import ru.mancomapp.util.extensions.toast

class WelcomeFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_welcome, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        welcome_text.text = getString(R.string.welcome_message, getString(R.string.visible_app_name))
        app_version.text = getString(R.string.app_version, BuildConfig.VERSION_NAME)

        enter_button.setOnClickListener { navigateToLoginFragment() }

        get_credentials_button.setOnClickListener {
            // TODO: implement
            toast("Get credentials")
        }

        demo_version_button.setOnClickListener {
            // TODO: implement
            toast("Demo version")
        }
    }

    private fun navigateToLoginFragment() {
        val action = WelcomeFragmentDirections.actionWelcomeFragmentToLoginFragment()
        navigate(action)
    }

    private fun navigate(action: NavDirections) {
        findNavController().navigate(action)
    }
}