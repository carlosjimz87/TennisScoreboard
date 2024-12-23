package com.carlosjimz87.tennisscoreboard.ui.theme

import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.googlefonts.Font
import androidx.compose.ui.text.googlefonts.GoogleFont
import com.carlosjimz87.tennisscoreboard.R

val provider = GoogleFont.Provider(
    providerAuthority = "com.google.android.gms.fonts",
    providerPackage = "com.google.android.gms",
    certificates = R.array.com_google_android_gms_fonts_certs
)

val FontOswald = FontFamily(
    Font(
        googleFont = GoogleFont("Oswald"),
        fontProvider = provider,
    )
)