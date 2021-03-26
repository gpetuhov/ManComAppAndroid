package ru.mancomapp.domain.models.service

import androidx.annotation.StringRes
import ru.mancomapp.R

enum class ServiceType(@StringRes val nameId: Int) {
    PLUMBER(R.string.plumber),
    ELECTRICIAN(R.string.electrician),
    CARPENTER(R.string.carpenter),
    OTHER(R.string.other),
    NOT_SELECTED(R.string.choose)
}