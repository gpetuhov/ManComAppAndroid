package ru.mancomapp.presentation.feedback

import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.mancomapp.R
import ru.mancomapp.App
import ru.mancomapp.domain.models.Feedback
import ru.mancomapp.domain.usecase.feedback.FeedbackEmptyException
import ru.mancomapp.domain.usecase.feedback.FeedbackUseCase
import ru.mancomapp.presentation.global.FeedbackBaseViewModel
import javax.inject.Inject

class FeedbackViewModel : FeedbackBaseViewModel() {

    @Inject lateinit var feedbackUseCase: FeedbackUseCase

    init {
        App.appComponent.inject(this)
    }

    fun send(feedback: Feedback) {
        sendJob = viewModelScope.launch(Dispatchers.IO) {
            try {
                feedbackUseCase.sendFeedback(feedback) { postSendStarted() }
                postSendSuccess()
            } catch (e: FeedbackEmptyException) {
                postSendError(R.string.feedback_empty_error)
            } catch (e: Exception) {
                // TODO: handle send error and no network (server unavailable)
            }
        }
    }
}