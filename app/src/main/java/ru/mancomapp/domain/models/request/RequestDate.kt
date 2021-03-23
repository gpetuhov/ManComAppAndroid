package ru.mancomapp.domain.models.request

import java.io.Serializable

class RequestDate : Serializable {
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