package com.carlosjimz87.tennisscoreboard.domain.states
import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.usecases.PointAnnotation.annotateGamePoint
import com.carlosjimz87.tennisscoreboard.domain.usecases.WinnerCalculation.determineGameWinner
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class GameStateTest {
    @Test
    fun `player1 advantages after deuce`() {
        val initialGame = GameState(points = mapOf(Player.PLAYER1 to 3, Player.PLAYER2 to 3))
        val updatedGame = annotateGamePoint(initialGame, Player.PLAYER1)

        assertEquals("Player 1 should have the advantage after deuce.", 4, updatedGame.points[Player.PLAYER1])
        assertEquals("Player 2's points should remain the same after deuce.", 3, updatedGame.points[Player.PLAYER2])
        assertNull("The game should not have a winner yet.", determineGameWinner(updatedGame.points, initialGame.isTieBreak))
    }

    @Test
    fun `player1 advantages then back to deuce`() {
        val initialGame = GameState(points = mapOf(Player.PLAYER1 to 4, Player.PLAYER2 to 3))
        val updatedGame = annotateGamePoint(initialGame, Player.PLAYER2)

        assertEquals("Player 1's points should decrease to deuce level.", 4, updatedGame.points[Player.PLAYER1])
        assertEquals("Player 2's points should increase to deuce level.", 4, updatedGame.points[Player.PLAYER2])
        assertNull("The game should not have a winner yet.", determineGameWinner(updatedGame.points, initialGame.isTieBreak))
    }

    @Test
    fun `player1 advantages, then deuce again, then player2 advantages`() {
        var game = GameState(points = mapOf(Player.PLAYER1 to 4, Player.PLAYER2 to 3))
        game = annotateGamePoint(game, Player.PLAYER2) // Back to deuce
        game = annotateGamePoint(game, Player.PLAYER2) // Player 2 advantages

        assertEquals("Player 1's points should remain at deuce level.", 4, game.points[Player.PLAYER1])
        assertEquals("Player 2 should have the advantage.", 5, game.points[Player.PLAYER2])
        assertNull("The game should not have a winner yet.", determineGameWinner(game.points, game.isTieBreak))
    }
}