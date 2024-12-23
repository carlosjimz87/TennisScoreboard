package com.carlosjimz87.tennisscoreboard.domain.usecases


import com.carlosjimz87.tennisscoreboard.BuildConfig
import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.states.SetState
import com.carlosjimz87.tennisscoreboard.domain.usecases.TieBreakDetermination.isTieBreak

object WinnerCalculation {

    fun determineGameWinner(points: Map<Player, Int>, isTieBreak: Boolean = false): Player? {
        val p1Points = points[Player.P1] ?: 0
        val p2Points = points[Player.P2] ?: 0

        return when {
            isTieBreak && p1Points >= 7 && p1Points - p2Points >= 2 -> Player.P1
            isTieBreak && p2Points >= 7 && p2Points - p1Points >= 2 -> Player.P2
            !isTieBreak && p1Points >= 4 && p1Points - p2Points >= 2 -> Player.P1
            !isTieBreak && p2Points >= 4 && p2Points - p1Points >= 2 -> Player.P2
            else -> null
        }
    }

    fun determineSetWinner(gamesWon: Map<Player, Int>): Player? {
        val p1Games = gamesWon[Player.P1] ?: 0
        val p2Games = gamesWon[Player.P2] ?: 0

        return when {
            // Tie-break determines the winner
            isTieBreak(gamesWon) -> determineGameWinner(gamesWon, isTieBreak = true)
            // Regular set win
            p1Games >= 6 && p1Games - p2Games >= 2 -> Player.P1
            p2Games >= 6 && p2Games - p1Games >= 2 -> Player.P2
            p1Games >= 6 && p2Games >= 6 &&  p1Games - p2Games >= 1 -> Player.P1
            p1Games >= 6 && p2Games >= 6 &&  p2Games - p1Games >= 1 -> Player.P2
            else -> null
        }
    }

    fun determineMatchWinner(sets: List<SetState>, maxSets: Int = BuildConfig.MAX_SETS): Player? {
        val setsToWin = (maxSets / 2) + 1


        val p1SetsWon = sets.count {
            (it.gamesWon[Player.P1] ?: 0) > (it.gamesWon[Player.P2] ?: 0) &&
                    (it.gamesWon[Player.P1] ?: 0) >= 6
        }

        val p2SetsWon = sets.count {
            (it.gamesWon[Player.P2] ?: 0) > (it.gamesWon[Player.P1] ?: 0) &&
                    (it.gamesWon[Player.P2] ?: 0) >= 6
        }

        val winner = when {
            p1SetsWon >= setsToWin -> Player.P1
            p2SetsWon >= setsToWin -> Player.P2
            else -> null
        }

        println("winner: $winner setsToWin: $setsToWin of $maxSets sets: $sets")
        return winner
    }
}