package com.solobaba.currencyconverter

import app.cash.turbine.test
import com.solobaba.currencyconverter.data.remote.Constants
import com.solobaba.currencyconverter.domain.repository.DomainCurrencyRepository
import com.solobaba.currencyconverter.domain.response.DomainCurrencySymbolsResponse
import com.solobaba.currencyconverter.presentation.viewmodel.CurrencyConverterViewmodel
import com.solobaba.currencyconverter.utils.ApiResult
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.advanceUntilIdle
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class CurrencyConverterViewmodelTest {
    //Mocks
    private lateinit var currencyRepository: DomainCurrencyRepository
    private lateinit var currencyConverterViewmodel: CurrencyConverterViewmodel
    private lateinit var domainCurrencySymbolsResponse: DomainCurrencySymbolsResponse

    //Test scope
    @get:Rule
    val mainDispatcherRule = MainDispatcherRule()

    @Before
    fun setUp() {
        currencyRepository = mockk(relaxed = true)
        currencyConverterViewmodel = CurrencyConverterViewmodel(currencyRepository)
        domainCurrencySymbolsResponse = mockk(relaxed = true)
    }

    @Test
    fun `getCurrencySymbols should update symbolsCurrencyResponse`() = runTest {
        val fakeCurrencySymbols = mapOf("USD" to "United States Dollar", "EUR" to "Euro")
        val fakeCurrencySymbolsResponse = DomainCurrencySymbolsResponse(symbols = fakeCurrencySymbols)
        coEvery { currencyRepository.getSymbols(Constants.FIXER_API_KEY) } returns flowOf(
            ApiResult.Success(fakeCurrencySymbolsResponse))

        println("Mocked symbols copy = ${domainCurrencySymbolsResponse.copy(symbols = fakeCurrencySymbols)}")
        println("Mocked symbols = $fakeCurrencySymbolsResponse")

        currencyConverterViewmodel.getCurrencySymbols()

        //Allow time for flow to emit and viewmodel to update state
        advanceUntilIdle()

        val actualSymbols = currencyConverterViewmodel.symbolsCurrencyResponse.value?.symbolsCurrencyResponse?.symbols
        println("Actual symbols: $actualSymbols")
        assertEquals(fakeCurrencySymbols, actualSymbols)
    }

    @Test
    fun `updateCurrencyAmount updates amount state`() {
        runTest {
            currencyConverterViewmodel.updateAmount ("100")
            currencyConverterViewmodel.amount.test {
                assertEquals("100", awaitItem())
            }
        }
    }
}