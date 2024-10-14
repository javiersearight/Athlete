package com.javy.athlete.ui.navigation

import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Left
import androidx.compose.animation.AnimatedContentTransitionScope.SlideDirection.Companion.Right
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDeepLink
import androidx.navigation.NavGraphBuilder
import androidx.navigation.compose.composable

const val defaultDuration = 400

fun NavGraphBuilder.slideInOutComposable(
    route: String,
    arguments: List<NamedNavArgument> = emptyList(),
    deepLinks: List<NavDeepLink> = listOf(),
    content: @Composable AnimatedContentScope.(NavBackStackEntry) -> Unit
) = this.composable(
    route = route,
    arguments = arguments,
    deepLinks = deepLinks,
    enterTransition = {
        slideIntoContainer(
            towards = Left,
            animationSpec = tween(defaultDuration)
        ) + fadeIn()
    },
    exitTransition = {
        slideOutOfContainer(
            towards = Left,
            animationSpec = tween(defaultDuration)
        ) + fadeOut()
    },
    popEnterTransition = {
        slideIntoContainer(
            towards = Right,
            animationSpec = tween(defaultDuration)
        ) + fadeIn()
    },
    popExitTransition = {
        slideOutOfContainer(
            towards = Right,
            animationSpec = tween(defaultDuration)
        ) + fadeOut()
    }
) {
    content(this, it)
}