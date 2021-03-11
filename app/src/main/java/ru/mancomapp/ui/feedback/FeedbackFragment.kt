package ru.mancomapp.ui.feedback

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import kotlinx.android.synthetic.main.fragment_feedback.*
import ru.mancomapp.R
import ru.mancomapp.util.extensions.toast

class FeedbackFragment : Fragment() {

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        return inflater.inflate(R.layout.fragment_feedback, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        back_button.setOnClickListener { navigateUp() }

        add_files_button.setOnClickListener {
            // TODO
            toast("Add files")
        }

        feedback_send_button.setOnClickListener {
            // TODO
            toast("Send feedback")
        }
    }

    private fun navigateUp() {
        findNavController().navigateUp()
    }
}