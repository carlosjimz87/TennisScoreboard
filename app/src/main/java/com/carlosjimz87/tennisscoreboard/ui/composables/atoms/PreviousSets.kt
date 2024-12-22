package com.carlosjimz87.tennisscoreboard.ui.composables.atoms
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import com.carlosjimz87.tennisscoreboard.ui.theme.Colors
import com.carlosjimz87.tennisscoreboard.ui.theme.Colors.green

@Composable
fun RowScope.PreviousSets(previousSetScores: List<Int>) {
    previousSetScores.forEach { score ->
        Text(
            text = score.toString(),
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.Companion.weight(1f).background(green),
            textAlign = TextAlign.Center,
            color = Colors.yellow,
            fontWeight = FontWeight.Bold
        )
    }
}