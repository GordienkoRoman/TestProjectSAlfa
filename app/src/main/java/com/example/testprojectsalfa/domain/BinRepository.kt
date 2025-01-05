package com.example.testprojectsalfa.domain

interface BinRepository {
    fun getBankCardByBin(bin:Int):BankCard

    fun getRequestHistoryList(): List<BankCard>

    fun saveBankCard()
}