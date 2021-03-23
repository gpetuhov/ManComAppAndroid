package ru.mancomapp.domain.usecase.bill

import ru.mancomapp.data.repository.BillRepository

class BillUseCase(private val billRepository: BillRepository) {

    // TODO: handle errors
    @Throws(Exception::class)
    suspend fun getBills() = billRepository.getBills()
}