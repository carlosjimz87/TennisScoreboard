package com.carlosjimz87.tennisscoreboard.ui.composables.atoms
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.widthIn
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.carlosjimz87.tennisscoreboard.ui.theme.Colors

@Composable
fun RowScope.CurrentGame(currentGameScore: String, isTieBreak: Boolean) {
    val color = if (isTieBreak) Colors.red else Colors.darkGreen

    Text(
        text = currentGameScore,
        style = MaterialTheme.typography.headlineMedium,
        modifier = Modifier
            .weight(1f)
            .background(color)
            .padding(horizontal = 2.dp, vertical = 2.dp)
            .widthIn(min = 50.dp), // Ensure a minimum width sufficient for 3 characters
        textAlign = TextAlign.Center,
        color = Colors.yellow,
        fontWeight = FontWeight.Bold
    )
}