package com.solobaba.currencyconverter.presentation.screens

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.SnackbarHostState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.solobaba.currencyconverter.R
import com.solobaba.currencyconverter.domain.response.DomainConvertCurrencyResponse
import com.solobaba.currencyconverter.presentation.components.CurrencyConvertButton
import com.solobaba.currencyconverter.presentation.components.CurrencyDropDownPicker
import com.solobaba.currencyconverter.presentation.components.CurrencyExchangeRateInfo
import com.solobaba.currencyconverter.presentation.components.CurrencyInputAmount
import com.solobaba.currencyconverter.presentation.components.CurrencySelection
import com.solobaba.currencyconverter.presentation.components.TitleText
import com.solobaba.currencyconverter.presentation.components.TopAppBar
import com.solobaba.currencyconverter.presentation.state.SymbolsCurrencyState
import com.solobaba.currencyconverter.presentation.viewmodel.CurrencyConverterViewmodel
import com.solobaba.currencyconverter.utils.ApiServiceResult
import com.solobaba.currencyconverter.utils.SpacerVertical16Dp
import com.solobaba.currencyconverter.utils.SpacerVertical24Dp
import com.solobaba.currencyconverter.utils.SpacerVertical32Dp

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun CurrencyConverterScreen(
    navController: NavHostController,
    snackBarHostState: SnackbarHostState,
    currencySymbolsFlag: MutableMap<String, String>
) {
    val viewmodel = hiltViewModel<CurrencyConverterViewmodel>()
    val latestRate = viewmodel.getLatestRatesResponse.collectAsState().value
    val convertCurrency = viewmodel.convertCurrencyResult.collectAsState().value
    val symbolsCurrency = when (val symbolsResult = viewmodel.symbolsCurrencyResponse.collectAsState().value) {
        is SymbolsCurrencyState -> symbolsResult.symbolsCurrencyResponse
        else -> null
    }
    val timeSeriesRates = viewmodel.timeSeriesRatesResponse.collectAsState().value
    val amount = viewmodel.amount.collectAsState().value
    val fromCurrency = viewmodel.fromCurrency.collectAsState().value
    val toCurrency = viewmodel.toCurrency.collectAsState().value
    val isConverting = viewmodel.isConverting.collectAsState().value
    val loading = viewmodel.loading.collectAsState().value

    //Initialize mutable states with default values as trailing texts for MainRateTextFields
    var baseCurrencySymbol by rememberSaveable { mutableStateOf("EUR") }
    var targetCurrencySymbol by rememberSaveable { mutableStateOf("PLN") }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.White)
    ){
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            TopAppBar()
            SpacerVertical32Dp()
            TitleText(stringResource(R.string.currency))
            TitleText(stringResource(R.string.calculator))
            SpacerVertical24Dp()
            CurrencyInputAmount(
                amount = amount,
                currency = fromCurrency,
                symbol = symbolsCurrency?.symbols?.get(fromCurrency) ?: "",
                onAmountChange = { viewmodel.updateAmount(it) },
                isReadOnly = false
            )
            CurrencyInputAmount(
                amount = (convertCurrency as? ApiServiceResult.Success)?.data?.result?.toString() ?: "0",
                currency = toCurrency,
                symbol = "",
                onAmountChange = { viewmodel.updateAmount(it) },
                isReadOnly = true
            )

            if (isConverting) {
                CircularProgressIndicator(
                    modifier = Modifier.align(Alignment.CenterHorizontally),
                    color = Color.Red
                )
            } else {
                when (convertCurrency) {
                    is ApiServiceResult.Error -> {
                        val errorMessage = convertCurrency.message

                        //Show snackbar message
                        LaunchedEffect(Unit) {
                            snackBarHostState.showSnackbar(
                                message = errorMessage ?: "Unknown error",
                                actionLabel = "Dismiss")
                        }
                    }
                    ApiServiceResult.Loading -> {}
                    is ApiServiceResult.Success -> {
                        val result = (convertCurrency as? ApiServiceResult.Success<DomainConvertCurrencyResponse>)?.data
                        CurrencyInputAmount(
                            amount = result?.result?.toString() ?: "0",
                            currency = toCurrency,
                            symbol = symbolsCurrency?.symbols?.get(toCurrency) ?: "",
                            onAmountChange = { },
                            isReadOnly = true
                        )
                    }
                    null -> {}
                }
            }
            SpacerVertical16Dp()

            //Currency pickers row
            Row(
                modifier = Modifier
                    .fillMaxSize()
                    .align(Alignment.CenterHorizontally),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                //Base currency picker
                CurrencyDropDownPicker(
                    modifier = Modifier,
                    readOnly = false,
                    enabled = true,
                    defaultSymbol = "EUR",
                    mapOfCurrencySymbolsToFlag = currencySymbolsFlag,
                    onSymbolSelected = { newText -> baseCurrencySymbol = newText }
                )

                Image(
                    painter = painterResource(id = R.drawable.currency_swap_img),
                    modifier = Modifier.size(16.dp),
                    colorFilter = ColorFilter.tint(Color(0xFFD3D3D3)),
                    contentDescription = null,
                )

                //Rate currency picker
                CurrencyDropDownPicker(
                    modifier = Modifier,
                    readOnly = false,
                    enabled = true,
                    defaultSymbol = "PLN",
                    mapOfCurrencySymbolsToFlag = currencySymbolsFlag,
                    onSymbolSelected = { newText -> baseCurrencySymbol = newText }
                )
            }
//            CurrencySelection(
//                fromCurrency = fromCurrency,
//                toCurrency = toCurrency,
//                currencySymbols = symbolsCurrency?.symbols ?: emptyMap()
//            )
            SpacerVertical24Dp()
            CurrencyConvertButton(viewmodel)
            SpacerVertical16Dp()
            CurrencyExchangeRateInfo()
        }
    }
}