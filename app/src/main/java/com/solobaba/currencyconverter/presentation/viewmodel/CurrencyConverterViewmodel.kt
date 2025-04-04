package com.solobaba.currencyconverter.presentation.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.solobaba.currencyconverter.data.remote.Constants
import com.solobaba.currencyconverter.domain.repository.DomainCurrencyRepository
import com.solobaba.currencyconverter.domain.response.DomainConvertCurrencyResponse
import com.solobaba.currencyconverter.presentation.state.ConvertCurrencyState
import com.solobaba.currencyconverter.presentation.state.LatestRatesState
import com.solobaba.currencyconverter.presentation.state.SymbolsCurrencyState
import com.solobaba.currencyconverter.presentation.state.TimeSeriesRatesState
import com.solobaba.currencyconverter.utils.ApiResult
import com.solobaba.currencyconverter.utils.ApiServiceResult
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyConverterViewmodel @Inject constructor(
    private val domainCurrencyRepository: DomainCurrencyRepository
) : ViewModel() {
    private val _getLatestRatesResponse: MutableStateFlow<LatestRatesState> =
        MutableStateFlow(LatestRatesState())
    val getLatestRatesResponse: StateFlow<LatestRatesState?> = _getLatestRatesResponse.asStateFlow()

    private val _convertCurrencyResponse: MutableStateFlow<ConvertCurrencyState> =
        MutableStateFlow(ConvertCurrencyState())
    val convertCurrencyResponse: StateFlow<ConvertCurrencyState?> = _convertCurrencyResponse.asStateFlow()

    private val _convertCurrencyResult = MutableStateFlow<ApiServiceResult<DomainConvertCurrencyResponse>?>(null)
    val convertCurrencyResult: StateFlow<ApiServiceResult<DomainConvertCurrencyResponse>?> = _convertCurrencyResult

    private val _symbolsCurrencyResponse: MutableStateFlow<SymbolsCurrencyState> =
        MutableStateFlow(SymbolsCurrencyState())
    val symbolsCurrencyResponse: StateFlow<SymbolsCurrencyState?> = _symbolsCurrencyResponse.asStateFlow()

    private val _timeSeriesRatesResponse: MutableStateFlow<TimeSeriesRatesState> =
        MutableStateFlow(TimeSeriesRatesState())
    val timeSeriesRatesResponse: StateFlow<TimeSeriesRatesState?> = _timeSeriesRatesResponse.asStateFlow()

    private val _amount = MutableStateFlow("1")
    val amount: StateFlow<String> = _amount

    private val _fromCurrency = MutableStateFlow("EUR")
    val fromCurrency: StateFlow<String> = _fromCurrency

    private val _toCurrency = MutableStateFlow("PLN")
    val toCurrency: StateFlow<String> = _toCurrency

    private val _isConverting = MutableStateFlow(false)
    val isConverting: StateFlow<Boolean> = _isConverting

    fun updateAmount(newAmount: String) {
        _amount.value = newAmount
    }

    private val _loading = MutableStateFlow(false)
    val loading: StateFlow<Boolean> = _loading.asStateFlow()

    init {
        getCurrencySymbols()
        getLatestRatesCurrency()
    }

    private fun getLatestRatesCurrency() {
        viewModelScope.launch {
            _getLatestRatesResponse.update {
                it.copy(isLoading = true)
            }

            domainCurrencyRepository.getLatestRates(Constants.FIXER_API_KEY).collectLatest { result ->
                when (result) {
                    is ApiResult.Error -> {
                        _getLatestRatesResponse.update {
                            it.copy(
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                    is ApiResult.Loading -> {
                        _getLatestRatesResponse.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                    is ApiResult.Success -> {
                        _getLatestRatesResponse.update {
                            it.copy(
                                isLoading = false,
                                latestRates = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    fun convertCurrency(baseCurrency: String, targetCurrency: String) {
        viewModelScope.launch {
            _isConverting.value = true
            _convertCurrencyResult.value = ApiServiceResult.Loading

            val amountValue = _amount.value.toDoubleOrNull() ?: 0.0

            domainCurrencyRepository.convertCurrency (
                Constants.FIXER_API_KEY,
                baseCurrency,
                targetCurrency,
                amountValue
                ).collectLatest { result ->
                _convertCurrencyResult.value = result
            }
            _isConverting.value = false
        }
    }

    fun getCurrencySymbols() {
        viewModelScope.launch {
            _symbolsCurrencyResponse.update {
                it.copy(isLoading = true)
            }

            domainCurrencyRepository.getSymbols(Constants.FIXER_API_KEY).collectLatest { result ->
                when (result) {
                    is ApiResult.Error -> {
                        _symbolsCurrencyResponse.update {
                            it.copy(
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                    is ApiResult.Loading -> {
                        _symbolsCurrencyResponse.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                    is ApiResult.Success -> {
                        _symbolsCurrencyResponse.update {
                            it.copy(
                                isLoading = false,
                                symbolsCurrencyResponse = result.data
                            )
                        }
                    }
                }
            }
        }
    }

    fun getTimeSeriesRates() {
        viewModelScope.launch {
            _timeSeriesRatesResponse.update {
                it.copy(isLoading = true)
            }

            val startDate = "2025-01-01"
            val endDate = "2025-02-01"

            domainCurrencyRepository.getTimeSeriesRates(
                apiKey = Constants.FIXER_API_KEY,
                startDate = startDate,
                endDate = endDate,
                base = _fromCurrency.value,
                symbols = "USD, GBP, PLN"
            ).collectLatest { result ->
                when (result) {
                    is ApiResult.Error -> {
                        _timeSeriesRatesResponse.update {
                            it.copy(
                                isLoading = false,
                                error = result.message
                            )
                        }
                    }
                    is ApiResult.Loading -> {
                        _timeSeriesRatesResponse.update {
                            it.copy(isLoading = result.isLoading)
                        }
                    }
                    is ApiResult.Success -> {
                        _timeSeriesRatesResponse.update {
                            it.copy(
                                isLoading = false,
                                timeSeriesRatesResponse = result.data
                            )
                        }
                    }
                }
            }
        }
    }
}