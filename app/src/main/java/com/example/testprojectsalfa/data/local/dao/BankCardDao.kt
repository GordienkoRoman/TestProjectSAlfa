package com.example.testprojectsalfa.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.testprojectsalfa.data.local.entities.BankCardDbModel

@Dao
interface BankCardDao {

    @Insert(entity = BankCardDbModel::class, onConflict = OnConflictStrategy.IGNORE)
    suspend fun insertBankCard(user: BankCardDbModel)

    @Query("SELECT * FROM bank_cards")
    suspend fun getBankCards(): List<BankCardDbModel>
}