package com.example.testprojectsalfa.data

import android.content.Context
import com.example.testprojectsalfa.data.local.dao.BankCardDao
import com.example.testprojectsalfa.data.mapper.BankCardMapper
import com.example.testprojectsalfa.data.remote.BankCardDto
import com.example.testprojectsalfa.domain.BankCard
import com.example.testprojectsalfa.domain.BinRepository
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import javax.inject.Inject

class BinRepositoryImpl @Inject constructor(
    private val bankCardDao: BankCardDao,
    private val mapper: BankCardMapper,
    val context: Context,
) : BinRepository {
    override suspend fun getBankCardByBin(bin: String): BankCard? {
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
                    val jsonResponse = jsonAdapterResponse.fromJson(responseData.toString())
                    val bankCard =
                        jsonResponse?.let { mapper.mapDtoToEntity(it, bin) } ?: throw IOException()
                    return@withContext bankCard
                } else {
                    throw Exception("HTTP ${response.code()}")
                }
            } catch (e: Exception) {
                throw Exception(e)

            }
        }
        return null
    }


    override suspend fun getRequestHistoryList(): List<BankCard> {
        return bankCardDao.getBankCards().map { mapper.mapDbModelToEntity(it) }
    }

    override suspend fun saveBankCard(bankCard: BankCard) {
        bankCardDao.insertBankCard(mapper.mapEntityToDbModel(bankCard))
    }
}