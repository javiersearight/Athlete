package com.javy.athlete.ui.screen.authentication.authenticate

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.hilt.navigation.compose.hiltViewModel
import com.javy.athlete.ui.common.LoadingIndicator

@Composable
fun AuthenticateAthleteScreen(code: String?, navigateToHomeScreen: () -> Unit) {

    val viewModel: AuthenticationViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        code?.let {
            viewModel.fetchToken(it) { navigateToHomeScreen() }
        }
    }

    with(uiState) {
        Log.d("AuthenticateAthleteScreen", "AuthenticateAthleteScreen")
        if (isLoading) LoadingIndicator()
    }
}