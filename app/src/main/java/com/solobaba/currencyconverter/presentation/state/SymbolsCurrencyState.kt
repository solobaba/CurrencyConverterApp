package com.solobaba.currencyconverter.presentation.state

import com.solobaba.currencyconverter.domain.response.DomainCurrencySymbolsResponse

data class SymbolsCurrencyState (
    var isLoading: Boolean = false,
    var symbolsCurrencyResponse: DomainCurrencySymbolsResponse? = null,
    var error: String? = null
)
