package com.carlosjimz87.tennisscoreboard.ui.composables.atoms
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.carlosjimz87.tennisscoreboard.ui.theme.Colors

@Composable
fun CurrentGame(currentGameScore: String, isTieBreak: Boolean) {
    val backgroundColor = if (isTieBreak) Colors.red else Colors.darkGreen

    Text(
        text = currentGameScore,
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(horizontal = 2.dp, vertical = 2.dp)
            .defaultMinSize(minWidth = 60.dp), // Minimum width to accommodate "DEU" or "ADV"
        textAlign = TextAlign.Center,
        color = Colors.yellow,
        fontWeight = FontWeight.Bold
    )
}