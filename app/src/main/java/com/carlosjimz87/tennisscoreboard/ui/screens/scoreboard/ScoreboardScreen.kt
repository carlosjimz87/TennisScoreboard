package com.carlosjimz87.tennisscoreboard.ui.screens.scoreboard


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.carlosjimz87.tennisscoreboard.ui.composables.organisms.ScoreBoard
import com.carlosjimz87.tennisscoreboard.ui.composables.organisms.ScoreButtons
import com.carlosjimz87.tennisscoreboard.ui.screens.scoreboard.state.ScoreboardUiState
import com.carlosjimz87.tennisscoreboard.ui.screens.scoreboard.viewmodel.ScoreBoardViewModel

@Composable
fun ScoreboardScreen(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    viewModel: ScoreBoardViewModel
) {

    val uiState by viewModel.boardUiState.collectAsState(initial = ScoreboardUiState())
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(backgroundColor)
            .padding(16.dp)
    ) {
        // Scoreboard Header
        ScoreBoard(
            modifier = Modifier.fillMaxWidth(),
            state = uiState
        ) {
            viewModel.resetMatch()
        }

        Spacer(modifier = Modifier.weight(1f))

        // Buttons to annotate points
        ScoreButtons(
            modifier,
            players = uiState.playersScore.keys.toList(),
            matchWinner = uiState.matchWinner,
        ) { viewModel.onPointScored(it) }
    }
}