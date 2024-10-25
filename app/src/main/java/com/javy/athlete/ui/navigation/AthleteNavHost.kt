package com.javy.athlete.ui.navigation

import android.app.Activity
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navDeepLink
import com.javy.athlete.ui.navigation.Route.AUTHENTICATION_SCREEN
import com.javy.athlete.ui.navigation.Route.HOME_SCREEN
import com.javy.athlete.ui.navigation.Route.SPLASH_SCREEN
import com.javy.athlete.ui.screen.authentication.authenticate.AuthenticateAthleteScreen
import com.javy.athlete.ui.screen.authentication.launch.LaunchScreen
import com.javy.athlete.ui.screen.home.HomeScreen

@Composable
fun AthleteNavHost() {
    val navController = rememberNavController()
    val context = LocalContext.current

    NavHost(
        navController = navController,
        startDestination = SPLASH_SCREEN
    ) {
        slideInOutComposable(route = SPLASH_SCREEN) {
            LaunchScreen(
                navigateToHomeScreen = { navController.navigate(HOME_SCREEN) }
            )
        }

        composable(route = HOME_SCREEN) {
            HomeScreen()
        }

        slideInOutComposable(
            route = AUTHENTICATION_SCREEN,
            deepLinks = listOf(navDeepLink {
                uriPattern = "https://redirect"
            })
        ) {
            val code = (context as? Activity)?.intent?.data?.getQueryParameter("code")
            AuthenticateAthleteScreen(
                code = code,
                navigateToHomeScreen = { navController.navigate(HOME_SCREEN) })
        }
    }
}