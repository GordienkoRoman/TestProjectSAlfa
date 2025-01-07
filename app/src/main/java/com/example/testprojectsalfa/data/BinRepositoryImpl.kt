package com.example.testprojectsalfa.data

import com.example.testprojectsalfa.data.remote.BankCardDto
import com.example.testprojectsalfa.domain.BankCard
import com.example.testprojectsalfa.domain.BinRepository
import com.example.testprojectsalfa.presentation.mainScreen.ScreenState
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import javax.inject.Inject

class BinRepositoryImpl @Inject constructor() : BinRepository {
    override suspend fun getBankCardByBin(bin: String): BankCard {
        var bankCard = BankCard()
        withContext(Dispatchers.IO) {
            try {
                val request = Request.Builder()
                    .url("https://lookup.binlist.net/$bin")
                    .build()
                val response = OkHttpClient().newCall(request).execute()
                if (response.isSuccessful) {
                    val responseData = response.body()?.string()
                    val moshi = Moshi.Builder()
                        .add(KotlinJsonAdapterFactory()).build()
                    val jsonAdapterResponse = moshi.adapter(BankCardDto::class.java)
                    val jsonResponse = jsonAdapterResponse.fromJson(responseData)
                    bankCard =
                        jsonResponse?.let { BankCard.mapToBankCard(it) } ?: throw IOException()
                    return@withContext bankCard
                }
                else{

                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
        return bankCard

    }


    override fun getRequestHistoryList(): List<BankCard> {
        TODO("Not yet implemented")
    }

    override fun saveBankCard() {
        TODO("Not yet implemented")
    }
}