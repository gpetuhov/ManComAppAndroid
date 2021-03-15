package ru.mancomapp.domain.models

import android.net.Uri

data class Attachment(
    val uri: Uri,
    val name: String
)