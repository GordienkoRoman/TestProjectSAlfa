package com.example.testprojectsalfa.domain.usecases

import com.example.testprojectsalfa.domain.BankCard
import com.example.testprojectsalfa.domain.BinRepository
import javax.inject.Inject

class GetRequestHistoryListUseCase @Inject constructor(private val binRepository: BinRepository) {
    suspend operator fun invoke(): List<BankCard> {
        return binRepository.getRequestHistoryList()
    }
}