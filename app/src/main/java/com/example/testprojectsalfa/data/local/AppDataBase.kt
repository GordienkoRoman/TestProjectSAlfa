package com.example.testprojectsalfa.data.local

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.testprojectsalfa.data.local.dao.BankCardDao
import com.example.testprojectsalfa.data.local.entities.BankCardDbModel

@Database(
    entities = [BankCardDbModel::class],
    version = 1,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {
    abstract val bankCardDao: BankCardDao
}