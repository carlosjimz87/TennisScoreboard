package com.carlosjimz87.tennisscoreboard.domain.usecases
import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.states.GameState
import com.carlosjimz87.tennisscoreboard.domain.states.MatchState
import com.carlosjimz87.tennisscoreboard.domain.states.SetState
import com.carlosjimz87.tennisscoreboard.domain.usecases.ServeRotation.determineNewServingInMatch
import com.carlosjimz87.tennisscoreboard.domain.usecases.TieBreakDetermination.isTieBreak
import com.carlosjimz87.tennisscoreboard.domain.usecases.WinnerCalculation.determineGameWinner
import com.carlosjimz87.tennisscoreboard.domain.usecases.WinnerCalculation.determineMatchWinner
import com.carlosjimz87.tennisscoreboard.domain.usecases.WinnerCalculation.determineSetWinner
import com.carlosjimz87.tennisscoreboard.utils.addOne
import com.carlosjimz87.tennisscoreboard.utils.replaceLast

object PointAnnotation {

    fun annotateGamePoint(gameState: GameState, player: Player): GameState {
        val updatedPoints = gameState.points.addOne(player)

        val newTieBreakCounter = if (gameState.isTieBreak) gameState.tieBreakCounter + 1 else gameState.tieBreakCounter

        return gameState.copy(
            points = updatedPoints,
            tieBreakCounter = newTieBreakCounter
        )
    }

    fun annotateSetPoint(setState: SetState, player: Player): SetState {
        val updatedGame = annotateGamePoint(setState.currentGame, player)

        val gameWinner = determineGameWinner(updatedGame.points, setState.currentGame.isTieBreak)

        return if (gameWinner != null) {
            val updatedGames = setState.gamesWon.addOne(gameWinner)

            val newIsTieBreak = isTieBreak(updatedGames)

            val setWinner = determineSetWinner(updatedGames) // Ensure this is always accurate

            setState.copy(
                gamesWon = updatedGames,
                currentGame = GameState(isTieBreak = newIsTieBreak),
                winner = setWinner,
                gameWinner = gameWinner
            )
        } else {
            setState.copy(currentGame = updatedGame, gameWinner = null)
        }
    }

    fun annotateMatchPoint(matchState: MatchState, player: Player): MatchState {
        val currentSet = matchState.sets.last()
        val updatedSet = annotateSetPoint(currentSet, player)

        val setWinner = updatedSet.winner

        // Determine the new serving player
        val newServingPlayer = determineNewServingInMatch(updatedSet.gameWinner, matchState, updatedSet)

        return if (setWinner != null) {
            // Update the finished set
            val updatedSets = matchState.sets.replaceLast(updatedSet)

            // Determine match winner
            val matchWinner = determineMatchWinner(updatedSets, matchState.maxSets)

            // Add a new set only if the match is not over
            val newSets = if (matchWinner == null) {
                updatedSets + SetState()
            } else {
                updatedSets
            }

            matchState.copy(
                sets = newSets,
                winner = matchWinner,
                servingPlayer = newServingPlayer,
                isTieBreak = currentSet.isTieBreak
            )
        } else {
            // No set winner, update the current set
            matchState.copy(
                sets = matchState.sets.replaceLast(updatedSet),
                servingPlayer = newServingPlayer,
                isTieBreak = currentSet.isTieBreak
            )
        }
    }
}