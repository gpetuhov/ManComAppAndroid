package ru.mancomapp.presentation.global

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import ru.mancomapp.domain.models.request.RequestDate

open class PassBaseViewModel : SendRequestBaseViewModel() {

    var requestDate: LiveData<RequestDate>

    private val requestDateLiveDataMutable = MutableLiveData<RequestDate>()

    var selectedRequestDate: RequestDate = RequestDate()

    init {
        requestDate = requestDateLiveDataMutable
        requestDateLiveDataMutable.postValue(selectedRequestDate)
    }

    fun saveSelectedDate(requestDate: RequestDate) {
        selectedRequestDate = requestDate
        requestDateLiveDataMutable.postValue(requestDate)
    }
}