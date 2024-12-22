package com.carlosjimz87.tennisscoreboard.domain.usecases

import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.states.MatchState
import com.carlosjimz87.tennisscoreboard.domain.states.SetState

object ServeRotation {

    fun rotateAfterGame(current: Player): Player = rotatePlayer(current)

    fun rotateInTieBreak(current: Player, counter: Int): Player =
        if (counter % 2 == 0) rotatePlayer(current) else current

    private fun rotatePlayer(current: Player): Player =
        if (current == Player.PLAYER1) Player.PLAYER2 else Player.PLAYER1

    fun determineNewServingInMatch(
        setWinner: Player?,
        matchState: MatchState,
        updatedSet: SetState
    ): Player {
        return when {
            // Rotate after a set is won
            setWinner != null -> rotateAfterGame(matchState.servingPlayer)

            // Rotate every two points during a tiebreak
            updatedSet.currentGame.isTieBreak -> rotateInTieBreak(
                matchState.servingPlayer,
                updatedSet.currentGame.tieBreakCounter
            )
            // No rotation otherwise
            else -> matchState.servingPlayer
        }
    }
}