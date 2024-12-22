package com.carlosjimz87.tennisscoreboard.domain.states

import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.ui.screens.scoreboard.state.ScoreboardUiState

data class MatchState(
    val sets: List<SetState> = listOf(SetState()),
    val servingPlayer: Player = Player.PLAYER1,
    val isTieBreak: Boolean = false,
    val winner: Player? = null,
    val maxSets: Int = 3 // This must be configurable
) {
    fun toScoreBoardUiState() : ScoreboardUiState{
        return ScoreboardUiState()
    }
}