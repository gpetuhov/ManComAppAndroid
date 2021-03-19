package ru.mancomapp.data.repository

import kotlinx.coroutines.delay
import ru.mancomapp.domain.models.request.Request
import ru.mancomapp.domain.models.request.RequestDate
import ru.mancomapp.domain.models.request.RequestStatus
import ru.mancomapp.domain.models.service.ServiceType

class RequestRepository {

    // TODO: remove this
    companion object {
        const val YEAR = 2021
        const val MONTH = 3
        const val DAY = 18
        const val TIME_IN_MILLIS = 1616056739495
    }

    @Throws(Exception::class)
    suspend fun getRequests(): List<Request> {
        // TODO: implement
        // TODO: handle errors
        delay(5000)
        return getDummyRequests()
    }

    // TODO: remove this
    private fun getDummyRequests(): List<Request> {
        val requests = mutableListOf<Request>()

        (1..100).forEach { index ->
            val request: Request

            val requestDate = RequestDate().apply {
                year = YEAR
                month = MONTH
                day = DAY
                timeInMillis = TIME_IN_MILLIS
            }

            if (index % 2 == 0) {
                request = Request.Management().apply {
                    id = index
                    date = requestDate
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
            } else {
                request = Request.Service().apply {
                    id = index
                    date = requestDate
                    type = ServiceType.PLUMBER
                    comment = "Dfjo34 34jt kjq oigfjraofiud foiuf8934fjklj42 tkt4j4oljt 34otj 43oi2tj4t3j o4ijto34jt " +
                            "kltj 4tk2 tkl 34 tj34tj tk34" +
                            "34kj klj 2tt34tj 43l tj4 tj34jt34jtkl 34jtkl34jt."
                    status = RequestStatus.ON_REVIEW
                }
            }

            requests.add(request)
        }

        return requests
    }
}