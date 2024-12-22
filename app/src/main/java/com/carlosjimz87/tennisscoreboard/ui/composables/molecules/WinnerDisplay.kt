package com.carlosjimz87.tennisscoreboard.ui.composables.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.ui.unit.dp
import com.carlosjimz87.tennisscoreboard.ui.screens.scoreboard.state.ScoreboardUiState
import com.carlosjimz87.tennisscoreboard.ui.theme.Colors

@Composable
fun ColumnScope.WinnerDisplay(
    state: ScoreboardUiState,
    onResetGame: () -> Unit
){

    // Winner announcement
    state.matchWinner?.let { winner ->
        Spacer(modifier = Modifier.weight(1f))

        Box(
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .clip(RoundedCornerShape(16.dp)) // Apply rounded corners
                .background(Colors.green)
                .padding(16.dp)
        ) {
            Text(
                text = "${winner.desc} WON!",
                style = MaterialTheme.typography.displayMedium,
                color = Colors.yellow,
            )
        }


        Spacer(modifier = Modifier.weight(1f))

        Button(
            onClick = onResetGame,
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .height(64.dp)
                .padding(top = 8.dp),
            colors = ButtonDefaults.buttonColors(containerColor = Colors.red)
        ) {
            Text("Reset Game")
        }
    }
}