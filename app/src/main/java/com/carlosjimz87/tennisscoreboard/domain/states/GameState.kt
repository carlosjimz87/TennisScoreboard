package com.carlosjimz87.tennisscoreboard.domain.states

import com.carlosjimz87.tennisscoreboard.domain.models.Player

data class GameState(
    val points: Map<Player, Int> = mapOf(Player.PLAYER1 to 0, Player.PLAYER2 to 0),
    val isTieBreak: Boolean = false,
    val tieBreakCounter: Int = 0,
)