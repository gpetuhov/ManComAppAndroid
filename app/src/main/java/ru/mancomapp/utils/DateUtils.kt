package ru.mancomapp.utils

import ru.mancomapp.domain.models.request.RequestDate
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun getFormattedDate(requestDate: RequestDate): String {
    if (requestDate.isEmpty()) return ""

    val calendar = Calendar.getInstance()
    calendar.set(requestDate.year, requestDate.month, requestDate.day)
    val format = SimpleDateFormat.getDateInstance(DateFormat.SHORT)
    return format.format(calendar.time)
}
