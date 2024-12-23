package com.carlosjimz87.tennisscoreboard.ui.composables.molecules

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
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
import com.carlosjimz87.tennisscoreboard.ui.theme.Colors.darkGreen
import com.carlosjimz87.tennisscoreboard.ui.theme.Colors.green

@Composable
fun PlayerRow(
    player: Player,
    isTieBreak: Boolean,
    isServing: Boolean,
    previousSetScores: List<Int>,
    currentSetScore: Int,
    currentGameScore: String,
    matchWinner: Player? = null,
    animated : Boolean = true
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(darkGreen)
            .padding(vertical = 8.dp, horizontal = 16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        // Player Name
        PlayerName(player.desc)

        // Spacer to align elements
        Spacer(modifier = Modifier.weight(1f))

        // Ball or Trophy icon
        BallOrTrophy(matchWinner == player, isServing)

        // Previous Set Scores
        PreviousSets(previousSetScores)

        // Current Set Score
        CurrentSet(currentSetScore)

        // Ensure consistent width for Current Game Score
        Box(
            modifier = Modifier
                .width(60.dp) // Enforce a fixed width for 3-character scores
                .align(Alignment.CenterVertically)
                .background(green),
            contentAlignment = Alignment.Center
        ) {
            CurrentGame(currentGameScore, isTieBreak, animated)
        }
    }
}

@Preview(name = "Default View")
@Composable
fun PreviewPlayerRow_Default() {
    PlayerRow(
        player = Player.P1,
        isTieBreak = false,
        isServing = false,
        previousSetScores = listOf(6, 4, 7),
        currentSetScore = 5,
        currentGameScore = "30",
        animated = false
    )
}

@Preview(name = "Serving Player")
@Composable
fun PreviewPlayerRow_Serving() {
    PlayerRow(
        player = Player.P1,
        isTieBreak = false,
        isServing = true,
        previousSetScores = listOf(6, 4),
        currentSetScore = 3,
        currentGameScore = Point.FORTY.d,
        animated = false
    )
}

@Preview(name = "Tie-Break Situation")
@Composable
fun PreviewPlayerRow_TieBreak() {
    PlayerRow(
        player = Player.P2,
        isTieBreak = true,
        isServing = true,
        previousSetScores = listOf(6, 7),
        currentSetScore = 6,
        currentGameScore = "6",
        animated = false
    )
}

@Preview(name = "No Previous Sets")
@Composable
fun PreviewPlayerRow_NoPreviousSets() {
    PlayerRow(
        player = Player.P2,
        isTieBreak = false,
        isServing = false,
        previousSetScores = emptyList(),
        currentSetScore = 2,
        currentGameScore = Point.FIFTEEN.d,
        animated = false
    )
}

@Preview(name = "Deuce Situation")
@Composable
fun PreviewPlayerRow_Deuce() {
    PlayerRow(
        player = Player.P1,
        isTieBreak = false,
        isServing = true,
        previousSetScores = listOf(6, 4),
        currentSetScore = 5,
        currentGameScore = Point.DEUCE.d,
        animated = false
    )
}

@Preview(name = "Advantage Player")
@Composable
fun PreviewPlayerRow_Advantage() {
    PlayerRow(
        player = Player.P2,
        isTieBreak = false,
        isServing = true,
        previousSetScores = listOf(6, 4),
        currentSetScore = 5,
        currentGameScore = Point.ADVANTAGE.d,
        animated = false
    )
}

@Preview(name = "Winner Player")
@Composable
fun PreviewPlayerRow_Winner() {
    PlayerRow(
        player = Player.P2,
        isTieBreak = false,
        isServing = true,
        previousSetScores = listOf(7, 6),
        currentSetScore = 6,
        currentGameScore = Point.LOVE.d,
        matchWinner = Player.P2,
        animated = false
    )
}





