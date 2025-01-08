package com.example.testprojectsalfa.di.modules

import android.content.Context
import androidx.room.Room
import com.example.testprojectsalfa.data.local.AppDatabase
import com.example.testprojectsalfa.data.local.dao.BankCardDao
import com.example.testprojectsalfa.di.components.AppScope
import dagger.Module
import dagger.Provides

@Module
class DataBaseModule {

    @AppScope
    @Provides
    fun provideAppDatabase(context: Context): AppDatabase {
        return Room.databaseBuilder(context, AppDatabase::class.java, "database.db")
            .fallbackToDestructiveMigration()
            .allowMainThreadQueries()
            .build()

    }

    @AppScope
    @Provides
    fun provideUsersDao(appDataBase: AppDatabase): BankCardDao = appDataBase.bankCardDao
}