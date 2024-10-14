package com.javy.athlete.ui.screen.authentication.launch

import android.content.Intent
import android.net.Uri
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.javy.athlete.BuildConfig
import com.javy.athlete.ui.common.LoadingIndicator

@Composable
fun LaunchScreen(navigateToHomeScreen: () -> Unit) {
    val viewModel: LaunchViewModel = hiltViewModel()
    val uiState by viewModel.uiState.collectAsState()

    val context = LocalContext.current

    LaunchedEffect(true) {
        viewModel.fetchAthlete()
    }

    with(uiState) {
        if (isLoading) LoadingIndicator()

        when (isAuthenticated) {
            true -> navigateToHomeScreen()
            false -> {
                val intentUri = Uri.parse("https://www.strava.com/oauth/mobile/authorize")
                    .buildUpon()
                    .appendQueryParameter("client_id", BuildConfig.CLIENT_ID)
                    .appendQueryParameter("redirect_uri", "https://redirect")
                    .appendQueryParameter("response_type", "code")
                    .appendQueryParameter("approval_prompt", "auto")
                    .appendQueryParameter("scope", "activity:write,read")
                    .build()

                val intent = Intent(Intent.ACTION_VIEW, intentUri)
                context.startActivity(intent)
            }

            null -> {}
        }
    }

}