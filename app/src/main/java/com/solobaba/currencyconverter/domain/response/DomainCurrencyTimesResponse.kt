package com.solobaba.currencyconverter.domain.response

import com.google.gson.annotations.SerializedName

data class DomainCurrencyTimesResponse(
    val success: Boolean,
    @SerializedName("timeseries")
    val timeSeries: Boolean,
    @SerializedName("start_date")
    val startDate: String,
    @SerializedName("end_date")
    val endDate: String,
    val base: String,
    val rates: Map<String, Map<String, Double>>,
    @SerializedName("error")
    var errorRemote: DomainErrorResponse?
)