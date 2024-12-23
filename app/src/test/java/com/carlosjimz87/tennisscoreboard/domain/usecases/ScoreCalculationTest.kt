package com.carlosjimz87.tennisscoreboard.domain.usecases


import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.models.Point
import org.junit.Assert.assertEquals
import org.junit.Test

class ScoreCalculationTest {
    @Test
    fun `calculateGameScore returns correct score for regular game`() {
        val points = mapOf(Player.P1 to 2, Player.P2 to 1)

        val result = ScoreCalculation.calculateGameScore(points, isTieBreak = false)

        assertEquals(
            "Player 1's score should be '30' with 2 points.",
            Point.THIRTY.d,
            result[Player.P1]
        )
        assertEquals(
            "Player 2's score should be '15' with 1 point.",
            Point.FIFTEEN.d,
            result[Player.P2]
        )
    }

    @Test
    fun `calculateGameScore returns correct score for deuce`() {
        val points = mapOf(Player.P1 to 3, Player.P2 to 3)

        val result = ScoreCalculation.calculateGameScore(points, isTieBreak = false)

        assertEquals(
            "Both players should have 'DEUCE' when points are tied at 3 or more.",
            Point.DEUCE.d,
            result[Player.P1]
        )
        assertEquals(
            "Both players should have 'DEUCE' when points are tied at 3 or more.",
            Point.DEUCE.d,
            result[Player.P2]
        )
    }

    @Test
    fun `calculateGameScore returns ADV for player with advantage`() {
        val points = mapOf(Player.P1 to 4, Player.P2 to 3)

        val result = ScoreCalculation.calculateGameScore(points, isTieBreak = false)

        assertEquals(
            "Player 1 should have 'ADV' with advantage over Player 2.",
            Point.ADVANTAGE.d,
            result[Player.P1]
        )
        assertEquals(
            "Player 2's score should be '40' with 3 points.",
            Point.FORTY.d,
            result[Player.P2]
        )
    }

    @Test
    fun `calculateGameScore handles tie-break points as numeric values`() {
        val points = mapOf(Player.P1 to 5, Player.P2 to 4)

        val result = ScoreCalculation.calculateGameScore(points, isTieBreak = true)

        assertEquals(
            "Player 1's score should be '5' in a tie-break game.",
            "5",
            result[Player.P1]
        )
        assertEquals(
            "Player 2's score should be '4' in a tie-break game.",
            "4",
            result[Player.P2]
        )
    }

    @Test
    fun `calculateGameScore returns correct score for player reaching 40`() {
        val points = mapOf(Player.P1 to 3, Player.P2 to 2)

        val result = ScoreCalculation.calculateGameScore(points, isTieBreak = false)

        assertEquals(
            "Player 1's score should be '40' with 3 points.",
            Point.FORTY.d,
            result[Player.P1]
        )
        assertEquals(
            "Player 2's score should be '30' with 2 points.",
            Point.THIRTY.d,
            result[Player.P2]
        )
    }
}
