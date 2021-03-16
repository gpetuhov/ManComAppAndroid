package ru.mancomapp.domain.models

class Feedback {
    var title: String = ""
    var content: String = ""

    fun isEmpty() = title.isEmpty() || content.isEmpty()
}