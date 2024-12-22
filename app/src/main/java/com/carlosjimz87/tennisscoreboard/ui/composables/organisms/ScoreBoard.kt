package com.carlosjimz87.tennisscoreboard.ui.composables.organisms
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.carlosjimz87.tennisscoreboard.ui.composables.molecules.PlayersScoringRows
import com.carlosjimz87.tennisscoreboard.ui.composables.molecules.WinnerDisplay
import com.carlosjimz87.tennisscoreboard.ui.screens.scoreboard.state.ScoreboardUiState
import com.carlosjimz87.tennisscoreboard.ui.theme.Colors


@Composable
fun ScoreBoard(
    modifier: Modifier = Modifier,
    state: ScoreboardUiState,
    onResetGame: () -> Unit
) {
    println("ScoreBoard: ${state.matchWinner}")
    Column(modifier = modifier) {

        Text(
            text = "Scoreboard 🎾",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = Colors.yellow,
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        PlayersScoringRows(state)

        // Winner Display only if there's a winner
        WinnerDisplay(
            state = state,
            onResetGame = onResetGame
        )
    }
}