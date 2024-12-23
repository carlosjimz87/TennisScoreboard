package com.carlosjimz87.tennisscoreboard.ui.composables.atoms
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutVertically
import androidx.compose.animation.togetherWith
import androidx.compose.animation.with
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.defaultMinSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.carlosjimz87.tennisscoreboard.ui.theme.Colors

@Composable
fun CurrentGame(
    currentGameScore: String,
    isTieBreak: Boolean,
    animated: Boolean = true,
) {
    //val backgroundColor = if (isTieBreak) Colors.red else backgroundDefault
    val textColor = if (isTieBreak) Colors.white else Colors.yellow

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .defaultMinSize(minWidth = 60.dp),
        contentAlignment = Alignment.Center
    ) {
        if(animated){
            AnimatedContent(
                targetState = currentGameScore,
                transitionSpec = {
                    (slideInVertically { height -> height } + fadeIn()).togetherWith(
                        slideOutVertically { height -> -height } + fadeOut())
                }, label = "Score animation"
            ) { score ->
                Text(
                    text = score,
                    style = MaterialTheme.typography.labelLarge,
                    textAlign = TextAlign.Center,
                    color = textColor,
                )
            }
        } else {
            Text(
                text = currentGameScore,
                style = MaterialTheme.typography.labelLarge,
                textAlign = TextAlign.Center,
                color = textColor,
            )
        }
    }
}