package com.solobaba.currencyconverter.presentation.state

import com.solobaba.currencyconverter.domain.response.DomainCurrencyTimesResponse

data class TimeSeriesRatesState (
    var isLoading: Boolean = false,
    var timeSeriesRatesResponse: DomainCurrencyTimesResponse? = null,
    var error: String? = null
)
