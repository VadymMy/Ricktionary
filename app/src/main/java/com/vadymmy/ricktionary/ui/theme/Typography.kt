package com.vadymmy.ricktionary.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import com.vadymmy.ricktionary.App
import com.vadymmy.ricktionary.R

val NumansFontFamily = FontFamily(
    Font(resId = R.font.numans, weight = FontWeight.Normal),
    Font(resId = R.font.numans, weight = FontWeight.Bold),
    Font(resId = R.font.numans, weight = FontWeight.ExtraBold)
)

val Typography = Typography(
    titleLarge = TextStyle(
        fontFamily = NumansFontFamily,
        fontWeight = FontWeight.ExtraBold,
        fontSize = textSizeLarge,
        color = AppColors.TextPrimary
    ),
    titleMedium = TextStyle(
        fontFamily = NumansFontFamily,
        fontWeight = FontWeight.Bold,
        fontSize = textSizeMedium,
        color = AppColors.TextPrimary
    ),
    bodyLarge = TextStyle(
        fontFamily = NumansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = textSizeNormal,
        color = AppColors.TextPrimary
    ),
    bodySmall = TextStyle(
        fontFamily = NumansFontFamily,
        fontWeight = FontWeight.Normal,
        fontSize = textSizeSmall,
        color = AppColors.TextPrimary
    )
)
