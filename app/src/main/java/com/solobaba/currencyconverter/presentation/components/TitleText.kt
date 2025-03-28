package com.solobaba.currencyconverter.presentation.components

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import com.solobaba.currencyconverter.R

@Composable
fun TitleText(text: String) {
    Text(
        text = text,
        color = Color(0xFF2D69DD),
        fontSize = 36.sp,
        fontWeight = FontWeight.Bold,
        fontFamily = FontFamily(Font(R.font.montserrat_bold)),
    )
}