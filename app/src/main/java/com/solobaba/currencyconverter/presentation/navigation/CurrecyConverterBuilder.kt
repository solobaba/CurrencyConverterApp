package com.solobaba.currencyconverter.presentation.navigation

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.SnackbarHostState
import androidx.compose.ui.Modifier
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.solobaba.currencyconverter.presentation.screens.CurrencyConverterRoute
import com.solobaba.currencyconverter.presentation.screens.CurrencyConverterScreen

fun NavGraphBuilder.currencyConverterRoute(
    navController: NavHostController,
    innerPadding: PaddingValues,
    snackBarHostState: SnackbarHostState,
    currencySymbolsFlag: MutableMap<String, String>
){
    composable(route = CurrencyConverterRoute.CurrencyConverterScreen.route){
        Box(modifier = Modifier.padding(innerPadding)) {
            CurrencyConverterScreen(navController, snackBarHostState, currencySymbolsFlag)
        }
    }
}