package com.carlosjimz87.tennisscoreboard.ui.theme

import android.os.Build
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.dynamicDarkColorScheme
import androidx.compose.material3.dynamicLightColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext

private val DarkColorScheme = darkColorScheme(
    primary = Colors.yellow, // Bright yellow for high contrast
    onPrimary = Colors.black, // Text on yellow (high contrast)
    secondary = Colors.green, // Green for accents
    onSecondary = Colors.white, // Text on green
    tertiary = Colors.red, // Red for warnings or highlights
    onTertiary = Colors.white, // Text on red
    background = Colors.darkGreen, // Dark green for overall background
    onBackground = Colors.white, // Text on background
    surface = Colors.black, // Black for surfaces like cards
    onSurface = Colors.white // Text on surface
)

private val LightColorScheme = lightColorScheme(
    primary = Colors.green, // Green for main theme color
    onPrimary = Colors.white, // Text on green
    secondary = Colors.yellow, // Yellow for accents
    onSecondary = Colors.black, // Text on yellow
    tertiary = Colors.red, // Red for warnings or highlights
    onTertiary = Colors.white, // Text on red
    background = Colors.white, // White for overall background
    onBackground = Colors.black, // Text on background
    surface = Colors.yellow, // Yellow for surfaces like cards
    onSurface = Colors.black // Text on surface
)

@Composable
fun TennisScoreboardTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    // Dynamic color is available on Android 12+
    dynamicColor: Boolean = true,
    content: @Composable () -> Unit
) {
    val colorScheme = when {
        dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
            val context = LocalContext.current
            if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
        }

        darkTheme -> DarkColorScheme
        else -> LightColorScheme
    }

    MaterialTheme(
        colorScheme = colorScheme,
        typography = Typography,
        content = content
    )
}