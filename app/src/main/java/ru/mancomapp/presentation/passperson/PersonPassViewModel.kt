package ru.mancomapp.presentation.passperson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.mancomapp.domain.models.pass.PassDate
import ru.mancomapp.presentation.global.SendRequestBaseViewModel

class PersonPassViewModel : SendRequestBaseViewModel() {

    var passDate: LiveData<PassDate>

    private val passDateLiveDataMutable = MutableLiveData<PassDate>()

    private var selectedPassDate: PassDate = PassDate()

    init {
        passDate = passDateLiveDataMutable
    }

    fun saveSelectedDate(passDate: PassDate) {
        selectedPassDate = passDate
        passDateLiveDataMutable.postValue(passDate)
    }
}