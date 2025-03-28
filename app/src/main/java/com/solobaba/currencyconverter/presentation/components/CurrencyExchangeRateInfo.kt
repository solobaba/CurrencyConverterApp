package com.solobaba.currencyconverter.presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solobaba.currencyconverter.R
import com.solobaba.currencyconverter.utils.SpacerVertical4Dp

@Composable
fun CurrencyExchangeRateInfo() {
    val showBottomSheet = remember { mutableStateOf(false) }

    if (showBottomSheet.value) {
        TimeSeriesBottomSheetView(onDismiss = { showBottomSheet.value = false })
    }

    Row(
        modifier = Modifier
            .fillMaxWidth()
            .clickable { showBottomSheet.value = true }
            .padding(8.dp),
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Text(
            text = stringResource(id = R.string.mid_market_rate),
            fontSize = 14.sp,
            fontFamily = FontFamily(Font(R.font.mulish_regular)),
            color = Color(0xFF2D69DD),
        )

        SpacerVertical4Dp()

        Surface(
            modifier = Modifier.size(24.dp),
            shape = RoundedCornerShape(12.dp),
            color = Color.LightGray.copy(alpha = 0.3f),
        ) {
            Box(contentAlignment = Alignment.Center) {
                Icon(
                    imageVector = Icons.Default.Info,
                    contentDescription = stringResource(id = R.string.information),
                    modifier = Modifier.size(16.dp),
                    tint = Color(0xFF4A90E2),
                )
            }
        }
    }
}