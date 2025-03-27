package com.solobaba.currencyconverter.data.remote.response

import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ConvertCurrencyResponse(
    val success: Boolean,
    val query: QueryData?,
    val info: InfoData?,
    val historical: Boolean?,
    val date: String?,
    val result: Double?,
    val error: ErrorResponse?
)

@JsonClass(generateAdapter = true)
data class QueryData(
    val from: String,
    val to: String,
    val amount: Double
)

@JsonClass(generateAdapter = true)
data class InfoData(
    val timestamp: Long,
    val rate: Double
)

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    val code: Int,
    val type: String,
    val info: String
)
