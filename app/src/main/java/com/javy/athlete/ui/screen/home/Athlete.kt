package com.javy.athlete.ui.screen.home

import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.javy.athlete.ui.common.LoadingIndicator

@Composable
fun Athlete() {
    val viewModel: HomeViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    with(uiState) {
        if (isLoading) LoadingIndicator()

        if (athlete != null) {
            Column {
                Profile(athlete = athlete)

                if (athlete.stats != null) {
                    Stats(stats = athlete.stats)
                }
            }
        }
    }
}