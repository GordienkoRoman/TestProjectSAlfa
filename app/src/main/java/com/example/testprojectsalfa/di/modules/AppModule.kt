package com.example.testprojectsalfa.di.modules

import com.example.testprojectsalfa.data.BinRepositoryImpl
import com.example.testprojectsalfa.di.components.AppScope
import com.example.testprojectsalfa.domain.BinRepository
import dagger.Binds
import dagger.Module

@Module
interface AppModule {
    @AppScope
    @Binds
    fun bindShopListRepository(impl: BinRepositoryImpl): BinRepository
}