package ru.mancomapp.domain.models.request

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
class RequestDate : Parcelable {
    var year: Int = 0
    var month: Int = -1
    var day: Int = 0
    var timeInMillis: Long = 0

    fun isEmpty() = year == 0 || month == -1 || day == 0 || timeInMillis == 0L

    fun equals(other: RequestDate): Boolean {
        return year == other.year
                && month == other.month
                && day == other.day
                && timeInMillis == other.timeInMillis
    }
}