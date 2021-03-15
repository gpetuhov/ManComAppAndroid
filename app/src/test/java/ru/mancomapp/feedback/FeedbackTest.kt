package ru.mancomapp.feedback

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.mancomapp.presentation.feedback.FeedbackViewModel

class FeedbackTest {

    companion object {
        private const val TITLE = "Title"
        private const val CONTENT = "Content"
    }

    private lateinit var feedback: FeedbackViewModel.Feedback

    @Before
    fun initFeedback() {
        feedback = FeedbackViewModel.Feedback()
    }

    @Test
    fun isEmpty_emptyTitleEmptyContent_true() {
        assertTrue(feedback.isEmpty())
    }

    @Test
    fun isEmpty_emptyTitle_true() {
        feedback.content = CONTENT
        assertTrue(feedback.isEmpty())
    }

    @Test
    fun isEmpty_EmptyContent_true() {
        feedback.title = TITLE
        assertTrue(feedback.isEmpty())
    }

    @Test
    fun isEmpty_notEmptyTitleNotEmptyContent_false() {
        feedback.title = TITLE
        feedback.content = CONTENT
        assertFalse(feedback.isEmpty())
    }
}