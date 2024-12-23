package com.carlosjimz87.tennisscoreboard.ui.composables.atoms
import androidx.compose.foundation.layout.RowScope
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import com.carlosjimz87.tennisscoreboard.ui.theme.Colors

@Composable
fun RowScope.PreviousSets(previousSetScores: List<Int>) {
    previousSetScores.forEach { score ->
        Text(
            text = score.toString(),
            style = MaterialTheme.typography.labelMedium,
            modifier = Modifier.Companion.weight(1f),
            textAlign = TextAlign.Center,
            color = Colors.yellow,
        )
    }
}