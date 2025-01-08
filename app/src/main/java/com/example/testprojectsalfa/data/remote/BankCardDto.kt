package com.example.testprojectsalfa.data.remote

import com.example.testprojectsalfa.domain.Bank

data class BankCardDto(
    val scheme: String,
    val country: Country,
    val bank: Bank,
)

data class Country(
    val name: String,
    val latitude: String,
    val longitude: String,
)
