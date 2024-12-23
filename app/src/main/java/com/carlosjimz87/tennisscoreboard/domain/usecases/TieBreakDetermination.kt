package com.carlosjimz87.tennisscoreboard.domain.usecases

import com.carlosjimz87.tennisscoreboard.domain.models.Player

object TieBreakDetermination {

    fun isTieBreak(gamesWon: Map<Player, Int>): Boolean {
        val p1Games = gamesWon[Player.P1] ?: 0
        val p2Games = gamesWon[Player.P2] ?: 0
        return p1Games == 6 && p2Games == 6
    }
}