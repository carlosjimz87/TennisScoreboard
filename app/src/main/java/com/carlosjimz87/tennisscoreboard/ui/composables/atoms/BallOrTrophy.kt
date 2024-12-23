package com.carlosjimz87.tennisscoreboard.ui.composables.atoms

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carlosjimz87.tennisscoreboard.R
import com.carlosjimz87.tennisscoreboard.ui.theme.Colors

@Composable
fun BallOrTrophy(
    isWinner: Boolean?,
    isServing: Boolean
) {
    Box(
        contentAlignment = Alignment.Center
    ) {
        when {
            isWinner == true -> IconRes(R.drawable.throphy)
            isServing -> IconRes(R.drawable.ball)
            else -> Spacer(modifier = Modifier.size(24.dp))
        }
    }
    Spacer(modifier = Modifier.width(8.dp))
}