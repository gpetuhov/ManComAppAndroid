package ru.mancomapp.domain.models.pass

class PassDate {
    var year: Int = 0
    var month: Int = 0
    var day: Int = 0
    var timeInMillis: Long = 0

    fun isEmpty() = year == 0 || month == 0 || day == 0 || timeInMillis == 0L
}