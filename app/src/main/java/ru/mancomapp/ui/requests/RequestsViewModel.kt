package ru.mancomapp.ui.requests

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.*
import ru.mancomapp.models.request.Request
import ru.mancomapp.models.request.RequestStatus

class RequestsViewModel : ViewModel() {

    var isRequestHistoryLoading: LiveData<Boolean>
    var isRequestHistoryError: LiveData<String>
    var requestHistory: LiveData<List<Request>>

    private var isRequestHistoryLoadingMutable = MutableLiveData<Boolean>()
    private var isRequestHistoryErrorMutable = MutableLiveData<String>()
    private var requestHistoryMutable = MutableLiveData<List<Request>>()

    private var isRequestHistoryLoaded = false
    private var isRequestHistoryLoadingStarted = false

    private var loadRequestHistoryJob: Job? = null

    init {
        isRequestHistoryLoading = isRequestHistoryLoadingMutable
        isRequestHistoryError = isRequestHistoryErrorMutable
        requestHistory = requestHistoryMutable
    }

    override fun onCleared() {
        super.onCleared()
        loadRequestHistoryJob?.cancel()
    }

    fun loadRequestHistory() {
        if (!isRequestHistoryLoaded && !isRequestHistoryLoadingStarted) {
            isRequestHistoryLoadingStarted = true
            isRequestHistoryLoadingMutable.postValue(true)

            loadRequestHistoryJob = viewModelScope.launch(Dispatchers.IO) {
                // TODO: implement
                delay(5000)
                val requests = getDummyRequests()

                // TODO: handle load error and no network (server unavailable)

                isRequestHistoryLoaded = true
                isRequestHistoryLoadingStarted = false

                withContext(Dispatchers.Main) {
                    isRequestHistoryLoadingMutable.postValue(false)
                    requestHistoryMutable.postValue(requests)
                }
            }
        }
    }

    // TODO: remove this
    private fun getDummyRequests(): List<Request> {
        val requests = mutableListOf<Request>()

        (1..100).forEach {
            val request = Request().apply {
                id = it
                title = "Тема обращения ывдоад ываоы вадл выалв ыалвы оалдвыо адывл оадлыв оавы оа ывдла овы"
                content = "Lsjdkf lfkjs fljsf lsdkjf adslkfj lksdjfj dfklsdjfl " +
                        "ksdfj l4jfl43j fl4k3qj f43 jqfkl4jgflk43jglfk jerlgkjl4k " +
                        "2jgl2k45 jglk43j5 glk34j glk4j 3g42lk gjklgj fgkfjdlgjfd g" +
                        "klfjdlskfj dslkfj asdlkfjsldak fjdslkj fldskf jl dsjflkdas fj" +
                        "lsdfk dsl;fkdl;skf ;adsk f;lsdak fdsjkfh q34f hqkjewhfkejwhf" +
                        "dlkgjds flkjsd fkljdsflkjsdflkj dsflk jdsfjasdlkfjadslkjfdlksfj" +
                        "sdflkja dsfljdsflkj sdflkjdsf."
                status = RequestStatus.NEW
            }

            requests.add(request)
        }

        return requests
    }
}