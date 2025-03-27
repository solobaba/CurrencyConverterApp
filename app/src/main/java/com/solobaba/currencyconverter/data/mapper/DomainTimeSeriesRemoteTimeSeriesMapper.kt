package com.solobaba.currencyconverter.data.mapper

import com.solobaba.currencyconverter.data.remote.response.CurrencyTimesResponse
import com.solobaba.currencyconverter.data.remote.response.ErrorResponse
import com.solobaba.currencyconverter.domain.response.DomainCurrencyTimesResponse
import com.solobaba.currencyconverter.domain.response.DomainErrorResponse

class DomainTimeSeriesRemoteTimeSeriesMapper {
    fun mapDomainTimeSeriesToRemoteTimeSeriesResponse(
        currencyTimesResponse: CurrencyTimesResponse?): DomainCurrencyTimesResponse? {
        if (currencyTimesResponse == null) return null
        return with(currencyTimesResponse) {
            DomainCurrencyTimesResponse(
                success = success,
                timeSeries = timeSeries,
                base = base,
                startDate = startDate,
                endDate = endDate,
                rates = rates,
                errorRemote = errorRemote?.toDomainErrorResponse()
            )
        }
    }

    fun ErrorResponse.toDomainErrorResponse(): DomainErrorResponse {
        return DomainErrorResponse(
            code = code,
            type = type,
            info = info
        )
    }
}