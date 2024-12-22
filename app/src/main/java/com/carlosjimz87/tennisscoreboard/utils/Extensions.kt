package com.carlosjimz87.tennisscoreboard.utils

import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.states.SetState

fun List<SetState>.replaceLast(newState: SetState): List<SetState> {
    if (this.isEmpty()) return this

    return this.toMutableList().apply {
        this[this.size - 1] = newState
    }.toList()
}

fun Map<Player, Int>.addOne(player: Player) = this.toMutableMap().apply {
    this[player] = (this[player] ?: 0) + 1
}