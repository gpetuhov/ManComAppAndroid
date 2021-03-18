package ru.mancomapp.presentation.passperson

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.mancomapp.domain.models.pass.PassDate
import ru.mancomapp.domain.models.pass.PersonPassAccessType
import ru.mancomapp.presentation.global.SendRequestBaseViewModel

class PersonPassViewModel : SendRequestBaseViewModel() {

    var passDate: LiveData<PassDate>
    var accessType: LiveData<PersonPassAccessType>

    private val passDateLiveDataMutable = MutableLiveData<PassDate>()
    private val accessTypeLiveDataMutable = MutableLiveData<PersonPassAccessType>()

    private var selectedPassDate: PassDate = PassDate()
    private var selectedAccessType: PersonPassAccessType = PersonPassAccessType.NOT_SELECTED

    init {
        passDate = passDateLiveDataMutable
        accessType = accessTypeLiveDataMutable

        passDateLiveDataMutable.postValue(selectedPassDate)
        accessTypeLiveDataMutable.postValue(PersonPassAccessType.NOT_SELECTED)
    }

    fun saveSelectedDate(passDate: PassDate) {
        selectedPassDate = passDate
        passDateLiveDataMutable.postValue(passDate)
    }

    fun saveSelectedAccessType(accessType: PersonPassAccessType) {
        selectedAccessType = accessType
        accessTypeLiveDataMutable.postValue(accessType)
    }
}