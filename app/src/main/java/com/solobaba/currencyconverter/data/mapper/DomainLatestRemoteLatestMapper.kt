package com.solobaba.currencyconverter.data.mapper

import com.solobaba.currencyconverter.data.remote.response.LatestCurrencyResponse
import com.solobaba.currencyconverter.data.remote.response.Rates
import com.solobaba.currencyconverter.domain.response.DomainLatestCurrencyResponse
import com.solobaba.currencyconverter.domain.response.DomainRates

class DomainLatestRemoteLatestMapper {
    fun mapDomainLatestCurrencyToRemoteLatestCurrency(
        latestCurrencyResponse: LatestCurrencyResponse?): DomainLatestCurrencyResponse? {
        if (latestCurrencyResponse == null) return null
        return with(latestCurrencyResponse) {
            DomainLatestCurrencyResponse(
                success = success,
                timestamp = timestamp,
                base = base,
                date = date,
                rates = rates
            )
        }
    }
}