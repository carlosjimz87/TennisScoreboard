package com.carlosjimz87.tennisscoreboard.ui.composables.atoms
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carlosjimz87.tennisscoreboard.ui.theme.Colors

@Composable
fun PlayerName(playerName: String) {
    Text(
        text = playerName,
        style = MaterialTheme.typography.titleLarge.copy(
            color = Colors.white,
        ),
        modifier = Modifier.width(140.dp),
    )
}