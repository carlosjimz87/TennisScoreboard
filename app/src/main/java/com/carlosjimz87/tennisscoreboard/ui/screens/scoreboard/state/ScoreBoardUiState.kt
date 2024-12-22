package com.carlosjimz87.tennisscoreboard.ui.screens.scoreboard.state

import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.models.PlayerScore

data class ScoreboardUiState(
    val playersScore :Map<Player, PlayerScore> = mapOf(
        Player.PLAYER1 to PlayerScore(),
        Player.PLAYER2 to PlayerScore()
    ),
    val isTieBreak: Boolean = false,
    val matchWinner: Player? = null
)