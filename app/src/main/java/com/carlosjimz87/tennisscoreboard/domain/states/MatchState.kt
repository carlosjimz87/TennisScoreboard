package com.carlosjimz87.tennisscoreboard.domain.states

import com.carlosjimz87.tennisscoreboard.BuildConfig
import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.models.PlayerScore
import com.carlosjimz87.tennisscoreboard.domain.usecases.PointAnnotation.annotateMatchPoint
import com.carlosjimz87.tennisscoreboard.domain.usecases.ScoreCalculation.calculateGameScore
import com.carlosjimz87.tennisscoreboard.ui.screens.scoreboard.state.ScoreboardUiState

data class MatchState(
    val sets: List<SetState> = listOf(SetState()),
    val servingPlayer: Player = Player.PLAYER1,
    val isTieBreak: Boolean = false,
    val winner: Player? = null,
    val maxSets: Int = BuildConfig.MAX_SETS
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
            matchWinner = winner,
            isTieBreak = isTieBreak
        )
    }


    fun annotatePoint(player: Player): MatchState {
        return annotateMatchPoint(this, player)
    }
}