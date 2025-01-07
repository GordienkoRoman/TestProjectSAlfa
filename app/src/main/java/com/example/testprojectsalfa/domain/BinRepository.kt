package com.example.testprojectsalfa.domain

interface BinRepository {
    suspend fun getBankCardByBin(bin:String):BankCard

    fun getRequestHistoryList(): List<BankCard>

    fun saveBankCard()
}