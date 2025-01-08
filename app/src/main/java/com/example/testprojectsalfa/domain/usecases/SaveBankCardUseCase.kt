package com.example.testprojectsalfa.domain.usecases

import com.example.testprojectsalfa.domain.BankCard
import com.example.testprojectsalfa.domain.BinRepository
import javax.inject.Inject

class SaveBankCardUseCase @Inject constructor (private val binRepository: BinRepository) {
    suspend operator fun invoke(bankCard: BankCard){
        binRepository.saveBankCard(bankCard)

    }
}