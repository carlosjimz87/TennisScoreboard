package com.carlosjimz87.tennisscoreboard.domain.usecases
import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.states.SetState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNull
import org.junit.Test

class WinnerCalculationTest {

    // Game winner tests
    @Test
    fun `determineGameWinner - regular game - player 1 wins`() {
        val points = mapOf(Player.P1 to 4, Player.P2 to 2)
        val winner = WinnerCalculation.determineGameWinner(points)
        assertEquals(Player.P1, winner)
    }

    @Test
    fun `determineGameWinner - regular game - player 2 wins`() {
        val points = mapOf(Player.P1 to 3, Player.P2 to 5)
        val winner = WinnerCalculation.determineGameWinner(points)
        assertEquals(Player.P2, winner)
    }

    @Test
    fun `determineGameWinner - regular game - no winner yet`() {
        val points = mapOf(Player.P1 to 3, Player.P2 to 2)
        val winner = WinnerCalculation.determineGameWinner(points)
        assertNull(winner)
    }

    @Test
    fun `determineGameWinner - tie-break game - player 1 wins`() {
        val points = mapOf(Player.P1 to 7, Player.P2 to 5)
        val winner = WinnerCalculation.determineGameWinner(points, isTieBreak = true)
        assertEquals(Player.P1, winner)
    }

    @Test
    fun `determineGameWinner - tie-break game - player 2 wins`() {
        val points = mapOf(Player.P1 to 6, Player.P2 to 8)
        val winner = WinnerCalculation.determineGameWinner(points, isTieBreak = true)
        assertEquals(Player.P2, winner)
    }

    @Test
    fun `determineGameWinner - tie-break game - no winner yet`() {
        val points = mapOf(Player.P1 to 6, Player.P2 to 5)
        val winner = WinnerCalculation.determineGameWinner(points, isTieBreak = true)
        assertNull(winner)
    }

    // Set winner tests

    @Test
    fun `determineSetWinner - player 1 wins set with 6 games and 2-game lead`() {
        val gamesWon = mapOf(Player.P1 to 6, Player.P2 to 4)
        val winner = WinnerCalculation.determineSetWinner(gamesWon)
        assertEquals(Player.P1, winner)
    }

    @Test
    fun `determineSetWinner - player 2 wins set with 6 games and 2-game lead`() {
        val gamesWon = mapOf(Player.P1 to 5, Player.P2 to 7)
        val winner = WinnerCalculation.determineSetWinner(gamesWon)
        assertEquals(Player.P2, winner)
    }

    @Test
    fun `determineSetWinner - no winner yet in regular set`() {
        val gamesWon = mapOf(Player.P1 to 5, Player.P2 to 4)
        val winner = WinnerCalculation.determineSetWinner(gamesWon)
        assertNull(winner)
    }

    @Test
    fun `determineSetWinner - player 1 wins set in tie-break`() {
        val gamesWon = mapOf(Player.P1 to 7, Player.P2 to 6)
        val winner = WinnerCalculation.determineSetWinner(gamesWon)
        assertEquals(Player.P1, winner)
    }

    // Match winner tests (2 sets to win in a best of 3)

    @Test
    fun `determineMatchWinner - player 1 wins match by winning 2 sets in best of 3`() {
        val sets = listOf(
            SetState(gamesWon = mapOf(Player.P1 to 6, Player.P2 to 4)),
            SetState(gamesWon = mapOf(Player.P1 to 6, Player.P2 to 3)),
            SetState(gamesWon = mapOf(Player.P1 to 0, Player.P2 to 0))
        )
        val winner = WinnerCalculation.determineMatchWinner(sets, maxSets = 3)
        assertEquals(Player.P1, winner)
    }

    @Test
    fun `determineMatchWinner - player 2 wins match by winning 2 sets in best of 3`() {
        val sets = listOf(
            SetState(gamesWon = mapOf(Player.P1 to 4, Player.P2 to 6)),
            SetState(gamesWon = mapOf(Player.P1 to 3, Player.P2 to 6)),
            SetState(gamesWon = mapOf(Player.P1 to 0, Player.P2 to 0))
        )
        val winner = WinnerCalculation.determineMatchWinner(sets, maxSets = 3)
        assertEquals(Player.P2, winner)
    }

    @Test
    fun `determineMatchWinner - no winner yet in best of 3`() {
        val sets = listOf(
            SetState(gamesWon = mapOf(Player.P1 to 6, Player.P2 to 4)),
            SetState(gamesWon = mapOf(Player.P1 to 3, Player.P2 to 6)),
            SetState(gamesWon = mapOf(Player.P1 to 0, Player.P2 to 0))
        )
        val winner = WinnerCalculation.determineMatchWinner(sets, maxSets = 3)
        assertNull(winner)
    }

// Match winner tests (3 sets to win in a best of 5)

    @Test
    fun `determineMatchWinner - player 1 wins match by winning 3 sets in best of 5`() {
        val sets = listOf(
            SetState(gamesWon = mapOf(Player.P1 to 6, Player.P2 to 4)),
            SetState(gamesWon = mapOf(Player.P1 to 6, Player.P2 to 3)),
            SetState(gamesWon = mapOf(Player.P1 to 7, Player.P2 to 5)),
            SetState(gamesWon = mapOf(Player.P1 to 0, Player.P2 to 0)),
            SetState(gamesWon = mapOf(Player.P1 to 0, Player.P2 to 0))
        )
        val winner = WinnerCalculation.determineMatchWinner(sets, maxSets = 5)
        assertEquals(Player.P1, winner)
    }

    @Test
    fun `determineMatchWinner - player 2 wins match by winning 3 sets in best of 5`() {
        val sets = listOf(
            SetState(gamesWon = mapOf(Player.P1 to 4, Player.P2 to 6)),
            SetState(gamesWon = mapOf(Player.P1 to 3, Player.P2 to 6)),
            SetState(gamesWon = mapOf(Player.P1 to 5, Player.P2 to 7)),
            SetState(gamesWon = mapOf(Player.P1 to 2, Player.P2 to 6)),
            SetState(gamesWon = mapOf(Player.P1 to 1, Player.P2 to 6))
        )
        val winner = WinnerCalculation.determineMatchWinner(sets, maxSets = 5)
        assertEquals(Player.P2, winner)
    }

    @Test
    fun `determineMatchWinner - no winner yet in best of 5`() {
        val sets = listOf(
            SetState(gamesWon = mapOf(Player.P1 to 6, Player.P2 to 4)),
            SetState(gamesWon = mapOf(Player.P1 to 4, Player.P2 to 6)),
            SetState(gamesWon = mapOf(Player.P1 to 6, Player.P2 to 5)),
            SetState(gamesWon = mapOf(Player.P1 to 3, Player.P2 to 6)),
            SetState(gamesWon = mapOf(Player.P1 to 0, Player.P2 to 0))
        )
        val winner = WinnerCalculation.determineMatchWinner(sets, maxSets = 5)
        assertNull(winner)
    }
}
