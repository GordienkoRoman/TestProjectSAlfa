package com.example.testprojectsalfa.domain.usecases

import com.example.testprojectsalfa.domain.BankCard
import com.example.testprojectsalfa.domain.BinRepository
import javax.inject.Inject

class GetBankCardByBinUseCase @Inject constructor(private val binRepository: BinRepository) {

    suspend operator fun invoke(bin: String): BankCard? {
        return binRepository.getBankCardByBin(bin)
    }
}