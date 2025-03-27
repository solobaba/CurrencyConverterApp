package com.solobaba.currencyconverter.data.remote.response

import com.google.gson.annotations.SerializedName
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class CurrencyTimesResponse(
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
    var errorRemote: ErrorResponse?
)