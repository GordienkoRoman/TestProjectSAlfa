package com.example.testprojectsalfa.domain

import javax.inject.Inject

class GetRequestHistoryListUseCase(private val binRepository: BinRepository) {
    fun getRequestHistoryList(): List<BankCard> {
        return binRepository.getRequestHistoryList()
    }
}