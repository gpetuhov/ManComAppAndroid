package ru.mancomapp.domain.models.pass

import androidx.annotation.StringRes
import ru.mancomapp.R

enum class CarPassAccessType(val id: Int, @StringRes val nameId: Int) {
    ONE_TIME(1, R.string.pass_type_one_time),
    DAY(2, R.string.pass_type_day),
    OTHER(3, R.string.other),
    NOT_SELECTED(0, R.string.pass_type);

    companion object {
        fun getById(id: Int): CarPassAccessType {
            return values().firstOrNull { it.id == id } ?: NOT_SELECTED
        }
    }
}