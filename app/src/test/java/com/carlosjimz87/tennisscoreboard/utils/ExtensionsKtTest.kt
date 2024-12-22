package com.carlosjimz87.tennisscoreboard.utils

import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.states.SetState
import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ExtensionsKtTest {

    @Test
    fun `replaceLast updates the last element in the list`() {
        val initialList = listOf(
            SetState(gamesWon = mapOf(Player.PLAYER1 to 5)),
            SetState(gamesWon = mapOf(Player.PLAYER1 to 6))
        )
        val newSet = SetState(gamesWon = mapOf(Player.PLAYER2 to 7))

        val updatedList = initialList.replaceLast(newSet)

        assertEquals("The list should still contain the same number of elements.", 2, updatedList.size)
        assertEquals("The first element should remain unchanged.", initialList[0], updatedList[0])
        assertEquals("The last element should be replaced with the new state.", newSet, updatedList[1])
    }

    @Test
    fun `replaceLast on an empty list returns an empty list`() {
        val initialList = emptyList<SetState>()
        val newSet = SetState(gamesWon = mapOf(Player.PLAYER2 to 7))

        val updatedList = initialList.replaceLast(newSet)

        assertTrue("Replacing the last element of an empty list should return an empty list.", updatedList.isEmpty())
    }

    @Test
    fun `addOne increments existing player's score`() {
        val initialMap = mapOf(Player.PLAYER1 to 3, Player.PLAYER2 to 2)

        val updatedMap = initialMap.addOne(Player.PLAYER1)

        assertEquals("Player 1's score should be incremented by 1.", 4, updatedMap[Player.PLAYER1])
        assertEquals("Player 2's score should remain unchanged.", 2, updatedMap[Player.PLAYER2])
    }

    @Test
    fun `addOne initializes player's score if not present`() {
        val initialMap = mapOf(Player.PLAYER1 to 3)

        val updatedMap = initialMap.addOne(Player.PLAYER2)

        assertEquals("Player 1's score should remain unchanged.", 3, updatedMap[Player.PLAYER1])
        assertEquals("Player 2's score should be initialized to 1.", 1, updatedMap[Player.PLAYER2])
    }

    @Test
    fun `addOne works with an empty map`() {
        val initialMap = emptyMap<Player, Int>()

        val updatedMap = initialMap.addOne(Player.PLAYER1)

        assertEquals("Player 1's score should be initialized to 1.", 1, updatedMap[Player.PLAYER1])
        assertEquals("The map should contain only one player.", 1, updatedMap.size)
    }
}