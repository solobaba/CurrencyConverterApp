package com.solobaba.currencyconverter.presentation.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solobaba.currencyconverter.R

@Composable
fun CurrencySelector(currency: String, symbol: String, modifier: Modifier) {
    Surface(
        modifier = modifier.padding(horizontal = 8.dp),
        shape = RoundedCornerShape(8.dp),
        color = Color.White,
        border = ButtonDefaults.outlinedButtonBorder,
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = currency,
                fontSize = 16.sp,
                fontFamily = FontFamily(Font(R.font.mulish_regular))
            )
            Icon(
                painter = painterResource(id = R.drawable.ic_caret_down),
                contentDescription = null
            )
        }
    }
}