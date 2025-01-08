package com.example.testprojectsalfa.presentation.mainScreen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.testprojectsalfa.domain.BankCard
import com.example.testprojectsalfa.domain.usecases.GetBankCardByBinUseCase
import com.example.testprojectsalfa.domain.usecases.GetRequestHistoryListUseCase
import com.example.testprojectsalfa.domain.usecases.SaveBankCardUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

class MainScreenViewModel @Inject constructor(
    private val getBankCardByBinUseCase: GetBankCardByBinUseCase,
    private val saveBankCardUseCase: SaveBankCardUseCase,
    private val getRequestHistoryListUseCase: GetRequestHistoryListUseCase
) : ViewModel() {

    val screenState = MutableStateFlow(ScreenState.Initial as ScreenState)


    suspend fun getBankCardByBin(bin: String = "45717360") {
        viewModelScope.launch {
          val bankCard = getBankCardByBinUseCase(bin)
            saveBankCardUseCase(bankCard)
            screenState.emit(ScreenState.Loaded(bankCard))
        }
    }
    suspend fun getRequestHistoryList() : List<BankCard>{
        return getRequestHistoryListUseCase()
    }
}