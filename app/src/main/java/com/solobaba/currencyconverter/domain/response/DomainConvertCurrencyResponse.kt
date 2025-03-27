package com.solobaba.currencyconverter.domain.response

data class DomainConvertCurrencyResponse(
    val success: Boolean,
    val query: DomainQueryData?,
    val info: DomainInfoData?,
    val historical: Boolean?,
    val date: String?,
    val result: Double?,
    val error: DomainErrorResponse?
)

data class DomainQueryData(
    val from: String,
    val to: String,
    val amount: Double
)

data class DomainInfoData(
    val timestamp: Long,
    val rate: Double
)

data class DomainErrorResponse(
    val code: Int,
    val type: String,
    val info: String
)
