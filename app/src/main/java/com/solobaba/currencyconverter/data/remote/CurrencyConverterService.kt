package com.solobaba.currencyconverter.data.remote

import com.solobaba.currencyconverter.data.remote.response.ConvertCurrencyResponse
import com.solobaba.currencyconverter.data.remote.response.CurrencySymbolsResponse
import com.solobaba.currencyconverter.data.remote.response.CurrencyTimesResponse
import com.solobaba.currencyconverter.data.remote.response.LatestCurrencyResponse
import retrofit2.http.GET
import retrofit2.http.Query

interface CurrencyConverterService {
    @GET("latest")
    suspend fun getLatestRates(
        @Query("access_key") apiKey: String,
    ): LatestCurrencyResponse

    @GET("convert")
    suspend fun convertCurrency(
        @Query("access_key") apiKey: String,
        @Query("from") from: String,
        @Query("to") to: String,
        @Query("amount") amount: Double,
    ): ConvertCurrencyResponse

    @GET("symbols")
    suspend fun getSymbols(
        @Query("access_key") accessKey: String,
    ): CurrencySymbolsResponse

    @GET("timeseries")
    suspend fun getTimeSeriesRates(
        @Query("access_key") apiKey: String,
        @Query("start_date") startDate: String,
        @Query("end_date") endDate: String,
        @Query("base") base: String,
        @Query("symbols") symbols: String,
    ): CurrencyTimesResponse
}