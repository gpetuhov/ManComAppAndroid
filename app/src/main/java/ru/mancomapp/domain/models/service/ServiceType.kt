package ru.mancomapp.domain.models.service

import androidx.annotation.StringRes
import ru.mancomapp.R

enum class ServiceType(val id: Int, @StringRes val nameId: Int) {
    PLUMBER(1, R.string.plumber),
    ELECTRICIAN(2, R.string.electrician),
    CARPENTER(3, R.string.carpenter),
    OTHER(4, R.string.other),
    NOT_SELECTED(0, R.string.choose);

    companion object {
        fun getById(id: Int): ServiceType {
            return values().firstOrNull { it.id == id } ?: NOT_SELECTED
        }
    }
}