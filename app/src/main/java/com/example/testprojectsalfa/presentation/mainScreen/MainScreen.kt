package com.example.testprojectsalfa.presentation.mainScreen

import android.content.Intent
import android.net.Uri
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity
import androidx.core.text.isDigitsOnly
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.testprojectsalfa.di.viewModelFactory.ViewModelFactory
import com.example.testprojectsalfa.domain.BankCard
import kotlinx.coroutines.async
import kotlinx.coroutines.launch


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
                Toast.makeText(LocalContext.current, currentState.e, Toast.LENGTH_SHORT).show()
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
            Text(text = "Request history")
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
    val context = LocalContext.current
    Text(text = "Country:${bankCard.country}")
    Row(modifier = Modifier.clickable {
        val gmmIntentUri =
            Uri.parse("geo:${bankCard.coordinates.latitude},${bankCard.coordinates.longitude}")
        val mapIntent = Intent(Intent.ACTION_VIEW, gmmIntentUri)
        mapIntent.setPackage("com.google.android.apps.maps")
            startActivity(context, mapIntent, null)
     }) {
        Text(text = "Coordinates:${bankCard.coordinates.latitude},${bankCard.coordinates.longitude}", textDecoration = TextDecoration.Underline)
    }
    Text(text = "Scheme${bankCard.cardType}")
    Text(text = "Bank Info")
    Text(text = "Name:${bankCard.bankInfo.name}")
    Text(modifier = Modifier.clickable{
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse(bankCard.bankInfo.url))
        try {
            startActivity(context,browserIntent,null)
        }
        catch (_: Exception){
            Toast.makeText(context,"Incorrect url",Toast.LENGTH_SHORT).show()
        }
    },text = "URL:${bankCard.bankInfo.url}", textDecoration = TextDecoration.Underline)
    Text(text = "City:${bankCard.bankInfo.city}")
    Text(modifier = Modifier.clickable {
        val call = Intent(Intent.ACTION_DIAL, Uri.parse("tel:${bankCard.bankInfo.phone}"))
        startActivity(context, call, null)
    },text = "Phone:${bankCard.bankInfo.phone}", textDecoration = TextDecoration.Underline)
}

