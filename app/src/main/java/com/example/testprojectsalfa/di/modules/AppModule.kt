package com.example.testprojectsalfa.di.modules

import com.example.testprojectsalfa.data.BinRepositoryImpl
import com.example.testprojectsalfa.di.components.AppScope
import com.example.testprojectsalfa.domain.BinRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
interface AppModule {
    @AppScope
    @Binds
    fun bindShopListRepository(impl: BinRepositoryImpl): BinRepository
}