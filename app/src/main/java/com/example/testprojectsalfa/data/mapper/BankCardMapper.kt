package com.example.testprojectsalfa.data.mapper

import com.example.testprojectsalfa.data.local.entities.BankCardDbModel
import com.example.testprojectsalfa.data.remote.BankCardDto
import com.example.testprojectsalfa.domain.Bank
import com.example.testprojectsalfa.domain.BankCard
import com.example.testprojectsalfa.domain.Coordinates
import javax.inject.Inject


class BankCardMapper @Inject constructor() {

    fun mapEntityToDbModel(bankCard: BankCard): BankCardDbModel {
        return BankCardDbModel(
            country = bankCard.country,
            latitude = bankCard.coordinates.latitude,
            longitude = bankCard.coordinates.longitude,
            cardType = bankCard.cardType,
            bankName = bankCard.bankInfo.name,
            url = bankCard.bankInfo.url,
            phone = bankCard.bankInfo.phone,
            city = bankCard.bankInfo.city,
            bin = bankCard.bin,
            id = 0
        )
    }

    fun mapDtoToEntity(dto: BankCardDto,bin:String): BankCard {
        return BankCard(
            bin = bin,
            country = dto.country.name,
            coordinates = Coordinates(dto.country.latitude, dto.country.longitude),
            cardType = dto.scheme,
            bankInfo = dto.bank
        )
    }

    fun mapDbModelToEntity(dbModel: BankCardDbModel):BankCard
    {
        return BankCard(
            bin = dbModel.bin,
            country = dbModel.country,
            coordinates = Coordinates(dbModel.latitude, dbModel.longitude),
            cardType = dbModel.cardType,
            bankInfo = Bank(dbModel.bankName,dbModel.url,dbModel.phone,dbModel.city)
        )
    }
}