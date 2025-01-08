package com.example.testprojectsalfa.domain

import javax.inject.Inject

class GetRequestHistoryListUseCase @Inject constructor(private val binRepository: BinRepository) {
    suspend operator fun invoke(): List<BankCard> {
        return binRepository.getRequestHistoryList()
    }
}