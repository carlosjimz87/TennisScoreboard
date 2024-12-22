package com.carlosjimz87.tennisscoreboard.ui.composables.atoms

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.carlosjimz87.tennisscoreboard.R
import com.carlosjimz87.tennisscoreboard.domain.models.Player

@Composable
fun BallOrTrophy(
    matchWinner: Player?,
    isServing: Boolean
) {
    Box(
        modifier = Modifier.size(24.dp),
        contentAlignment = Alignment.Center
    ) {
        if (matchWinner != null) {
            IconRes(R.drawable.throphy)
        } else if (isServing) {
            IconRes(R.drawable.ball)
        } else {
            Spacer(modifier = Modifier.size(24.dp))
        }
    }
}