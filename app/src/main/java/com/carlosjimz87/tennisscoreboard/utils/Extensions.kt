package com.carlosjimz87.tennisscoreboard.utils

import android.content.Context
import android.media.MediaPlayer
import android.view.Window
import androidx.core.content.ContextCompat.getColor
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsControllerCompat
import com.carlosjimz87.tennisscoreboard.BuildConfig
import com.carlosjimz87.tennisscoreboard.R
import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.states.SetState
import com.carlosjimz87.tennisscoreboard.ui.theme.Colors
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

fun List<SetState>.replaceLast(newState: SetState): List<SetState> {
    if (this.isEmpty()) return this

    return this.toMutableList().apply {
        this[this.size - 1] = newState
    }.toList()
}

fun Map<Player, Int>.addOne(player: Player) = this.toMutableMap().apply {
    this[player] = (this[player] ?: 0) + 1
}

fun decorateSystemBars(context: Context, window: Window, color: Int = R.color.green) {
    // Set the system bar colors
    WindowCompat.setDecorFitsSystemWindows(window, false) // Let Compose handle insets
    window.statusBarColor = getColor(context, color) // Status bar color
    window.navigationBarColor = getColor(context, color) // Navigation bar color
    WindowInsetsControllerCompat(window, window.decorView).isAppearanceLightStatusBars = false
}

@Suppress("ConstantConditionIf")
fun isGrandSlam(): Boolean {
    return BuildConfig.MAX_SETS > 3
}

fun getBackgroundColors() =
    if (isGrandSlam()) Pair(Colors.green, R.color.green) else Pair(
        Colors.darkGreen,
        R.color.dark_green
    )


fun playSound(context: Context, resource: Int, durationInMillis: Int? = null) {
    val mediaPlayer = MediaPlayer.create(context, resource)

    mediaPlayer.setOnCompletionListener {
        mediaPlayer.release() // Release resources when playback completes
    }

    mediaPlayer.setOnPreparedListener {
        mediaPlayer.start() // Start playback

        // If a duration is specified, stop playback early
        durationInMillis?.let { duration ->
            CoroutineScope(Dispatchers.IO).launch {
                delay(duration.toLong())
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                    mediaPlayer.release()
                }
            }
        }
    }
}