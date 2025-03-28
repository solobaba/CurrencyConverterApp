package com.solobaba.currencyconverter

import android.os.Build
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Snackbar
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.navigation.compose.rememberNavController
import com.solobaba.currencyconverter.presentation.navigation.CurrencyConverterNavigation
import com.solobaba.currencyconverter.ui.theme.CurrencyConverterTheme
import com.solobaba.currencyconverter.utils.Extensions.loadMapOfCurrencySymbolToFlag
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //enableEdgeToEdge()
        setContent {
            val navController = rememberNavController()
            val snackBarHostState = remember { SnackbarHostState() }
            CurrencyConverterTheme {
                Scaffold(
                    modifier = Modifier.fillMaxSize(),
                    containerColor = Color.White,
                    snackbarHost = {
                        SnackbarHost(hostState = snackBarHostState) { data ->
                            Snackbar(
                                snackbarData = data,
                                containerColor = Color.Red, //Custom background
                                contentColor = Color.White  //Custom text color
                            )
                        }
                    }
                ) { innerPadding ->
                    Box(
                        content = {
                            CurrencyConverterNavigation(
                                navController = navController,
                                innerPadding = innerPadding,
                                snackBarHostState = snackBarHostState,
                                currencySymbolsFlag = loadMapOfCurrencySymbolToFlag(assets)
                            )
                        }
                    )
                }
            }
        }
    }
}