package com.carlosjimz87.tennisscoreboard.ui.composables.organisms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carlosjimz87.tennisscoreboard.domain.models.Player

@Composable
fun ScoreButtons(
    modifier: Modifier = Modifier,
    players : List<Player>,
    onScore: (Player) -> Unit
) {
    Row(
        modifier = modifier.fillMaxWidth().height(64.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        players.forEach { player ->
            Button(
                modifier = Modifier.fillMaxHeight(),
                onClick = { onScore(player) },
            ) {
                Text("${player.desc} Scores")
            }
        }
    }
}