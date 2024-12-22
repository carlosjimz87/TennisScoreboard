package com.carlosjimz87.tennisscoreboard.ui.screens.scoreboard


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.ui.composables.organisms.ScoreBoard
import com.carlosjimz87.tennisscoreboard.ui.composables.organisms.ScoreButtons
import com.carlosjimz87.tennisscoreboard.ui.screens.scoreboard.state.ScoreboardUiState
import com.carlosjimz87.tennisscoreboard.ui.screens.scoreboard.viewmodel.ScoreBoardViewModel
import com.carlosjimz87.tennisscoreboard.ui.theme.Colors

@Composable
fun ScoreboardScreen(
    modifier: Modifier = Modifier,
    viewModel: ScoreBoardViewModel
) {

    val uiState by viewModel.boardUiState.collectAsState(initial = ScoreboardUiState())
    val players = listOf(Player.PLAYER1, Player.PLAYER2)
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Colors.darkGreen)
            .padding(16.dp)
    ) {
        // Scoreboard Header
        ScoreBoard(
            modifier = Modifier.fillMaxWidth(),
            players = players,
            state = uiState
        )

        Spacer(modifier = Modifier.weight(1f))

        // Buttons to annotate points
        ScoreButtons(
            modifier,
            players = players,
        ) { viewModel.onPointScored(it) }
    }
}