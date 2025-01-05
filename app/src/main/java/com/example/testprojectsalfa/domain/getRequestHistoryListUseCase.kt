package com.example.testprojectsalfa.domain

class getRequestHistoryListUseCase(val binRepository: BinRepository) {
    fun getRequestHistoryList(): List<BankCard> {
        return binRepository.getRequestHistoryList()
    }
}