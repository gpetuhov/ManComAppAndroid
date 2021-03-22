package ru.mancomapp.domain.models.bill

import androidx.annotation.StringRes
import ru.mancomapp.R

enum class BillStatus(@StringRes val nameId: Int) {
    PAID(R.string.bill_status_paid),
    NOT_PAID(R.string.bill_status_not_paid),
    IN_PROGRESS(R.string.bill_status_in_progress)
}