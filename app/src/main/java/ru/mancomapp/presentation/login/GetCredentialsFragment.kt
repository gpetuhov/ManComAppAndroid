package ru.mancomapp.presentation.login

import android.os.Bundle
import android.text.Html
import android.text.method.LinkMovementMethod
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_get_credentials.*
import ru.mancomapp.R

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

        val getCredentialsLink = "<a href=\"${getString(R.string.get_credentials_schedule_url)}\">${getString(R.string.here)}</a>"
        val html = getString(R.string.get_credentials_info, getCredentialsLink)

        val result = if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.N) {
            Html.fromHtml(html, Html.FROM_HTML_MODE_LEGACY)
        } else {
            Html.fromHtml(html)
        }

        get_credentials_info.text = result
        get_credentials_info.movementMethod = LinkMovementMethod.getInstance()
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }
}