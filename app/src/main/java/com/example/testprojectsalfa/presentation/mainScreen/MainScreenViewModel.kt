package com.example.testprojectsalfa.presentation.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testprojectsalfa.data.remote.BankCardDto
import com.example.testprojectsalfa.domain.BankCard
import com.example.testprojectsalfa.domain.GetBankCardByBinUseCase
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.OkHttpClient
import okhttp3.Request
import java.io.IOException
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val getBankCardByBinUseCase: GetBankCardByBinUseCase
) : ViewModel() {

    val screenState = MutableStateFlow(ScreenState.Initial as ScreenState)


    suspend fun test2(client: OkHttpClient, bin: String = "45717360") {
        viewModelScope.launch {
          val bankCard = getBankCardByBinUseCase(bin)
            screenState.emit(ScreenState.Loaded(bankCard))
        }
//        withContext(Dispatchers.IO) {
//            try {
//                val request = Request.Builder()
//                    .url("https://lookup.binlist.net/$bin")
//                    .build()
//                val response = client.newCall(request).execute()
//                if(response.isSuccessful)
//                {
//                    val responseData = response.body()?.string()
//                    val moshi = Moshi.Builder()
//                        .add(KotlinJsonAdapterFactory()).build()
//                    val jsonAdapterResponse = moshi.adapter(BankCardDto::class.java)
//                    val jsonResponse = jsonAdapterResponse.fromJson(responseData)
//                    val bankCard = jsonResponse?.let { BankCard.mapToBankCard(it) }?: throw IOException()
//                    screenState.emit(ScreenState.Loaded(bankCard))
//                }
//            } catch (e: Exception) {
//                screenState.emit(ScreenState.Error(e.toString()))
//                e.printStackTrace()
//            }
//
//        }
    }
}