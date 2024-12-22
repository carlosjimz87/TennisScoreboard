package com.carlosjimz87.tennisscoreboard.domain.states

import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.usecases.TieBreakDetermination.isTieBreak

data class SetState(
    val gamesWon: Map<Player, Int> = mapOf(Player.PLAYER1 to 0, Player.PLAYER2 to 0),
    val currentGame: GameState = GameState(),
    val winner: Player? = null,
    val gameWinner: Player? = null
) {
    val isTieBreak: Boolean
        get() = isTieBreak(gamesWon)
}