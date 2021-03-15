package ru.mancomapp

import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.mancomapp.domain.models.LoginCredentials

class LoginCredentialsTest {

    companion object {
        private const val LOGIN = "login"
        private const val PASSWORD = "password"
    }

    private lateinit var loginCredentials: LoginCredentials

    @Before
    fun initLoginCredentials() {
        loginCredentials = LoginCredentials()
    }

    @Test
    fun isEmpty_emptyLoginEmptyPassword_true() {
        assertTrue(loginCredentials.isEmpty())
    }

    @Test
    fun isEmpty_emptyLogin_true() {
        loginCredentials.password = PASSWORD
        assertTrue(loginCredentials.isEmpty())
    }

    @Test
    fun isEmpty_emptyPassword_true() {
        loginCredentials.login = LOGIN
        assertTrue(loginCredentials.isEmpty())
    }

    @Test
    fun isEmpty_notEmptyLoginNotEmptyPassword_false() {
        loginCredentials.login = LOGIN
        loginCredentials.password = PASSWORD
        assertFalse(loginCredentials.isEmpty())
    }
}