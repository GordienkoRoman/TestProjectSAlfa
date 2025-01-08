package com.example.testprojectsalfa.domain

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class BankCard(
    val bin: String,
    val country: String="",
    val coordinates: Coordinates=Coordinates(),
    val cardType: String = "",
    val bankInfo:Bank = Bank()
) : Parcelable {

}

@Parcelize
data class Coordinates(
    val latitude: String="",
    val longitude: String="",
) : Parcelable

@Parcelize
data class Bank(
    val name: String="",
    val url: String="",
    val phone: String="",
    val city: String="",
) : Parcelable
