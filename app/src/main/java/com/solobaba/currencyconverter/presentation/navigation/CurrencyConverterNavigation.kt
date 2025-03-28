package com.solobaba.currencyconverter.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.material3.SnackbarHostState
import androidx.navigation.compose.NavHost
import com.solobaba.currencyconverter.presentation.screens.CurrencyConverterRoute

@Composable
fun CurrencyConverterNavigation(
    navController: NavHostController,
    innerPadding: PaddingValues,
    snackBarHostState: SnackbarHostState,
    currencySymbolsFlag: MutableMap<String, String>
) {
    NavHost(
        navController = navController, startDestination = CurrencyConverterRoute.CurrencyConverterScreen.route
    ){
        currencyConverterRoute(navController, innerPadding, snackBarHostState, currencySymbolsFlag)
    }
}