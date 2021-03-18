package ru.mancomapp.presentation.global

import android.net.Uri
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.mancomapp.App
import ru.mancomapp.domain.models.Attachment
import ru.mancomapp.data.source.local.AppConstants
import ru.mancomapp.utils.getFileName

open class FeedbackBaseViewModel : SendRequestBaseViewModel() {

    var attachments: LiveData<List<Attachment>>

    private val attachmentsLiveDataMutable = MutableLiveData<List<Attachment>>()

    protected val attachmentsList = mutableListOf<Attachment>()

    init {
        attachments = attachmentsLiveDataMutable
    }

    fun isAddAttachmentsAllowed() = attachmentsList.size < AppConstants.Files.FEEDBACK_MAX_FILES

    fun addAttachment(uri: Uri?) {
        uri?.let {
            val fileName = getFileName(App.application.applicationContext, uri) ?: ""
            val attachment = Attachment(uri, fileName)
            attachmentsList.add(attachment)

            val newAttachments = mutableListOf<Attachment>()
            newAttachments.addAll(attachmentsList)
            attachmentsLiveDataMutable.postValue(newAttachments)
        }
    }
}