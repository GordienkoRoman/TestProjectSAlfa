package com.example.testprojectsalfa.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.testprojectsalfa.BaseApplication
import com.example.testprojectsalfa.di.viewModelFactory.ViewModelFactory
import com.example.testprojectsalfa.domain.BankCard
import com.example.testprojectsalfa.presentation.mainScreen.MainScreen
import com.example.testprojectsalfa.presentation.requestHistoryListScreen.RequestHistoryListScreen
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
            val list : MutableState<List<BankCard>> = rememberSaveable {
                mutableStateOf(listOf())
            } //Не лучшее решение, но простое
            val navController: NavHostController = rememberNavController()
            TestProjectSAlfaTheme {
                NavHost(
                    modifier = Modifier,
                    navController = navController,
                    startDestination = "mainscreen",
                    route = "maingraph"
                )
                {
                    composable(route = "mainscreen") {
                        MainScreen(
                            viewModelFactory,
                            onHistoryRequestClick = {
                                list.value = it
                                navController.navigate("list")
                            })
                    }
                    composable("list", arguments = listOf()) {
                        RequestHistoryListScreen(requestHistoryList = list)
                    }
                }
            }
        }
    }
}
