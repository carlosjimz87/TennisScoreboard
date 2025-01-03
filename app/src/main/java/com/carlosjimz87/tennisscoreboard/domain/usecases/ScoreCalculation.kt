package com.carlosjimz87.tennisscoreboard.domain.usecases

import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.models.Point

object ScoreCalculation {

    fun calculateGameScore(points: Map<Player, Int>, isTieBreak: Boolean): Map<Player, String> {
        val scoreMap = mutableMapOf<Player, String>()
        val (p1Points, p2Points) = points[Player.P1]!! to points[Player.P2]!!

        // If tie-break -> return points as numbers
        if (isTieBreak) {
            return points.mapValues { it.value.toString() }
        }

        // If regular game -> calculate scores using Point enum
        when {
            p1Points >= 3 && p2Points >= 3 -> {
                scoreMap[Player.P1] = when {
                    p1Points > p2Points -> Point.ADVANTAGE.d
                    p1Points == p2Points -> Point.DEUCE.d
                    else -> Point.FORTY.d
                }
                scoreMap[Player.P2] = when {
                    p2Points > p1Points -> Point.ADVANTAGE.d
                    p1Points == p2Points -> Point.DEUCE.d
                    else -> Point.FORTY.d
                }
            }
            else -> {
                scoreMap[Player.P1] = Point.fromValue(p1Points).d
                scoreMap[Player.P2] = Point.fromValue(p2Points).d
            }
        }

        return scoreMap
    }
}