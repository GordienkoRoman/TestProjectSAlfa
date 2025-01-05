package com.example.testprojectsalfa.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.testprojectsalfa.presentation.mainScreen.MainScreen
import com.example.testprojectsalfa.ui.theme.TestProjectSAlfaTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestProjectSAlfaTheme {
                MainScreen()
            }
        }
    }
}
