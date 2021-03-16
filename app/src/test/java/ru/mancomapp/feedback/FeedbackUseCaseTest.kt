package ru.mancomapp.feedback

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import org.mockito.Mockito
import ru.mancomapp.data.repository.FeedbackRepository
import ru.mancomapp.domain.models.Feedback
import ru.mancomapp.domain.usecase.feedback.FeedbackEmptyException
import ru.mancomapp.domain.usecase.feedback.FeedbackUseCase

class FeedbackUseCaseTest {

    companion object {
        private const val TITLE = "Title"
        private const val CONTENT = "Content"
    }

    private val feedbackRepositoryMock = Mockito.mock(FeedbackRepository::class.java)
    private lateinit var feedbackUseCase: FeedbackUseCase
    private lateinit var feedback: Feedback

    @Before
    fun initFeedback() {
        feedback = Feedback()
        feedbackUseCase = FeedbackUseCase(feedbackRepositoryMock)
    }

    @Test(expected = FeedbackEmptyException::class)
    fun sendFeedback_emptyFeedback_throwsException() {
        runBlocking {
            feedbackUseCase.sendFeedback(feedback) { /* Do nothing */ }
        }
    }

    @Test
    fun sendFeedback_notEmptyFeedback_sendStarted() {
        runBlocking {
            feedback.title = TITLE
            feedback.content = CONTENT

            var isSendStarted = false
            feedbackUseCase.sendFeedback(feedback) { isSendStarted = true }
            assertTrue(isSendStarted)
        }
    }
}