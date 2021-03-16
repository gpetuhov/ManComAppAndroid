package ru.mancomapp.feedback

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.newSingleThreadContext
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.*
import org.junit.Assert.*
import ru.mancomapp.App
import ru.mancomapp.R
import ru.mancomapp.di.components.DaggerTestAppComponent
import ru.mancomapp.domain.models.Feedback
import ru.mancomapp.presentation.feedback.FeedbackViewModel

class FeedbackViewModelTest {

    companion object {
        private const val TITLE = "Title"
        private const val CONTENT = "Content"
    }

    private lateinit var viewModel: FeedbackViewModel
    private lateinit var feedback: Feedback

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    @get:Rule
    val rule = InstantTaskExecutorRule()

    @ExperimentalCoroutinesApi
    @Before
    fun initViewModel() {
        App.appComponent = DaggerTestAppComponent.builder().build()
        Dispatchers.setMain(mainThreadSurrogate)

        viewModel = FeedbackViewModel()
        feedback = Feedback()
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDown() {
        Dispatchers.resetMain()
        mainThreadSurrogate.close()
    }

    @Test
    fun sendFeedback_emptyFeedback_errorFeedbackEmpty() {
        viewModel.send(feedback)
        delay()
        assertFalse(viewModel.isSendStarted.value ?: false)
        assertFalse(viewModel.isSendSuccess.value ?: false)
        assertEquals(R.string.feedback_empty_error, viewModel.isSendError.value)
    }

    @Test
    fun sendFeedback_emptyTitle_errorFeedbackEmpty() {
        feedback.content = CONTENT
        viewModel.send(feedback)
        delay()
        assertFalse(viewModel.isSendStarted.value ?: false)
        assertFalse(viewModel.isSendSuccess.value ?: false)
        assertEquals(R.string.feedback_empty_error, viewModel.isSendError.value)
    }

    @Test
    fun sendFeedback_emptyContent_errorFeedbackEmpty() {
        feedback.title = TITLE
        viewModel.send(feedback)
        delay()
        assertFalse(viewModel.isSendStarted.value ?: false)
        assertFalse(viewModel.isSendSuccess.value ?: false)
        assertEquals(R.string.feedback_empty_error, viewModel.isSendError.value)
    }

    @Test
    fun sendFeedback_notEmptyFeedback_sendSuccess() {
        feedback.title = TITLE
        feedback.content = CONTENT
        viewModel.send(feedback)
        delay()
        assertTrue(viewModel.isSendSuccess.value ?: false)
    }

    private fun delay() = Thread.sleep(300)
}