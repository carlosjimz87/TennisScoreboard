package com.carlosjimz87.tennisscoreboard.domain.usecases

import com.carlosjimz87.tennisscoreboard.domain.models.Player

object TieBreakDetermination {

    fun isTieBreak(gamesWon: Map<Player, Int>): Boolean {
        val player1Games = gamesWon[Player.PLAYER1] ?: 0
        val player2Games = gamesWon[Player.PLAYER2] ?: 0
        return player1Games == 6 && player2Games == 6
    }
}