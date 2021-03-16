package ru.mancomapp.domain.usecase.feedback

import ru.mancomapp.data.repository.FeedbackRepository
import ru.mancomapp.domain.models.Feedback

class FeedbackUseCase(private val feedbackRepository: FeedbackRepository) {

    @Throws(
        FeedbackEmptyException::class,
        Exception::class
    )
    suspend fun sendFeedback(
        feedback: Feedback,
        onSendRequestStarted: suspend () -> Unit
    ) {
        if (feedback.isEmpty()) throw FeedbackEmptyException()

        onSendRequestStarted()
        feedbackRepository.sendFeedback(feedback)

        // TODO: handle login error and no network (server unavailable)
    }
}