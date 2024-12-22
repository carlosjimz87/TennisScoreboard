package com.carlosjimz87.tennisscoreboard.domain.usecases


import com.carlosjimz87.tennisscoreboard.BuildConfig
import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.states.GameState
import com.carlosjimz87.tennisscoreboard.domain.states.MatchState
import com.carlosjimz87.tennisscoreboard.domain.states.SetState
import org.junit.Assert.assertTrue
import org.junit.Assert.assertNull
import org.junit.Assert.assertEquals
import org.junit.Test

class PointAnnotationTest {

    @Test
    fun `annotateGamePoint increments points for the correct player`() {
        val initialGame = GameState(points = mapOf(Player.PLAYER1 to 0, Player.PLAYER2 to 0))

        val updatedGame = PointAnnotation.annotateGamePoint(initialGame, Player.PLAYER1)

        assertEquals("Player 1's points should increment.", 1, updatedGame.points[Player.PLAYER1])
        assertEquals("Player 2's points should remain unchanged.", 0, updatedGame.points[Player.PLAYER2])
    }

    @Test
    fun `annotateGamePoint increments tieBreakCounter during tie-break`() {
        val initialGame = GameState(points = mapOf(Player.PLAYER1 to 0, Player.PLAYER2 to 0), isTieBreak = true)

        val updatedGame = PointAnnotation.annotateGamePoint(initialGame, Player.PLAYER1)

        assertEquals("Tie-break counter should increment during a tie-break.", 1, updatedGame.tieBreakCounter)
    }

    @Test
    fun `annotateSetPoint updates current game and gameswon after a game is won`() {
        val initialSet = SetState(
            gamesWon = mapOf(Player.PLAYER1 to 0, Player.PLAYER2 to 0),
            currentGame = GameState(points = mapOf(Player.PLAYER1 to 3, Player.PLAYER2 to 0))
        )

        val updatedSet = PointAnnotation.annotateSetPoint(initialSet, Player.PLAYER1)

        assertEquals("Player 1 should win a game.", 1, updatedSet.gamesWon[Player.PLAYER1])
        assertEquals("Player 2's games won should remain unchanged.", 0, updatedSet.gamesWon[Player.PLAYER2])
        assertEquals("Current game points should reset after a game is won.", 0, updatedSet.currentGame.points[Player.PLAYER1])
        assertEquals("Current game points should reset after a game is won.", 0, updatedSet.currentGame.points[Player.PLAYER2])
    }

    @Test
    fun `annotateSetPoint starts a tie-break when both players reach 6 games`() {
        val initialSet = SetState(
            gamesWon = mapOf(Player.PLAYER1 to 6, Player.PLAYER2 to 5),
            currentGame = GameState(points = mapOf(Player.PLAYER1 to 0, Player.PLAYER2 to 3))
        )

        val updatedSet = PointAnnotation.annotateSetPoint(initialSet, Player.PLAYER2)

        assertTrue("Tie-break should start when both players reach 6 games.", updatedSet.currentGame.isTieBreak)
    }

    @Test
    fun `annotateMatchPoint updates the match state after a set is won`() {
        val initialMatch = MatchState(
            sets = listOf(
                SetState(
                    gamesWon = mapOf(Player.PLAYER1 to 5, Player.PLAYER2 to 4),
                    currentGame = GameState(points = mapOf(Player.PLAYER1 to 3, Player.PLAYER2 to 0))
                )
            )
        )

        val updatedMatch = PointAnnotation.annotateMatchPoint(initialMatch, Player.PLAYER1)

        assertEquals("A new set should be added after a set is won.", 2, updatedMatch.sets.size)
        assertEquals("Player 1 should win the first set.", Player.PLAYER1, updatedMatch.sets.first().winner)
        assertNull("The match should not have a winner yet.", updatedMatch.winner)
    }

    @Test
    fun `annotateMatchPoint determines the match winner`() {
        val initialMatch = MatchState(
            sets = listOf(
                SetState(winner = Player.PLAYER1),
                SetState(winner = Player.PLAYER1),
                SetState(
                    gamesWon = mapOf(Player.PLAYER1 to 5, Player.PLAYER2 to 4),
                    currentGame = GameState(points = mapOf(Player.PLAYER1 to 3, Player.PLAYER2 to 0))
                )
            ),
            maxSets = BuildConfig.MAX_SETS
        )

        val updatedMatch = PointAnnotation.annotateMatchPoint(initialMatch, Player.PLAYER1)

        assertEquals("Player 1 should win the match.", Player.PLAYER1, updatedMatch.winner)
    }

    @Test
    fun `after tiebreak is won, next points are added to a new set`() {
        val initialMatch = MatchState(
            sets = listOf(
                SetState(
                    gamesWon = mapOf(Player.PLAYER1 to 6, Player.PLAYER2 to 6),
                    currentGame = GameState(points = mapOf(Player.PLAYER1 to 6, Player.PLAYER2 to 5)),
                    winner = Player.PLAYER1,
                )
            )
        )

        val updatedMatch = PointAnnotation.annotateMatchPoint(initialMatch, Player.PLAYER1)

        assertEquals("The new set should have been created after tiebreak.", 2, updatedMatch.sets.size)
        assertEquals("The previous set's winner should remain unchanged.", Player.PLAYER1, updatedMatch.sets.first().winner)
        assertEquals("The new set should start with 0 games for each player.", 0, updatedMatch.sets.last().gamesWon[Player.PLAYER1])
        assertEquals("The new set should start with 0 games for each player.", 0, updatedMatch.sets.last().gamesWon[Player.PLAYER2])
        assertNull("The new set should not have a winner yet.", updatedMatch.sets.last().winner)
    }

    @Test
    fun `subsequent points after tiebreak are added to the new set`() {
        val initialMatch = MatchState(
            sets = listOf(
                SetState(
                    gamesWon = mapOf(Player.PLAYER1 to 6, Player.PLAYER2 to 6),
                    currentGame = GameState(points = mapOf(Player.PLAYER1 to 7, Player.PLAYER2 to 5)),
                    winner = Player.PLAYER1,
                ),
                SetState() // The new set initialized after tiebreak
            )
        )

        val updatedMatch = PointAnnotation.annotateMatchPoint(initialMatch, Player.PLAYER2)

        assertEquals("The number of sets should remain the same.", 2, updatedMatch.sets.size)
        assertEquals("Points for Player 2 should be updated in the new set.", 1, updatedMatch.sets.last().currentGame.points[Player.PLAYER2])
        assertEquals("Points for Player 1 should remain unchanged in the new set.", 0, updatedMatch.sets.last().currentGame.points[Player.PLAYER1])
    }
}