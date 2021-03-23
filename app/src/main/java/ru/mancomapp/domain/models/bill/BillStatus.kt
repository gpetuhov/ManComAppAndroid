package ru.mancomapp.domain.models.bill

import androidx.annotation.StringRes
import ru.mancomapp.R

enum class BillStatus(
    @StringRes val nameId: Int,
    @StringRes val detailsId: Int
) {
    PAID(R.string.bill_status_paid, R.string.bill_status_paid_details),
    NOT_PAID(R.string.bill_status_not_paid, R.string.bill_status_not_paid_details),
    IN_PROGRESS(R.string.bill_status_in_progress, R.string.bill_status_in_progress_details)
}