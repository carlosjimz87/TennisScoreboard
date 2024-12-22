package com.carlosjimz87.tennisscoreboard.ui.screens.scoreboard.state

import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.models.PlayerScore

data class ScoreboardUiState(
    val player1Display: PlayerScore = PlayerScore(),
    val player2Display: PlayerScore = PlayerScore(),
    val isTieBreak: Boolean = false,
    val matchWinner: Player? = null
)