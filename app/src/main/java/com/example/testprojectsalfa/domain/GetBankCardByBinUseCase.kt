package com.example.testprojectsalfa.domain

import javax.inject.Inject

class GetBankCardByBinUseCase @Inject constructor(private val binRepository: BinRepository) {

    suspend operator fun invoke(bin: String): BankCard{
        return binRepository.getBankCardByBin(bin)
    }
    suspend fun getBankCardByBin(bin: String): BankCard {
        return binRepository.getBankCardByBin(bin)
    }
}