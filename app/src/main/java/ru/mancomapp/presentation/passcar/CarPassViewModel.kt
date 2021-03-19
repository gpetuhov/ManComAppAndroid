package ru.mancomapp.presentation.passcar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mancomapp.App
import ru.mancomapp.R
import ru.mancomapp.domain.models.request.RequestDate
import ru.mancomapp.domain.models.pass.PersonPass
import ru.mancomapp.domain.models.pass.PersonPassAccessType
import ru.mancomapp.domain.usecase.pass.AccessTypeNotSelectedException
import ru.mancomapp.domain.usecase.pass.PassDateEmptyException
import ru.mancomapp.domain.usecase.pass.PersonNameEmptyException
import ru.mancomapp.domain.usecase.pass.PersonPassUseCase
import ru.mancomapp.presentation.global.SendRequestBaseViewModel
import javax.inject.Inject

class CarPassViewModel : SendRequestBaseViewModel() {

    // TODO: implement
//    @Inject lateinit var personPassUseCase: PersonPassUseCase
//
//    var requestDate: LiveData<RequestDate>
//    var accessType: LiveData<PersonPassAccessType>
//
//    private val requestDateLiveDataMutable = MutableLiveData<RequestDate>()
//    private val accessTypeLiveDataMutable = MutableLiveData<PersonPassAccessType>()
//
//    var selectedRequestDate: RequestDate = RequestDate()
//    private var selectedAccessType: PersonPassAccessType = PersonPassAccessType.NOT_SELECTED
//
//    init {
//        App.appComponent.inject(this)
//
//        requestDate = requestDateLiveDataMutable
//        accessType = accessTypeLiveDataMutable
//
//        requestDateLiveDataMutable.postValue(selectedRequestDate)
//        accessTypeLiveDataMutable.postValue(PersonPassAccessType.NOT_SELECTED)
//    }
//
//    fun saveSelectedDate(requestDate: RequestDate) {
//        selectedRequestDate = requestDate
//        requestDateLiveDataMutable.postValue(requestDate)
//    }
//
//    fun saveSelectedAccessType(accessType: PersonPassAccessType) {
//        selectedAccessType = accessType
//        accessTypeLiveDataMutable.postValue(accessType)
//    }
//
//    fun sendRequest(personName: String) {
//        val personPass = PersonPass().apply {
//            this.personName = personName
//            this.requestDate = selectedRequestDate
//
//            // TODO: restore this, when access type implemented
////            this.accessType = selectedAccessType
//            this.accessType = PersonPassAccessType.OTHER
//        }
//
//        sendJob = viewModelScope.launch(Dispatchers.IO) {
//            try {
//                personPassUseCase.sendRequest(personPass) { postSendStarted() }
//                postSendSuccess()
//            } catch (e: PersonNameEmptyException) {
//                postSendError(R.string.guest_name_empty_error)
//            } catch (e: PassDateEmptyException) {
//                postSendError(R.string.pass_date_empty_error)
//            } catch (e: AccessTypeNotSelectedException) {
//                postSendError(R.string.access_type_empty_error)
//            } catch (e: Exception) {
//                // TODO: handle send error and no network (server unavailable)
//            }
//        }
//    }
}