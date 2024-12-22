package com.carlosjimz87.tennisscoreboard.ui.screens.scoreboard.viewmodel

import androidx.lifecycle.ViewModel
import com.carlosjimz87.tennisscoreboard.domain.models.Player
import com.carlosjimz87.tennisscoreboard.domain.states.MatchState
import com.carlosjimz87.tennisscoreboard.ui.screens.scoreboard.state.ScoreboardUiState
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.map

class ScoreBoardViewModel : ViewModel() {
    private val _matchState = MutableStateFlow(MatchState())
    val boardUiState: Flow<ScoreboardUiState> = _matchState.map { it.toScoreBoardUiState() }

    fun onPointScored(player: Player) {
       _matchState.value = _matchState.value.annotatePoint(player)
    }
}