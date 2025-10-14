package com.shourov.apps.pacedream.navigation

import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.EaseIn
import androidx.compose.animation.core.EaseOut
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import com.shourov.apps.pacedream.signin.navigation.userOnBoardingScreen
import com.shourov.apps.pacedream.ui.PaceDreamAppState

@Composable
fun PaceDreamNavHost(
    modifier: Modifier = Modifier,
    startDestination: String,
    appState: PaceDreamAppState,
) {
    val navController = appState.navController

    NavHost(
        navController = navController,
        startDestination = startDestination,
        modifier = modifier,
        enterTransition = {
            fadeIn(
                animationSpec = tween(
                    300, easing = LinearEasing,
                ),
            ) + slideIntoContainer(
                animationSpec = tween(300, easing = EaseIn),
                towards = AnimatedContentTransitionScope.SlideDirection.Start,
            )
        },
        exitTransition = {
            fadeOut(
                animationSpec = tween(
                    300, easing = LinearEasing,
                ),
            ) + slideOutOfContainer(
                animationSpec = tween(200, easing = EaseOut),
                towards = AnimatedContentTransitionScope.SlideDirection.End,
            )
        },
    ) {
        userOnBoardingScreen(
            navController = navController,
            onNavigateToSignInWithEmail = {
                appState.navigateToUserStartDestination(UserStartTopLevelDestination.SIGN_IN_WITH_MAIL)
            },
            onStartWithPhone = {
                appState.navigateToUserStartDestination(UserStartTopLevelDestination.SIGN_IN_WITH_PHONE)
            },
            onNavigateToAccountSetup = {
                appState.navigateToUserStartDestination(UserStartTopLevelDestination.ACCOUNT_SETUP)
            },
        )

        DashboardNavigation(hostModeManager = appState.hostModeManager)

        //SignInNavigation(navHostController = navController)
    }
}