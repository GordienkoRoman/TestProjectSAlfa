package com.example.testprojectsalfa.data.local.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(
    tableName = "bank_cards"
)
data class BankCardDbModel (
    @ColumnInfo(name = "country")
    val country: String,
    @ColumnInfo(name = "latitude")
    val latitude: String,
    @ColumnInfo(name = "longitude")
    val longitude: String,
    @ColumnInfo(name = "card_type")
    val cardType: String,
    @ColumnInfo(name = "bank_name")
    val bankName: String,
    @ColumnInfo(name = "url")
    val url: String,
    @ColumnInfo(name = "phone")
    val phone: String,
    @ColumnInfo(name = "city")
    val city: String,
    @ColumnInfo(name = "bin")
    val bin: String,
    @PrimaryKey(autoGenerate = true)
    val id:Int
    )