package com.example.testprojectsalfa.presentation.requestHistoryListScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBarsPadding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.testprojectsalfa.domain.BankCard

@Composable
fun RequestHistoryListScreen(requestHistoryList: MutableState<List<BankCard>>) {
    LazyColumn(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colorScheme.primary)
            .navigationBarsPadding(),
        verticalArrangement = Arrangement.spacedBy(15.dp)
    ) {
        items(requestHistoryList.value.size)
        {
            Text(text = requestHistoryList.value[it].bin)
        }
    }
}