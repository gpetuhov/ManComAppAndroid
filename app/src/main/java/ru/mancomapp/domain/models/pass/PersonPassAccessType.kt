package ru.mancomapp.domain.models.pass

import androidx.annotation.StringRes
import ru.mancomapp.R

enum class PersonPassAccessType(@StringRes val nameId: Int) {
    APARTMENT(R.string.access_type_apartment),
    LOBBY(R.string.access_type_lobby),
    OTHER(R.string.other),
    NOT_SELECTED(R.string.access_type)
}