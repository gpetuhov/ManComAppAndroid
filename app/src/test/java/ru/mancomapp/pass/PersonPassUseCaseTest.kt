package ru.mancomapp.pass

import kotlinx.coroutines.runBlocking
import org.junit.Assert.assertTrue
import org.junit.Before
import org.junit.Test
import ru.mancomapp.di.components.DaggerTestAppComponent
import ru.mancomapp.domain.models.pass.PassDate
import ru.mancomapp.domain.models.pass.PersonPass
import ru.mancomapp.domain.models.pass.PersonPassAccessType
import ru.mancomapp.domain.usecase.pass.AccessTypeNotSelectedException
import ru.mancomapp.domain.usecase.pass.PassDateEmptyException
import ru.mancomapp.domain.usecase.pass.PersonNameEmptyException
import ru.mancomapp.domain.usecase.pass.PersonPassUseCase
import javax.inject.Inject

class PersonPassUseCaseTest {

    companion object {
        private const val NAME = "Name"
        private const val YEAR = 2021
        private const val MONTH = 3
        private const val DAY = 18
        private const val TIME_IN_MILLIS = 1616056739495
    }

    @Inject lateinit var personUseCase: PersonPassUseCase

    private lateinit var personPass: PersonPass
    private lateinit var passDate: PassDate

    @Before
    fun init() {
        val appComponent = DaggerTestAppComponent.builder().build()
        appComponent.inject(this)

        personPass = PersonPass()
        initPassDate()
    }

    @Test(expected = PersonNameEmptyException::class)
    fun sendRequest_nameEmpty_throwsException() {
        runBlocking {
            personPass.passDate = passDate
            personPass.accessType = PersonPassAccessType.OTHER
            personUseCase.sendRequest(personPass) { /* Do nothing */ }
        }
    }

    @Test(expected = PassDateEmptyException::class)
    fun sendRequest_dateEmpty_throwsException() {
        runBlocking {
            personPass.personName = NAME
            personPass.accessType = PersonPassAccessType.OTHER
            personUseCase.sendRequest(personPass) { /* Do nothing */ }
        }
    }

    @Test(expected = AccessTypeNotSelectedException::class)
    fun sendRequest_accessTypeNotSelected_throwsException() {
        runBlocking {
            personPass.personName = NAME
            personPass.passDate = passDate
            personUseCase.sendRequest(personPass) { /* Do nothing */ }
        }
    }

    @Test
    fun sendRequest_validPersonPass_sendStarted() {
        runBlocking {
            personPass.personName = NAME
            personPass.passDate = passDate
            personPass.accessType = PersonPassAccessType.OTHER

            var isSendStarted = false
            personUseCase.sendRequest(personPass) { isSendStarted = true }
            assertTrue(isSendStarted)
        }
    }

    private fun initPassDate() {
        passDate = PassDate()
        passDate.year = YEAR
        passDate.month = MONTH
        passDate.day = DAY
        passDate.timeInMillis = TIME_IN_MILLIS
    }
}