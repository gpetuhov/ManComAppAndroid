package ru.mancomapp.domain.models.request

import androidx.annotation.StringRes
import ru.mancomapp.R

enum class RequestStatus(@StringRes val nameId: Int) {
    NEW(R.string.request_status_new),
    ON_REVIEW(R.string.request_status_on_review),
    COMPLETE(R.string.request_status_complete)
}