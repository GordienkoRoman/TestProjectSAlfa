package com.example.testprojectsalfa.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import com.example.testprojectsalfa.BaseApplication
import com.example.testprojectsalfa.di.viewModelFactory.ViewModelFactory
import com.example.testprojectsalfa.presentation.mainScreen.MainScreen
import com.example.testprojectsalfa.ui.theme.TestProjectSAlfaTheme
import javax.inject.Inject

class MainActivity : ComponentActivity() {

    @Inject
    lateinit var viewModelFactory: ViewModelFactory

    private val component by lazy {
        (application as BaseApplication).component
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        component.inject(this)
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            TestProjectSAlfaTheme {
                MainScreen(viewModelFactory)
            }
        }
    }
}
