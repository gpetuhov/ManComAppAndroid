package ru.mancomapp.pass

import ru.mancomapp.domain.models.pass.PassDate

class PersonPassTestData {
    companion object {
        const val YEAR = 2021
        const val MONTH = 3
        const val DAY = 18
        const val TIME_IN_MILLIS = 1616056739495

        fun getPassDate(): PassDate {
            return PassDate().apply {
                year = YEAR
                month = MONTH
                day = DAY
                timeInMillis = TIME_IN_MILLIS
            }
        }
    }
}