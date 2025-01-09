package com.example.testprojectsalfa.domain

interface BinRepository {
    suspend fun getBankCardByBin(bin: String): BankCard

    suspend fun getRequestHistoryList(): List<BankCard>

    suspend fun saveBankCard(bankCard: BankCard)
}