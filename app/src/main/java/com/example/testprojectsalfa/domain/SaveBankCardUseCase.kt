package com.example.testprojectsalfa.domain

import javax.inject.Inject

class SaveBankCardUseCase (private val binRepository: BinRepository) {
    fun saveBankCard(){
        binRepository.saveBankCard()
    }
}