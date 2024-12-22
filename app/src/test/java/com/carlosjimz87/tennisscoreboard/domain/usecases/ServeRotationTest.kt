package com.carlosjimz87.tennisscoreboard.domain.usecases

import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.states.GameState
import com.carlosjimz87.tennisscoreboard.domain.states.MatchState
import com.carlosjimz87.tennisscoreboard.domain.states.SetState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class ServeRotationTest {

    @Test
    fun `rotateAfterGame alternates serving player`() {
        assertEquals(
            "Player 2 should serve after Player 1.",
            Player.PLAYER2,
            ServeRotation.rotateAfterGame(Player.PLAYER1)
        )
        assertEquals(
            "Player 1 should serve after Player 2.",
            Player.PLAYER1,
            ServeRotation.rotateAfterGame(Player.PLAYER2)
        )
    }

    @Test
    fun `rotateInTieBreak alternates every two points`() {
        assertEquals(
            "Serve should alternate after two points in tie-break.",
            Player.PLAYER2,
            ServeRotation.rotateInTieBreak(Player.PLAYER1, 2)
        )
        assertEquals(
            "Serve should alternate after four points in tie-break.",
            Player.PLAYER1,
            ServeRotation.rotateInTieBreak(Player.PLAYER2, 4)
        )
        assertEquals(
            "Serve should not alternate on odd points in tie-break.",
            Player.PLAYER1,
            ServeRotation.rotateInTieBreak(Player.PLAYER1, 1)
        )
        assertEquals(
            "Serve should not alternate on odd points in tie-break.",
            Player.PLAYER2,
            ServeRotation.rotateInTieBreak(Player.PLAYER2, 3)
        )
    }



    @Test
    fun `no serve rotation when no winner or tie-break`() {
        val matchState = MatchState(servingPlayer = Player.PLAYER1)
        val updatedSet = SetState(
            currentGame = GameState(points = mapOf(Player.PLAYER1 to 2, Player.PLAYER2 to 1))
        )
        val setWinner = null

        val newServer = ServeRotation.determineNewServingInMatch(setWinner, matchState, updatedSet)

        assertEquals(
            "Serve should remain unchanged if no game or set is won and not in tie-break.",
            Player.PLAYER1,
            newServer
        )
    }

    @Test
    fun `no serve rotation for tied game scores`() {
        val matchState = MatchState(servingPlayer = Player.PLAYER1)
        val updatedSet = SetState(
            currentGame = GameState(points = mapOf(Player.PLAYER1 to 3, Player.PLAYER2 to 3))
        )
        val setWinner = null

        val newServer = ServeRotation.determineNewServingInMatch(setWinner, matchState, updatedSet)

        assertEquals(
            "Serve should remain unchanged for tied game scores.",
            Player.PLAYER1,
            newServer
        )
    }

    @Test
    fun `serve rotates at the start of a new set`() {
        val matchState = MatchState(servingPlayer = Player.PLAYER1)
        val updatedSet = SetState(winner = Player.PLAYER1)
        val setWinner = Player.PLAYER1

        val newServer = ServeRotation.determineNewServingInMatch(setWinner, matchState, updatedSet)

        assertEquals(
            "Serve should rotate correctly at the start of a new set.",
            Player.PLAYER2,
            newServer
        )
    }

    @Test
    fun `serve alternates correctly during tie-break`() {
        val matchState = MatchState(servingPlayer = Player.PLAYER1)
        val updatedSet = SetState(
            currentGame = GameState(
                points = mapOf(Player.PLAYER1 to 5, Player.PLAYER2 to 4),
                isTieBreak = true,
                tieBreakCounter = 3
            )
        )
        val newServerOddCounter = ServeRotation.determineNewServingInMatch(null, matchState, updatedSet)

        val updatedSetEvenCounter = updatedSet.copy(
            currentGame = updatedSet.currentGame.copy(tieBreakCounter = 4)
        )
        val newServerEvenCounter = ServeRotation.determineNewServingInMatch(null, matchState, updatedSetEvenCounter)

        assertEquals(
            "Serve should remain consistent during tie-break on odd counter.",
            Player.PLAYER1,
            newServerOddCounter
        )
        assertEquals(
            "Serve should alternate during tie-break on even counter.",
            Player.PLAYER2,
            newServerEvenCounter
        )
    }

}