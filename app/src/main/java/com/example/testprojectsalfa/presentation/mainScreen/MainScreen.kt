package com.example.testprojectsalfa.presentation.mainScreen

import android.content.Intent
import android.net.Uri
import android.widget.TextView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.content.ContextCompat.startActivity

@Preview
@Composable
fun MainScreen() {

    val bin = rememberSaveable {
        mutableStateOf("")
    }
    val textFieldIsEmpty = rememberSaveable {
        mutableStateOf(true)
    }
    Column(
        modifier = Modifier
            .background(Color.White)
            .fillMaxSize()
            .padding(all = 10.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        TextField(
            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
                .align(Alignment.CenterHorizontally),
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
            trailingIcon = { Icons.Filled.Clear },
            onValueChange = {
                textFieldIsEmpty.value = it == ""
                bin.value = it
            },
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number)
        )

    }
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