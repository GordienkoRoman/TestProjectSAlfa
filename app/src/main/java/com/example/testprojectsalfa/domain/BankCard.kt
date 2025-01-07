package com.example.testprojectsalfa.domain

import android.os.Parcelable
import com.example.testprojectsalfa.data.remote.BankCardDto
import kotlinx.parcelize.Parcelize

@Parcelize
data class BankCard(
    val id: Int=0,
    val country: String="",
    val coordinates: Coordinates=Coordinates(),
    val cardType: String = "",
    val bankInfo:Bank = Bank()
) : Parcelable {
    companion object {
        fun mapToBankCard(dto: BankCardDto): BankCard {
            return BankCard(
                id=0,
                country = dto.country.name,
                coordinates = Coordinates(dto.country.latitude,dto.country.longitude),
                cardType = dto.scheme,
                bankInfo = dto.bank
            )
        }
    }
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
