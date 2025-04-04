package com.solobaba.currencyconverter.presentation.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solobaba.currencyconverter.R
import com.solobaba.currencyconverter.presentation.viewmodel.CurrencyConverterViewmodel

@Composable
fun CurrencyConvertButton(
    viewModel: CurrencyConverterViewmodel,
    baseCurrencySymbol: String,
    targetCurrencySymbol: String
) {
    Button(
        onClick = { viewModel.convertCurrency(baseCurrencySymbol, targetCurrencySymbol) },
        modifier = Modifier
            .fillMaxWidth()
            .height(56.dp),
        shape = RoundedCornerShape(8.dp),
        colors = ButtonDefaults.buttonColors(containerColor = Color(0xFF50E3A4)),
    ) {
        Text(
            text = stringResource(R.string.convert),
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.mulish_bold)),
            color = Color.White,
        )
    }
}