package com.carlosjimz87.tennisscoreboard.domain.usecases


import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.states.SetState
import com.carlosjimz87.tennisscoreboard.domain.usecases.TieBreakDetermination.isTieBreak

object WinnerCalculation {

    fun determineGameWinner(points: Map<Player, Int>, isTieBreak: Boolean = false): Player? {
        val player1Points = points[Player.PLAYER1] ?: 0
        val player2Points = points[Player.PLAYER2] ?: 0

        return when {
            isTieBreak && player1Points >= 7 && player1Points - player2Points >= 2 -> Player.PLAYER1
            isTieBreak && player2Points >= 7 && player2Points - player1Points >= 2 -> Player.PLAYER2
            !isTieBreak && player1Points >= 4 && player1Points - player2Points >= 2 -> Player.PLAYER1
            !isTieBreak && player2Points >= 4 && player2Points - player1Points >= 2 -> Player.PLAYER2
            else -> null
        }
    }

    fun determineSetWinner(gamesWon: Map<Player, Int>): Player? {
        val player1Games = gamesWon[Player.PLAYER1] ?: 0
        val player2Games = gamesWon[Player.PLAYER2] ?: 0

        return when {
            // Tie-break determines the winner
            isTieBreak(gamesWon) -> determineGameWinner(gamesWon, isTieBreak = true)
            // Regular set win
            player1Games >= 6 && player1Games - player2Games >= 2 -> Player.PLAYER1
            player2Games >= 6 && player2Games - player1Games >= 2 -> Player.PLAYER2
            player1Games >= 6 && player2Games >= 6 &&  player1Games - player2Games >= 1 -> Player.PLAYER1
            player1Games >= 6 && player2Games >= 6 &&  player2Games - player1Games >= 1 -> Player.PLAYER2
            else -> null
        }
    }

    fun determineMatchWinner(sets: List<SetState>, maxSets: Int): Player? {
        val setsToWin = (maxSets / 2) + 1

        val player1SetsWon = sets.count { it.winner == Player.PLAYER1 }
        val player2SetsWon = sets.count { it.winner == Player.PLAYER2 }

        return when {
            player1SetsWon >= setsToWin -> Player.PLAYER1
            player2SetsWon >= setsToWin -> Player.PLAYER2
            else -> null
        }
    }
}