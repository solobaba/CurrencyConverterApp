package com.solobaba.currencyconverter.data.repositoryImpl

import com.solobaba.currencyconverter.data.mapper.DomainConvertRemoteConvertMapper
import com.solobaba.currencyconverter.data.mapper.DomainLatestRemoteLatestMapper
import com.solobaba.currencyconverter.data.mapper.DomainSymbolsRemoteSymbolsMapper
import com.solobaba.currencyconverter.data.mapper.DomainTimeSeriesRemoteTimeSeriesMapper
import com.solobaba.currencyconverter.data.remote.CurrencyConverterService
import com.solobaba.currencyconverter.domain.repository.DomainCurrencyRepository
import com.solobaba.currencyconverter.domain.response.DomainConvertCurrencyResponse
import com.solobaba.currencyconverter.domain.response.DomainCurrencySymbolsResponse
import com.solobaba.currencyconverter.domain.response.DomainCurrencyTimesResponse
import com.solobaba.currencyconverter.domain.response.DomainLatestCurrencyResponse
import com.solobaba.currencyconverter.utils.ApiResult
import com.solobaba.currencyconverter.utils.ApiServiceResult
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class CurrencyConverterRepositoryImpl @Inject constructor(
    private val currencyConverterService: CurrencyConverterService
) : DomainCurrencyRepository {
    private val domainLatestRemoteLatestMapper: DomainLatestRemoteLatestMapper =
        DomainLatestRemoteLatestMapper()
    private val domainConvertRemoteConvertMapper: DomainConvertRemoteConvertMapper =
        DomainConvertRemoteConvertMapper()
    private val domainSymbolsRemoteSymbolsMapper: DomainSymbolsRemoteSymbolsMapper =
        DomainSymbolsRemoteSymbolsMapper()
    private val domainTimeSeriesRemoteTimeSeriesMapper: DomainTimeSeriesRemoteTimeSeriesMapper =
        DomainTimeSeriesRemoteTimeSeriesMapper()

    override suspend fun getLatestRates(apiKey: String): Flow<ApiResult<DomainLatestCurrencyResponse>> {
        return flow {
            emit(ApiResult.Loading(true))

            val latestCurrencyResponse = try {
                currencyConverterService.getLatestRates(apiKey)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(ApiResult.Error("Error loading latest currency data"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(ApiResult.Error(message = "Error loading latest currency data"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiResult.Error(message = "Error loading latest currency data"))
                return@flow
            }

            emit(
                ApiResult.Success(
                    domainLatestRemoteLatestMapper.mapDomainLatestCurrencyToRemoteLatestCurrency(
                        latestCurrencyResponse
                    )
                )
            )
            emit(ApiResult.Loading(false))
        }
    }

    override suspend fun convertCurrency(
        apiKey: String,
        from: String,
        to: String,
        amount: Double
    ): Flow<ApiServiceResult<DomainConvertCurrencyResponse>> {
        return flow {
            val convertCurrencyResponse = try {
                currencyConverterService.convertCurrency(apiKey, from, to, amount)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(ApiServiceResult.Error("Error converting currency data"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(ApiServiceResult.Error(message = "Error converting currency data"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiServiceResult.Error(message = "Error converting currency data"))
                return@flow
            }

            emit(
                ApiServiceResult.Success(
                    domainConvertRemoteConvertMapper.mapDomainConvertCurrencyToRemoteConvertCurrency(
                        convertCurrencyResponse
                    )
                )
            )
            emit(ApiServiceResult.Error(
                convertCurrencyResponse.error?.info ?: "Error converting currency data"
            ))
        }
    }

    override suspend fun getSymbols(apiKey: String): Flow<ApiResult<DomainCurrencySymbolsResponse>> {
        return flow {
            emit(ApiResult.Loading(true))

            val symbolsResponse = try {
                currencyConverterService.getSymbols(apiKey)
            } catch (e: IOException) {
                e.printStackTrace()
                emit(ApiResult.Error("Error loading symbols data"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(ApiResult.Error(message = "Error loading symbols data"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiResult.Error(message = "Error loading symbols data"))
                return@flow
            }

            emit(
                ApiResult.Success(
                    domainSymbolsRemoteSymbolsMapper.mapDomainSymbolsToRemoteSymbolsResponse(
                        symbolsResponse
                    )
                )
            )
            emit(ApiResult.Loading(false))
        }
    }

    override suspend fun getTimeSeriesRates(
        apiKey: String,
        startDate: String,
        endDate: String,
        base: String,
        symbols: String
    ): Flow<ApiResult<DomainCurrencyTimesResponse>> {
        return flow {
            emit(ApiResult.Loading(true))

            val timeSeriesResponse = try {
                currencyConverterService.getTimeSeriesRates(
                    apiKey,
                    startDate,
                    endDate,
                    base,
                    symbols
                )
            } catch (e: IOException) {
                e.printStackTrace()
                emit(ApiResult.Error("Error loading time series data"))
                return@flow
            } catch (e: HttpException) {
                e.printStackTrace()
                emit(ApiResult.Error(message = "Error loading time series data"))
                return@flow
            } catch (e: Exception) {
                e.printStackTrace()
                emit(ApiResult.Error(message = "Error loading time series data"))
                return@flow
            }

            emit(ApiResult.Success(
                    domainTimeSeriesRemoteTimeSeriesMapper.mapDomainTimeSeriesToRemoteTimeSeriesResponse(
                        timeSeriesResponse)))
            emit(ApiResult.Loading(false))
        }
    }
}