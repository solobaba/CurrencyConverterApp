package com.solobaba.currencyconverter.domain.repository

import com.solobaba.currencyconverter.domain.response.DomainConvertCurrencyResponse
import com.solobaba.currencyconverter.domain.response.DomainCurrencySymbolsResponse
import com.solobaba.currencyconverter.domain.response.DomainCurrencyTimesResponse
import com.solobaba.currencyconverter.domain.response.DomainLatestCurrencyResponse
import com.solobaba.currencyconverter.utils.ApiResult
import kotlinx.coroutines.flow.Flow

interface DomainCurrencyRepository {
    suspend fun getLatestRates(apiKey: String): Flow<ApiResult<DomainLatestCurrencyResponse>>

    suspend fun convertCurrency(
        apiKey: String,
        from: String,
        to: String,
        amount: Double
    ): Flow<ApiResult<DomainConvertCurrencyResponse>>

    suspend fun getSymbols(apiKey: String): Flow<ApiResult<DomainCurrencySymbolsResponse>>

    suspend fun getTimeSeriesRates(
        apiKey: String,
        startDate: String,
        endDate: String,
        base: String,
        symbols: String
    ): Flow<ApiResult<DomainCurrencyTimesResponse>>
}