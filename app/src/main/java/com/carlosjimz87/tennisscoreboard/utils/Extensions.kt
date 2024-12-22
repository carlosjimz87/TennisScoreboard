package com.carlosjimz87.tennisscoreboard.utils

import android.content.Context
import android.media.MediaPlayer
import com.carlosjimz87.tennisscoreboard.R
import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.states.SetState
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

fun playSound(context: Context, resource: Int, durationInMillis: Int? = null) {
    val mediaPlayer = MediaPlayer.create(context, resource)

    mediaPlayer.setOnCompletionListener {
        mediaPlayer.release() // Release resources when playback completes
    }

    mediaPlayer.setOnPreparedListener {
        mediaPlayer.start() // Start playback

        // If a duration is specified, stop playback early
        durationInMillis?.let { duration ->
            CoroutineScope(Dispatchers.Main).launch {
                delay(duration.toLong())
                if (mediaPlayer.isPlaying) {
                    mediaPlayer.stop()
                    mediaPlayer.release()
                }
            }
        }
    }
}