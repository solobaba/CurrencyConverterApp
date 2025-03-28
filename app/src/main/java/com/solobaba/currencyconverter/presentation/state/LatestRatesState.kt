package com.solobaba.currencyconverter.presentation.state

import com.solobaba.currencyconverter.domain.response.DomainLatestCurrencyResponse

data class LatestRatesState (
    var isLoading: Boolean = false,
    var latestRates: DomainLatestCurrencyResponse? = null,
    var error: String? = null
)
