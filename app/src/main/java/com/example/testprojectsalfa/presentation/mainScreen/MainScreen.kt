package com.example.testprojectsalfa.presentation.mainScreen

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testprojectsalfa.di.viewModelFactory.ViewModelFactory
import com.example.testprojectsalfa.domain.BankCard
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import okhttp3.OkHttpClient


@Composable
fun MainScreen(
    viewModelFactory: ViewModelFactory,
    onHistoryRequestClick: (List<BankCard>) -> Unit,
) {

    val viewModel: MainScreenViewModel = viewModel(factory = viewModelFactory)
    val screenState = viewModel.screenState.collectAsState(ScreenState.Initial)

    val coroutineScope = rememberCoroutineScope()
    val bin = rememberSaveable {
        mutableStateOf("")
    }
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(all = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        BinTextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.CenterHorizontally), bin
        )
        Button(onClick = {
            coroutineScope.async {
                viewModel.getBankCardByBin(bin.value)
            }
        }, enabled = bin.value.length in 6..8) {
            Text(text = "Lookup")
        }
        when (val currentState = screenState.value) {
            ScreenState.Initial -> {}
            ScreenState.Loading -> {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.background
                )
            }

            is ScreenState.Error -> {
                CircularProgressIndicator(
                    color = MaterialTheme.colorScheme.background
                )
            }

            is ScreenState.Loaded -> {
                BankCardInfo(bankCard = currentState.bankCard)
            }
        }
        Button(onClick = {
            coroutineScope.launch {
                val list = viewModel.getRequestHistoryList()
                onHistoryRequestClick(list)
            }
        }) {
            Text(text = "Reuest history")
        }
    }
}

@Composable
fun BinTextField(modifier: Modifier, bin: MutableState<String>) {
    TextField(
        modifier = modifier,
        value = bin.value,
        textStyle = TextStyle(
            textAlign = TextAlign.Center
        ),
        placeholder = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "4571  7360",
                textAlign = TextAlign.Center
            )
        },
        supportingText = { Text(text = "Enter the first 6 to 8 digits of a card number (BIN/IIN)") },
        colors = TextFieldDefaults.colors(
            unfocusedContainerColor = Color.White,
            focusedContainerColor = Color.White,
            unfocusedIndicatorColor = Color.Gray,
            focusedIndicatorColor = Color.Blue
        ),
        trailingIcon = {
            IconButton(onClick = { bin.value = "" }) {
                Icons.Filled.Clear
            }
        },
        onValueChange = {
            if (it.length <= 8 && it.isDigitsOnly())
                bin.value = it
        },
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
    )
}

@Composable
fun BankCardInfo(bankCard: BankCard) {
    Text(text = bankCard.country)
    Text(text = bankCard.coordinates.latitude)
    Text(text = bankCard.coordinates.longitude)
    Text(text = bankCard.cardType)
    Text(text = "Bank")
    Text(text = bankCard.bankInfo.name)
    Text(text = bankCard.bankInfo.url)
    Text(text = bankCard.bankInfo.city)
    Text(text = bankCard.bankInfo.phone)
}

//    //Не правильно показывает, мб данные некорректные на сайте.
//    val gmmIntentUri =
//        Uri.parse("geo:${user.coordinates.latitude},${user.coordinates.longitude}")
//    val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
//    mapIntent.setPackage("com.google.android.apps.maps")
//    startActivity(context, mapIntent, null)
//}
//val call = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${user.number}"))
//startActivity(context, call, null)
//val intent = Intent(Intent.ACTION_SENDTO).apply {
//    data = Uri.parse("mailto:") // Only email apps handle this.
//    putExtra(Intent.EXTRA_SUBJECT, "Hi ${user.name},")
//    putExtra(Intent.EXTRA_EMAIL, arrayOf(user.mail))
//}
//startActivity(context, intent, null)