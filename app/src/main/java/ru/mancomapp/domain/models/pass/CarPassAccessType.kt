package ru.mancomapp.domain.models.pass

import androidx.annotation.StringRes
import ru.mancomapp.R

enum class CarPassAccessType(@StringRes val nameId: Int) {
    TERRITORY(R.string.access_type_territory),
    GUEST_PARKING(R.string.access_type_guest_parking),
    OTHER(R.string.other),
    NOT_SELECTED(R.string.access_type)
}