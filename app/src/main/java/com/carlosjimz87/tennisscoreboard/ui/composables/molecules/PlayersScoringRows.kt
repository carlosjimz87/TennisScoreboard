package com.carlosjimz87.tennisscoreboard.ui.composables.molecules
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ColumnScope
import androidx.compose.runtime.Composable
import com.carlosjimz87.tennisscoreboard.ui.screens.scoreboard.state.ScoreboardUiState

@Composable
fun ColumnScope.PlayersScoringRows(state: ScoreboardUiState) {
    Column{
        state.playersScore.forEach { (player, score) ->
            PlayerRow(
                player = player,
                isTieBreak = state.isTieBreak,
                isServing = score.isServing,
                previousSetScores = score.previousSetScores,
                currentSetScore = score.currentSetScore,
                currentGameScore = score.currentGameScore,
                matchWinner = state.matchWinner
            )
        }
    }
}