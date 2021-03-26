package ru.mancomapp.presentation.login

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_get_credentials.*
import ru.mancomapp.R
import ru.mancomapp.utils.extensions.initHtml

class GetCredentialsFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_get_credentials, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initText()
        close_button.setOnClickListener { navigateUp() }
    }

    private fun initText() {
        welcome_text.text = getString(R.string.welcome_message, getString(R.string.app_name))

        val getCredentialsLink = "<b><a href=\"${getString(R.string.get_credentials_schedule_url)}\">${getString(R.string.here)}</a></b>"
        val html = getString(R.string.get_credentials_info, getCredentialsLink)

        initHtml(get_credentials_info, html)
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }
}