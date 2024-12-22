package com.carlosjimz87.tennisscoreboard.domain.states
import com.carlosjimz87.tennisscoreboard.domain.models.Player
import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class MatchStateTest {

    @Test
    fun `player wins a single game in a regular set`() {
        var match = MatchState()

        repeat(4) { match = match.annotatePoint(Player.PLAYER1) }
        assertEquals("Player 1 should win one game in the current set.", 1, match.sets.last().gamesWon[Player.PLAYER1])
        assertEquals("Player 2 should have no games won.", 0, match.sets.last().gamesWon[Player.PLAYER2])
    }

    @Test
    fun `player wins a set with 6 games and 2-game lead`() {
        var match = MatchState()

        repeat(24) { match = match.annotatePoint(Player.PLAYER1) }
        assertEquals("Player 1 should win the current set.", 6, match.sets.first().gamesWon[Player.PLAYER1])
        assertEquals("Player 1 should be the winner of the set.", Player.PLAYER1, match.sets.first().winner)
    }

    @Test
    fun `set enters tiebreak at 6-6`() {
        var match = MatchState()

        // Simulate games to reach tie-break
        match = simulateReachTieBreak(match)

        assertTrue("The current set should enter a tie-break at 6-6.", match.sets.last().isTieBreak)
    }

    @Test
    fun `player wins set with tiebreak`() {
        var match = MatchState()

        // Simulate games to reach tie-break
        match = simulateReachTieBreak(match)

        // Simulate tie-break points
        repeat(7) { match = match.annotatePoint(Player.PLAYER1) }

        assertEquals("Player 1 should win the tie-break set.", Player.PLAYER1, match.sets.first().winner)
        assertEquals("Player 1 should have won one set.", 1, match.sets.count { it.winner == Player.PLAYER1 })
    }

    @Test
    fun `player wins match with 2 sets in a 3-set match`() {
        var match = MatchState()

        // Player 1 wins first set
        repeat(24) { match = match.annotatePoint(Player.PLAYER1) }
        assertEquals("Player 1 should win the first set.", Player.PLAYER1, match.sets[0].winner)

        // Player 1 wins second set
        repeat(24) { match = match.annotatePoint(Player.PLAYER1) }
        assertEquals("Player 1 should win the second set.", Player.PLAYER1, match.sets[1].winner)

        // Match winner
        assertEquals("Player 1 should win the match.", Player.PLAYER1, match.winner)
    }

    @Test
    fun `serve alternates after each game in regular play`() {
        var match = MatchState()
        val initialServer = match.servingPlayer

        repeat(4) { match = match.annotatePoint(Player.PLAYER1) }
        assertNotEquals("Serving player should alternate after each game.", initialServer, match.servingPlayer)
    }

    @Test
    fun `serve alternates every two points in tiebreak`() {
        var match = MatchState()

        // Simulate games to reach tie-break
        match = simulateReachTieBreak(match)

        assertTrue("The current set should enter a tie-break.", match.sets.last().isTieBreak)
        val initialServer = match.servingPlayer

        // Simulate tie-break points
        repeat(2) { match = match.annotatePoint(Player.PLAYER1) }
        assertNotEquals("Serve should alternate every two points in tie-break.", initialServer, match.servingPlayer)
    }

    @Test
    fun `match score reflects set and game progress`() {
        var match = MatchState()

        // Player 1 wins first game
        repeat(4) { match = match.annotatePoint(Player.PLAYER1) }
        assertEquals("Player 1 should win one game.", 1, match.sets.last().gamesWon[Player.PLAYER1])
        assertEquals("Player 2 should have no games won.", 0, match.sets.last().gamesWon[Player.PLAYER2])

        // Player 2 wins second game
        repeat(4) { match = match.annotatePoint(Player.PLAYER2) }
        assertEquals("Both players should have one game each.", 1, match.sets.last().gamesWon[Player.PLAYER2])
    }

    @Test
    fun `new set starts after a set is won`() {
        var match = MatchState()

        // Player 1 wins first set
        repeat(24) { match = match.annotatePoint(Player.PLAYER1) }
        assertEquals("Player 1 should win the first set.", Player.PLAYER1, match.sets.first().winner)

        // Check that a new set is started
        assertEquals("A new set should start after the first set is won.", 2, match.sets.size)
        assertEquals("The new set should have 0 games for Player 1.", 0, match.sets.last().gamesWon[Player.PLAYER1])
        assertEquals("The new set should have 0 games for Player 2.", 0, match.sets.last().gamesWon[Player.PLAYER2])
    }

    @Test
    fun `tiebreak score updates correctly`() {
        var match = MatchState()

        // Simulate games to reach tie-break
        match = simulateReachTieBreak(match)

        // Simulate tie-break points
        repeat(7) { match = match.annotatePoint(Player.PLAYER1) }
        assertEquals("Player 1 should win the tie-break set.", Player.PLAYER1, match.sets.first().winner)
    }

    private fun simulateReachTieBreak(match: MatchState): MatchState {
        var newMatch = match
        repeat(23) { newMatch = newMatch.annotatePoint(Player.PLAYER1) }
        repeat(23) { newMatch = newMatch.annotatePoint(Player.PLAYER2) }
        repeat(4) { newMatch = newMatch.annotatePoint(Player.PLAYER1) }
        repeat(4) { newMatch = newMatch.annotatePoint(Player.PLAYER2) }
        return newMatch
    }
}