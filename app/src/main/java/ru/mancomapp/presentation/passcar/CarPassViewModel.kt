package ru.mancomapp.presentation.passcar

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import ru.mancomapp.App
import ru.mancomapp.R
import ru.mancomapp.domain.models.pass.CarPass
import ru.mancomapp.domain.models.pass.CarPassAccessType
import ru.mancomapp.domain.usecase.pass.*
import ru.mancomapp.presentation.global.PassBaseViewModel
import javax.inject.Inject

class CarPassViewModel : PassBaseViewModel() {

    @Inject lateinit var carPassUseCase: CarPassUseCase

    var accessType: LiveData<CarPassAccessType>

    private val accessTypeLiveDataMutable = MutableLiveData<CarPassAccessType>()

    private var selectedAccessType: CarPassAccessType = CarPassAccessType.NOT_SELECTED

    init {
        App.appComponent.inject(this)

        accessType = accessTypeLiveDataMutable
        accessTypeLiveDataMutable.postValue(CarPassAccessType.NOT_SELECTED)
    }

    fun saveSelectedAccessType(accessType: CarPassAccessType) {
        selectedAccessType = accessType
        accessTypeLiveDataMutable.postValue(accessType)
    }

    fun sendRequest(carModel: String, carNumber: String) {
        val carPass = CarPass().apply {
            this.carModel = carModel
            this.carNumber = carNumber
            this.requestDate = selectedRequestDate
            this.accessType = selectedAccessType
        }

        sendJob = viewModelScope.launch(Dispatchers.IO) {
            try {
                carPassUseCase.sendRequest(carPass) { postSendStarted() }
                postSendSuccess()
            } catch (e: CarModelEmptyException) {
                postSendError(R.string.car_model_empty_error)
            } catch (e: CarNumberEmptyException) {
                postSendError(R.string.car_number_empty_error)
            } catch (e: PassDateEmptyException) {
                postSendError(R.string.pass_date_empty_error)
            } catch (e: AccessTypeNotSelectedException) {
                postSendError(R.string.access_type_empty_error)
            } catch (e: Exception) {
                // TODO: handle send error and no network (server unavailable)
            }
        }
    }
}