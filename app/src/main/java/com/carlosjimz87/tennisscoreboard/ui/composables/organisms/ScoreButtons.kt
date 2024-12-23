package com.carlosjimz87.tennisscoreboard.ui.composables.organisms

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import com.carlosjimz87.tennisscoreboard.R
import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.ui.theme.Colors
import com.carlosjimz87.tennisscoreboard.utils.playSound

@Composable
fun ScoreButtons(
    modifier: Modifier = Modifier,
    players : List<Player>,
    matchWinner: Player?,
    onScore: (Player) -> Unit
) {
    val context = LocalContext.current
    Row(
        modifier = modifier.fillMaxWidth().height(64.dp),
        horizontalArrangement = Arrangement.SpaceAround
    ) {
        if(matchWinner == null){

            players.forEach { player ->
                Button(
                    modifier = Modifier.fillMaxHeight(),
                    onClick = {
                        playSound(context, R.raw.score, durationInMillis = 2000)
                        onScore(player)
                    },
                ) {

                    Text(
                        text = "${player.name} Scores",
                        style = MaterialTheme.typography.labelMedium.copy(
                            color = Colors.white,
                        ),
                    )
                }
            }
        }
    }
}