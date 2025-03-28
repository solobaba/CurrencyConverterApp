package com.solobaba.currencyconverter.data.mapper

import com.solobaba.currencyconverter.data.remote.response.CurrencySymbolsResponse
import com.solobaba.currencyconverter.data.remote.response.Symbols
import com.solobaba.currencyconverter.domain.response.DomainCurrencySymbolsResponse
import com.solobaba.currencyconverter.domain.response.DomainSymbols

class DomainSymbolsRemoteSymbolsMapper {
    fun mapDomainSymbolsToRemoteSymbolsResponse(
        currencySymbolsResponse: CurrencySymbolsResponse?): DomainCurrencySymbolsResponse? {
        if (currencySymbolsResponse == null) return null
        return with(currencySymbolsResponse) {
            DomainCurrencySymbolsResponse(
                success = success,
                symbols = symbols
            )
        }
    }
}