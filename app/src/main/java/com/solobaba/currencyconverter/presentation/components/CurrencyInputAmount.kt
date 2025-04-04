package com.solobaba.currencyconverter.presentation.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.solobaba.currencyconverter.R
import kotlin.math.truncate

@Composable
fun CurrencyInputAmount(
    amount: String,
    currency: String,
    symbol: String,
    onAmountChange: (String) -> Unit,
    isReadOnly: Boolean,
    enabled: Boolean
) {
    TextField(
        value = amount,
        onValueChange = onAmountChange,
        readOnly = isReadOnly,
        enabled = enabled,
        textStyle = TextStyle(
            fontSize = 18.sp,
            fontFamily = FontFamily(Font(R.font.mulish_bold)),
            color = Color.Gray
        ),
        modifier = Modifier
            .fillMaxWidth()
            .border(
                width = 0.5.dp,
                color = Color.LightGray,
                shape = RoundedCornerShape(6.dp)
            ),
        singleLine = true,
        trailingIcon = {
            Text(
                modifier = Modifier.padding(end = 8.dp),
                text = currency,
                fontSize = 18.sp,
                fontFamily = FontFamily(Font(R.font.mulish_bold)),
                color = Color.Gray
            )
        },
        colors = TextFieldDefaults.textFieldColors(
            textColor = Color.DarkGray,
            backgroundColor = Color.White,
            disabledTextColor = Color.DarkGray,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            disabledIndicatorColor = Color.Transparent,
            cursorColor = Color.LightGray
        ),
        //Keyboard should only show numbers
        keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number)
    )

//    Surface(
//        modifier = Modifier
//            .fillMaxWidth()
//            .padding(vertical = 8.dp),
//        shape = RoundedCornerShape(8.dp),
//        color = Color(0xFFFAFAFA),
//    ) {
//        Row(
//            modifier = Modifier.padding(16.dp),
//            horizontalArrangement = Arrangement.SpaceBetween,
//            verticalAlignment = Alignment.CenterVertically,
//        ) {
//            if (isReadOnly) {
//                Text(
//                    text = "$symbol$amount",
//                    fontSize = 24.sp,
//                    fontFamily = FontFamily(Font(R.font.mulish_bold)),
//                    color = Color.Gray
//                )
//            } else {
//                BasicTextField(
//                    value = amount,
//                    onValueChange = onAmountChange,
//                    textStyle = TextStyle(
//                        fontSize = 24.sp,
//                        fontFamily = FontFamily(Font(R.font.mulish_bold)),
//                        color = Color.Gray
//                    ),
//                    keyboardOptions = KeyboardOptions.Default.copy(keyboardType = KeyboardType.Number),
//                    modifier = Modifier.width(100.dp)
//                )
//            }
//
//            Text(
//                text = currency,
//                fontSize = 18.sp,
//                fontFamily = FontFamily(Font(R.font.mulish_bold)),
//                color = Color.Gray
//            )
//        }
//    }
}