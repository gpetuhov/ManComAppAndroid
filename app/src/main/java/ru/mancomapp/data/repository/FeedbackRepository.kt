package ru.mancomapp.data.repository

import kotlinx.coroutines.delay
import ru.mancomapp.domain.models.Feedback

class FeedbackRepository {

    @Throws(Exception::class)
    suspend fun sendFeedback(feedback: Feedback) {
        // TODO
        delay(5000)

        // TODO: handle send error and no network (server unavailable)
    }
}