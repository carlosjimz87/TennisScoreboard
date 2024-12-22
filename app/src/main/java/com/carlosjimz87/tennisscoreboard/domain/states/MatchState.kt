package com.carlosjimz87.tennisscoreboard.domain.states

import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.models.PlayerScore
import com.carlosjimz87.tennisscoreboard.domain.models.Point
import com.carlosjimz87.tennisscoreboard.ui.screens.scoreboard.state.ScoreboardUiState

data class MatchState(
    val sets: List<SetState> = listOf(SetState()),
    val servingPlayer: Player = Player.PLAYER1,
    val isTieBreak: Boolean = false,
    val winner: Player? = null,
    val maxSets: Int = 3 // This must be configurable
) {
    fun toScoreBoardUiState() : ScoreboardUiState{
        val currentSet = sets.last()

        // Calculate game scores using ScoreCalculation
        val gameScores = calculateGameScore(
            points = currentSet.currentGame.points,
            isTieBreak = currentSet.currentGame.isTieBreak
        )

        // Generate PlayerScore for each player
        val player1Score = PlayerScore(
            previousSetScores = sets.dropLast(1).map { it.gamesWon[Player.PLAYER1] ?: 0 },
            currentSetScore = currentSet.gamesWon[Player.PLAYER1] ?: 0,
            currentGameScore = gameScores[Player.PLAYER1] ?: "0",
            isServing = servingPlayer == Player.PLAYER1
        )

        val player2Score = PlayerScore(
            previousSetScores = sets.dropLast(1).map { it.gamesWon[Player.PLAYER2] ?: 0 },
            currentSetScore = currentSet.gamesWon[Player.PLAYER2] ?: 0,
            currentGameScore = gameScores[Player.PLAYER2] ?: "0",
            isServing = servingPlayer == Player.PLAYER2
        )

        return ScoreboardUiState(
            playersScore = mapOf(
                Player.PLAYER1 to player1Score,
                Player.PLAYER2 to player2Score
            ),
            matchWinner = winner
        )
    }


    private fun calculateGameScore(points: Map<Player, Int>, isTieBreak: Boolean): Map<Player, String> {
        val scoreMap = mutableMapOf<Player, String>()
        val (p1Points, p2Points) = points[Player.PLAYER1]!! to points[Player.PLAYER2]!!

        // If tie-break -> return points as numbers
        if (isTieBreak) {
            return points.mapValues { it.value.toString() }
        }

        // If regular game -> calculate scores using Point enum
        when {
            p1Points >= 3 && p2Points >= 3 -> {
                scoreMap[Player.PLAYER1] = when {
                    p1Points > p2Points -> Point.ADVANTAGE.d
                    p1Points == p2Points -> Point.DEUCE.d
                    else -> Point.FORTY.d
                }
                scoreMap[Player.PLAYER2] = when {
                    p2Points > p1Points -> Point.ADVANTAGE.d
                    p1Points == p2Points -> Point.DEUCE.d
                    else -> Point.FORTY.d
                }
            }
            else -> {
                scoreMap[Player.PLAYER1] = Point.fromValue(p1Points).d
                scoreMap[Player.PLAYER2] = Point.fromValue(p2Points).d
            }
        }

        return scoreMap
    }

    fun annotatePoint(player: Player): MatchState {
        val currentSet = sets.last()
        val updatedSet = currentSet.annotatePoint(player)

        val updatedSets = sets.dropLast(1) + updatedSet

        val newMatchState = copy(
            sets = updatedSets,
            servingPlayer =
            if (servingPlayer == Player.PLAYER1) Player.PLAYER2 else Player.PLAYER1,
        )
        println("MatchState.annotatePoint: ${newMatchState.sets.last().currentGame.points}")
        return newMatchState
    }
}