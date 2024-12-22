package com.carlosjimz87.tennisscoreboard

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import com.carlosjimz87.tennisscoreboard.ui.screens.scoreboard.ScoreboardScreen
import com.carlosjimz87.tennisscoreboard.ui.screens.scoreboard.viewmodel.ScoreBoardViewModel
import com.carlosjimz87.tennisscoreboard.ui.theme.TennisScoreboardTheme
import com.carlosjimz87.tennisscoreboard.utils.decorateSystemBars
import com.carlosjimz87.tennisscoreboard.utils.getBackgroundColors

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        val (color,colorId) = getBackgroundColors()
        decorateSystemBars(baseContext, window, colorId)
        setContent {
            TennisScoreboardTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    ScoreboardScreen(
                        modifier = Modifier.padding(innerPadding),
                        backgroundColor = color,
                        viewModel = ScoreBoardViewModel()
                    )
                }
            }
        }
    }
}