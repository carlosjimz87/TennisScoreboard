package com.carlosjimz87.tennisscoreboard.domain.models

data class PlayerScore(
    val previousSetScores: List<Int> = emptyList(),
    val currentSetScore: Int = 0,
    val currentGameScore: String = "0",
    val isServing: Boolean = false
)