package com.carlosjimz87.tennisscoreboard.ui.theme

import androidx.compose.material3.Typography
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

// Set of Material typography styles to start with
val Typography = Typography(
    displayLarge = TextStyle(
        fontFamily = FontOswald,
        fontWeight = FontWeight.Bold,
        fontSize = 52.sp,
        lineHeight = 24.sp,
        letterSpacing = 0.5.sp
    ),
    titleLarge = TextStyle(
        fontFamily = FontOswald,
        fontWeight = FontWeight.Medium,
        fontSize = 32.sp,
        lineHeight = 28.sp,
        letterSpacing = 0.sp
    ),
    labelLarge = TextStyle(
        fontFamily = FontOswald,
        fontWeight = FontWeight.Bold,
        fontSize = 28.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),

    labelMedium = TextStyle(
        fontFamily = FontOswald,
        fontWeight = FontWeight.Bold,
        fontSize = 24.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    ),

    labelSmall = TextStyle(
        fontFamily = FontOswald,
        fontWeight = FontWeight.Bold,
        fontSize = 22.sp,
        lineHeight = 16.sp,
        letterSpacing = 0.5.sp
    )

)