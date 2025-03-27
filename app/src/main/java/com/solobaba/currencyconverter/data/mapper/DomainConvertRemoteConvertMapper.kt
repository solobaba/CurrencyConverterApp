package com.solobaba.currencyconverter.data.mapper

import com.solobaba.currencyconverter.data.remote.response.ConvertCurrencyResponse
import com.solobaba.currencyconverter.data.remote.response.ErrorResponse
import com.solobaba.currencyconverter.data.remote.response.InfoData
import com.solobaba.currencyconverter.data.remote.response.QueryData
import com.solobaba.currencyconverter.domain.response.DomainConvertCurrencyResponse
import com.solobaba.currencyconverter.domain.response.DomainErrorResponse
import com.solobaba.currencyconverter.domain.response.DomainInfoData
import com.solobaba.currencyconverter.domain.response.DomainQueryData

class DomainConvertRemoteConvertMapper {
    fun mapDomainConvertCurrencyToRemoteConvertCurrency(
        convertCurrencyResponse: ConvertCurrencyResponse?): DomainConvertCurrencyResponse? {
        if (convertCurrencyResponse == null) return null
        return with(convertCurrencyResponse) {
            DomainConvertCurrencyResponse(
                success = success,
                query = query?.toDomainQueryData(),
                info = info?.toDomainInfoData(),
                historical = historical,
                date = date,
                result = result,
                error = error?.toDomainErrorResponse()
            )
        }
    }

    fun QueryData.toDomainQueryData(): DomainQueryData {
        return DomainQueryData(
            from = from,
            to = to,
            amount = amount
        )
    }

    fun InfoData.toDomainInfoData(): DomainInfoData {
        return DomainInfoData(
            timestamp = timestamp,
            rate = rate
        )
    }

    fun ErrorResponse.toDomainErrorResponse(): DomainErrorResponse {
        return DomainErrorResponse(
            code = code,
            type = type,
            info = info
        )
    }
}