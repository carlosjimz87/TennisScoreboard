package com.carlosjimz87.tennisscoreboard.ui.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.dp
import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.ui.theme.Colors

@Composable
fun ScoreboardScreen(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier
            .fillMaxWidth()
            .background(Colors.darkGreen)
            .padding(16.dp)
    ) {
        // Scoreboard Header
        Column {

            Text(
                text = "Scoreboard 🎾",
                style = MaterialTheme.typography.displayMedium,
                fontWeight = FontWeight.Bold,
                color = Colors.yellow,
                modifier = Modifier.fillMaxWidth().align(Alignment.CenterHorizontally)
            )

            Spacer(modifier = Modifier.height(8.dp))

            Column {
                listOf(Player.PLAYER1, Player.PLAYER2).forEach { player ->
                    Text(
                        text = "$player Score",
                        style = MaterialTheme.typography.headlineMedium,
                        color = Colors.white,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

        }
        Spacer(modifier = Modifier.weight(1f))

        // Buttons to annotate points
        Row(
            modifier = modifier.fillMaxWidth().height(64.dp),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Button(
                onClick = {  },
            ) {
                Text("Player 1 Scores")
            }

            Button(
                onClick = {  },
            ) {
                Text("Player 2 Scores")
            }
        }
    }
}