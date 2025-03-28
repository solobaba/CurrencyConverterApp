package com.solobaba.currencyconverter.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.solobaba.currencyconverter.R

@Composable
fun CurrencySelection(fromCurrency: String, toCurrency: String, currencySymbols: Map<String, String>) {
    Row(
        modifier = Modifier.fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        CurrencySelector(
            currency = fromCurrency,
            symbol = currencySymbols[fromCurrency] ?: "",
            modifier = Modifier.weight(1f)
        )

        Box(
            modifier = Modifier.padding(horizontal = 8.dp),
            contentAlignment = Alignment.Center,
        ) {
            Spacer(modifier = Modifier.height(50.dp))
            Image(
                painter = painterResource(id = R.drawable.currency_swap_img),
                modifier = Modifier.size(16.dp),
                colorFilter = ColorFilter.tint(Color(0xFFD3D3D3)),
                contentDescription = null,
            )
        }

        CurrencySelector(
            currency = toCurrency,
            symbol = currencySymbols[toCurrency] ?: "",
            modifier = Modifier.weight(1f)
        )
    }
}