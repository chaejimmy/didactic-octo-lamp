package com.shourov.apps.pacedream.signin.navigation

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavOptions
import androidx.navigation.compose.composable
import com.shourov.apps.pacedream.core.data.UserAuthPath
import com.shourov.apps.pacedream.core.data.UserAuthPath.EXISTING
import com.shourov.apps.pacedream.core.data.UserAuthPath.NEW
import com.shourov.apps.pacedream.home.HomeScreen
import com.shourov.apps.pacedream.signin.OnBoardingScreen
import com.shourov.apps.pacedream.signin.StartWithEmailScreen
import com.shourov.apps.pacedream.signin.StartWithPhoneScreen
import com.shourov.apps.pacedream.signin.setup.SetupScreen

const val ONBOARDING_ROUTE = "onboarding_route"
const val START_WITH_EMAIL_ROUTE = "start_with_email_route"
const val START_SIGN_IN_WITH_PHONE_ROUTE = "sign_in_route"
const val CREATE_ACCOUNT_ROUTE = "create_account_route"
const val HOME_ROUTE = "home_route"

const val DASHBOARD_ROUTE = "dashboard_route"

fun NavController.navigateToUserOnBoardingScreen(navOptions: NavOptions) =
    navigate(ONBOARDING_ROUTE, navOptions)

fun NavController.navigateToSignInWithPhoneScreen(navOptions: NavOptions) =
    navigate(START_SIGN_IN_WITH_PHONE_ROUTE, navOptions)

fun NavController.navigateToEmailSignInScreen(navOptions: NavOptions) =
    navigate(START_WITH_EMAIL_ROUTE, navOptions)

fun NavController.navigateToCreateAccountScreen(navOptions: NavOptions) =
    navigate(CREATE_ACCOUNT_ROUTE, navOptions)

fun NavController.navigateToHomeScreen(navOptions: NavOptions) =
    navigate(HOME_ROUTE, navOptions)


fun NavGraphBuilder.userOnBoardingScreen(
    navController: NavController,
    onStartWithPhone: () -> Unit,
    onNavigateToSignInWithEmail: () -> Unit,
    onNavigateToAccountSetup: () -> Unit,
) {
    var userAuthPath: UserAuthPath = NEW
    composable(
        route = ONBOARDING_ROUTE,
    ) {
        OnBoardingScreen(
            onCreateAccount = {
                userAuthPath = NEW
                onStartWithPhone()
            },
            onSignIn = {
                userAuthPath = EXISTING
                //onStartWithPhone()
                onNavigateToSignInWithEmail()
            },
        )
    }

    composable(
        route = START_SIGN_IN_WITH_PHONE_ROUTE,
    ) {
        StartWithPhoneScreen(
            onCreateAccountClicked = {
                userAuthPath = NEW
                onStartWithPhone()
            },
            onContinueWithMailClicked = {
                userAuthPath = it
                onNavigateToSignInWithEmail()
            },
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 12.dp,
                )
                .padding(
                    top = 16.dp,
                ),
            userAuthPath = userAuthPath,
            onNavigateToAccountSignIn = {
                userAuthPath = EXISTING
                //onStartWithPhone()
                onNavigateToSignInWithEmail()
            },
            onContinueAccountSetup = onNavigateToAccountSetup,
        )
    }

    composable(
        route = START_WITH_EMAIL_ROUTE,
    ) {
        StartWithEmailScreen(
            onContinueWithGoogleClicked = { /*TODO google auth client */ },
            modifier = Modifier
                .fillMaxSize()
                .padding(
                    horizontal = 12.dp,
                ),
            userAuthPath = userAuthPath,
            onNavigateToPhoneEntry = {
                userAuthPath = it
                onStartWithPhone()
            },
            onForgotPasswordClicked = { /*TODO forgot password */ },
            onContinueAccountSetup = onNavigateToAccountSetup,
            onNavigateToCreateAccount = {
                userAuthPath = NEW
                onStartWithPhone()
            },
            onNavigateToAccountSignIn = {
                userAuthPath = EXISTING
                onStartWithPhone()
            },
        )
    }

    composable(
        route = CREATE_ACCOUNT_ROUTE,
    ) {
        SetupScreen()
    }

    composable(
        route = HOME_ROUTE,
    ) {
        HomeScreen()
    }
}
