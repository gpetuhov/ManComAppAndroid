package ru.mancomapp.data.repository

import kotlinx.coroutines.delay
import ru.mancomapp.domain.models.request.Request
import ru.mancomapp.domain.models.request.RequestStatus

class RequestRepository {

    @Throws(Exception::class)
    suspend fun getRequests(): List<Request> {
        // TODO: implement
        // TODO: handle errors

        delay(5000)

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