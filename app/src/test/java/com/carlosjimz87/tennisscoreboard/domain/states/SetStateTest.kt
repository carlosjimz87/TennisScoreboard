package com.carlosjimz87.tennisscoreboard.domain.states
import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.usecases.WinnerCalculation.determineSetWinner
import com.carlosjimz87.tennisscoreboard.utils.addOne
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Assert.assertNull
import org.junit.Test

class SetStateTest {

    @Test
    fun `default SetState initializes with no games won and no winner`() {
        val setState = SetState()

        assertEquals(
            "Player 1 should start with 0 games won.",
            0,
            setState.gamesWon[Player.P1]
        )
        assertEquals(
            "Player 2 should start with 0 games won.",
            0,
            setState.gamesWon[Player.P2]
        )
        assertNull(
            "There should be no winner for a new SetState.",
            setState.winner
        )
        assertFalse(
            "isTieBreak should be false for a new SetState.",
            setState.isTieBreak
        )
    }

    @Test
    fun `SetState updates correctly when a player wins a game`() {
        val initialSet = SetState(
            gamesWon = mapOf(Player.P1 to 3, Player.P2 to 2)
        )
        val updatedSet = initialSet.copy(
            gamesWon = initialSet.gamesWon.addOne(Player.P1)
        )

        assertEquals(
            "Player 1's games won should be incremented by 1.",
            4,
            updatedSet.gamesWon[Player.P1]
        )
        assertEquals(
            "Player 2's games won should remain the same.",
            2,
            updatedSet.gamesWon[Player.P2]
        )
    }

    @Test
    fun `SetState correctly identifies a tie-break situation`() {
        val setState = SetState(
            gamesWon = mapOf(Player.P1 to 6, Player.P2 to 6)
        )

        assertTrue(
            "SetState should indicate a tie-break when both players reach 6 games.",
            setState.isTieBreak
        )
    }

    @Test
    fun `SetState declares a winner when a player wins with 6 games and 2-game lead`() {
        val setState = SetState(
            gamesWon = mapOf(Player.P1 to 6, Player.P2 to 4)
        )

        val updatedSet = setState.copy(
            winner = determineSetWinner(setState.gamesWon)
        )

        assertEquals(
            "Player 1 should be the winner when they have a 2-game lead with 6 games.",
            Player.P1,
            updatedSet.winner
        )
    }

    @Test
    fun `SetState does not declare a winner without a 2-game lead`() {
        val setState = SetState(
            gamesWon = mapOf(Player.P1 to 6, Player.P2 to 5)
        )

        val updatedSet = setState.copy(
            winner = determineSetWinner(setState.gamesWon)
        )

        assertNull(
            "There should be no winner when a player has 6 games but less than a 2-game lead.",
            updatedSet.winner
        )
    }

    @Test
    fun `SetState resets current game after a game is won`() {
        val initialSet = SetState(
            gamesWon = mapOf(Player.P1 to 5, Player.P2 to 5),
            currentGame = GameState(points = mapOf(Player.P1 to 4, Player.P2 to 2))
        )
        val updatedSet = initialSet.copy(
            currentGame = GameState(),
            gamesWon = initialSet.gamesWon.addOne(Player.P1)
        )

        assertEquals(
            "Player 1's games won should increment after winning the current game.",
            6,
            updatedSet.gamesWon[Player.P1]
        )
        assertEquals(
            "Player 2's games won should remain the same.",
            5,
            updatedSet.gamesWon[Player.P2]
        )
        assertEquals(
            "Current game points for Player 1 should reset after winning.",
            0,
            updatedSet.currentGame.points[Player.P1]
        )
        assertEquals(
            "Current game points for Player 2 should reset after winning.",
            0,
            updatedSet.currentGame.points[Player.P2]
        )
    }
}