package com.example.testprojectsalfa.presentation.mainScreen

import com.example.testprojectsalfa.data.remote.BankCardDto
import com.example.testprojectsalfa.domain.BankCard

sealed class ScreenState {

    object Initial : ScreenState()

    object Loading : ScreenState()

    data class Error(val e: String) : ScreenState()

    data class Loaded(
        val bankCard: BankCard
    ) : ScreenState()
}