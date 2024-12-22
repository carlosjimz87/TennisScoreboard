package com.carlosjimz87.tennisscoreboard.ui.composables.atoms

import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.carlosjimz87.tennisscoreboard.ui.theme.Colors


@Composable
fun IconRes(res : Int, color: Color = Colors.yellow) {
    Icon(
        painter = painterResource(id = res),
        contentDescription = null,
        modifier = Modifier.size(20.dp),
        tint = color
    )
}