package com.carlosjimz87.tennisscoreboard.domain.usecases


import com.carlosjimz87.tennisscoreboard.domain.models.Player
import org.junit.Assert.assertTrue
import org.junit.Assert.assertFalse
import org.junit.Test

class TieBreakDeterminationTest {

    @Test
    fun `isTieBreak returns true when both players have 6 games`() {
        val gamesWon = mapOf(Player.P1 to 6, Player.P2 to 6)
        val result = TieBreakDetermination.isTieBreak(gamesWon)
        assertTrue("Tie-break should be triggered when both players have 6 games.", result)
    }

    @Test
    fun `isTieBreak returns false when player 1 has less than 6 games`() {
        val gamesWon = mapOf(Player.P1 to 5, Player.P2 to 6)
        val result = TieBreakDetermination.isTieBreak(gamesWon)
        assertFalse("Tie-break should not be triggered if player 1 has less than 6 games.", result)
    }

    @Test
    fun `isTieBreak returns false when player 2 has less than 6 games`() {
        val gamesWon = mapOf(Player.P1 to 6, Player.P2 to 5)
        val result = TieBreakDetermination.isTieBreak(gamesWon)
        assertFalse("Tie-break should not be triggered if player 2 has less than 6 games.", result)
    }

    @Test
    fun `isTieBreak returns false when both players have less than 6 games`() {
        val gamesWon = mapOf(Player.P1 to 4, Player.P2 to 5)
        val result = TieBreakDetermination.isTieBreak(gamesWon)
        assertFalse("Tie-break should not be triggered if both players have less than 6 games.", result)
    }

    @Test
    fun `isTieBreak returns false when player 1 has more than 6 games`() {
        val gamesWon = mapOf(Player.P1 to 7, Player.P2 to 6)
        val result = TieBreakDetermination.isTieBreak(gamesWon)
        assertFalse("Tie-break should not be triggered if player 1 has more than 6 games.", result)
    }

    @Test
    fun `isTieBreak returns false when player 2 has more than 6 games`() {
        val gamesWon = mapOf(Player.P1 to 6, Player.P2 to 7)
        val result = TieBreakDetermination.isTieBreak(gamesWon)
        assertFalse("Tie-break should not be triggered if player 2 has more than 6 games.", result)
    }
}