package com.example.testprojectsalfa.domain

class saveBankCardUseCase(val binRepository: BinRepository) {
    fun saveBankCard(){
        binRepository.saveBankCard()
    }
}