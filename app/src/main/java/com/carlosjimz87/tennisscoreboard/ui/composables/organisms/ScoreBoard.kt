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
import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.ui.screens.scoreboard.state.ScoreboardUiState
import com.carlosjimz87.tennisscoreboard.ui.theme.Colors


@Composable
fun ScoreBoard(
    modifier: Modifier = Modifier,
    players : List<Player>,
    state: ScoreboardUiState,
) {
    Column(modifier = modifier) {

        Text(
            text = "Scoreboard 🎾",
            style = MaterialTheme.typography.displayMedium,
            fontWeight = FontWeight.Bold,
            color = Colors.yellow,
            modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
        )

        Spacer(modifier = Modifier.height(8.dp))

        Column {
            players.forEach { player ->
                Text(
                    text = "${player.desc} Score",
                    style = MaterialTheme.typography.headlineMedium,
                    color = Colors.white,
                    fontWeight = FontWeight.Bold
                )
            }
        }

    }
}