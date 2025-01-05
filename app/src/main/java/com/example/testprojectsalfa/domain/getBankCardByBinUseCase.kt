package com.example.testprojectsalfa.domain

class getBankCardByBinUseCase(val binRepository: BinRepository) {
    fun getBankCardByBin(bin : Int):BankCard{
       return binRepository.getBankCardByBin(bin)
    }
}