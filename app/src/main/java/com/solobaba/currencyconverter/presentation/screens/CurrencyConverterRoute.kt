package com.solobaba.currencyconverter.presentation.screens

sealed class CurrencyConverterRoute(val route: String) {
    data object CurrencyConverterScreen : CurrencyConverterRoute(route = "currencyConverterScreen")
}