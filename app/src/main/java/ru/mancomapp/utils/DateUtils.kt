package ru.mancomapp.utils

import ru.mancomapp.domain.models.request.RequestDate
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun getFormattedDate(requestDate: RequestDate) =
    getFormattedDate(requestDate, false)

fun getLongFormattedDate(requestDate: RequestDate) =
    getFormattedDate(requestDate, true)

private fun getFormattedDate(requestDate: RequestDate, isLongFormat: Boolean): String {
    if (requestDate.isEmpty()) return ""

    val calendar = Calendar.getInstance()
    calendar.set(requestDate.year, requestDate.month, requestDate.day)
    val format = SimpleDateFormat.getDateInstance(if (isLongFormat) DateFormat.LONG else DateFormat.SHORT)
    return format.format(calendar.time)
}
