package com.solobaba.currencyconverter.domain.response

data class CountryList(
    val isoAlpha3: String,
    val currency: Currency,
    val flag: String
)

data class Currency(
    val code: String
)
