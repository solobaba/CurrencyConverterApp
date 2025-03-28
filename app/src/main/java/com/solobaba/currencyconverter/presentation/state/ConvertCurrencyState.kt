package com.solobaba.currencyconverter.presentation.state

import com.solobaba.currencyconverter.domain.response.DomainConvertCurrencyResponse

data class ConvertCurrencyState (
    var isLoading: Boolean = false,
    var convertCurrencyResponse: DomainConvertCurrencyResponse? = null,
    var error: String? = null
)
