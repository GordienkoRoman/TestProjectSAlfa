package com.example.testprojectsalfa.domain

import javax.inject.Inject

class SaveBankCardUseCase @Inject constructor (private val binRepository: BinRepository) {
    suspend operator fun invoke(bankCard: BankCard){
        binRepository.saveBankCard(bankCard)

    }
}