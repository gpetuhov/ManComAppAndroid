package ru.mancomapp.domain.models

class LoginCredentials {
    var login: String = ""
    var password: String = ""
    var isPrivacyPolicyConfirmed: Boolean = false

    fun isEmpty() = login.isEmpty() || password.isEmpty()
}