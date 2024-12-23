package com.carlosjimz87.tennisscoreboard.ui.composables.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.carlosjimz87.tennisscoreboard.R
import com.carlosjimz87.tennisscoreboard.ui.screens.scoreboard.state.ScoreboardUiState
import com.carlosjimz87.tennisscoreboard.ui.theme.Colors
import com.carlosjimz87.tennisscoreboard.utils.playSound

@Composable
fun ColumnScope.WinnerDisplay(
    state: ScoreboardUiState,
    onResetGame: () -> Unit
) {
    val context = LocalContext.current

    // Winner announcement
    state.matchWinner?.let { winner ->
        playSound(context = context, R.raw.applause)

        Spacer(modifier = Modifier.height(32.dp)) // Add some space at the top

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(16.dp)) // Apply rounded corners
                .background(Colors.green)
                .padding(16.dp)
        ) {
            Text(
                text = "${winner.name} WON!",
                style = MaterialTheme.typography.displayLarge,
                color = Colors.yellow,
            )
        }

        Spacer(modifier = Modifier.height(32.dp)) // Add some space between the message and button

        Button(
            onClick = onResetGame,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(68.dp)
                .padding(horizontal = 32.dp, vertical = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Colors.red)
        ) {
            Text(
                text = "Reset Game",
                style = MaterialTheme.typography.labelMedium.copy(
                    color = Colors.white,
                ),
            )
        }
    }
}