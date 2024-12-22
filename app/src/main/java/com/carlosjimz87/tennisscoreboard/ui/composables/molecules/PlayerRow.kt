package com.carlosjimz87.tennisscoreboard.ui.composables.molecules

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.models.Point
import com.carlosjimz87.tennisscoreboard.ui.composables.atoms.BallOrTrophy
import com.carlosjimz87.tennisscoreboard.ui.composables.atoms.CurrentGame
import com.carlosjimz87.tennisscoreboard.ui.composables.atoms.CurrentSet
import com.carlosjimz87.tennisscoreboard.ui.composables.atoms.PlayerName
import com.carlosjimz87.tennisscoreboard.ui.composables.atoms.PreviousSets


@Composable
fun PlayerRow(
    playerName: String,
    isTieBreak: Boolean,
    isServing: Boolean,
    previousSetScores: List<Int>,
    currentSetScore: Int,
    currentGameScore: String,
    matchWinner: Player? = null
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = 8.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        PlayerName(playerName)

        Spacer(modifier = Modifier.weight(1f))

        // Show a (Ball for Serving || Trophy for Match Winner)
        BallOrTrophy(matchWinner, isServing)

        // Previous Set Scores
        PreviousSets(previousSetScores)

        // Current Set Score
        CurrentSet(currentSetScore)

        // Current Game Score
        CurrentGame(currentGameScore, isTieBreak)
    }
}

@Preview(name = "Default View")
@Composable
fun PreviewPlayerRow_Default() {
    PlayerRow(
        playerName = Player.PLAYER1.name,
        isTieBreak = false,
        isServing = false,
        previousSetScores = listOf(6, 4, 7),
        currentSetScore = 5,
        currentGameScore = "30"
    )
}

@Preview(name = "Serving Player")
@Composable
fun PreviewPlayerRow_Serving() {
    PlayerRow(
        playerName = Player.PLAYER1.name,
        isTieBreak = false,
        isServing = true,
        previousSetScores = listOf(6, 4),
        currentSetScore = 3,
        currentGameScore = Point.FORTY.d
    )
}

@Preview(name = "Tie-Break Situation")
@Composable
fun PreviewPlayerRow_TieBreak() {
    PlayerRow(
        playerName = Player.PLAYER2.name,
        isTieBreak = true,
        isServing = true,
        previousSetScores = listOf(6, 7),
        currentSetScore = 6,
        currentGameScore = "6"
    )
}

@Preview(name = "No Previous Sets")
@Composable
fun PreviewPlayerRow_NoPreviousSets() {
    PlayerRow(
        playerName = Player.PLAYER2.name,
        isTieBreak = false,
        isServing = false,
        previousSetScores = emptyList(),
        currentSetScore = 2,
        currentGameScore = Point.FIFTEEN.d
    )
}

@Preview(name = "Deuce Situation")
@Composable
fun PreviewPlayerRow_Deuce() {
    PlayerRow(
        playerName = Player.PLAYER1.name,
        isTieBreak = false,
        isServing = true,
        previousSetScores = listOf(6, 4),
        currentSetScore = 5,
        currentGameScore = Point.DEUCE.d
    )
}

@Preview(name = "Advantage Player")
@Composable
fun PreviewPlayerRow_Advantage() {
    PlayerRow(
        playerName = Player.PLAYER2.name,
        isTieBreak = false,
        isServing = true,
        previousSetScores = listOf(6, 4),
        currentSetScore = 5,
        currentGameScore = Point.ADVANTAGE.d
    )
}






