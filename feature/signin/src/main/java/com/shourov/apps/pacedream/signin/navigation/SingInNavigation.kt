/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shourov.apps.pacedream.signin.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import com.shourov.apps.pacedream.signin.screens.createAccount.CreateAccountScreen
import com.shourov.apps.pacedream.signin.screens.onBoarding.OnBoardingScreen
import com.shourov.apps.pacedream.signin.screens.signIn.SignIn
import com.shourov.apps.pacedream.signin.screens.startWithEmailOrPhone.StartWithEmailOrPhoneScreen

fun NavGraphBuilder.signInNavigation(
    navHostController: NavHostController,
){
    /*composable(route = SignInRoutes.START_EMAIL_PHONE.name){
        StartWithEmailOrPhoneScreen(navHostController = navHostController)
    }*/
    composable(route = SignInRoutes.ONBOARDING.name){
        OnBoardingScreen(navHostController)
    }

    composable(route = SignInRoutes.CREATE_ACCOUNT.name){
        CreateAccountScreen(navHostController)
    }

    composable(route = SignInRoutes.SIGN_IN.name){
        SignIn(navHostController)
    }
}