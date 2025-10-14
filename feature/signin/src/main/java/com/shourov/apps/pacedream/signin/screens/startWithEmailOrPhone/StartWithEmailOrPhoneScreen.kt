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

package com.shourov.apps.pacedream.signin.screens.startWithEmailOrPhone

import androidx.compose.animation.AnimatedContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pacedream.common.composables.VerticalSpacer
import com.pacedream.common.icon.PaceDreamIcons
import com.shourov.apps.pacedream.core.ui.R
import com.shourov.apps.pacedream.core.ui.SignInButton
import com.shourov.apps.pacedream.signin.screens.startWithEmailOrPhone.components.StartWithEmail
import com.shourov.apps.pacedream.signin.screens.startWithEmailOrPhone.components.StartWithPhone

@Preview
@Composable
fun StartWithEmailOrPhoneScreen(
    // navHostController: NavHostController,
    onStartEmailPhoneResponse: (String, String, String) -> Unit = { _, _, _ -> },
    onNavigateToSignIn: () -> Unit = {},

    ) {
    var startWithPhone by remember { mutableStateOf(true) }
    var phoneNumber by remember { mutableStateOf("") }
    var countryCode by remember { mutableStateOf("") }
    var emailAddress by remember { mutableStateOf("") }
    /* PaceDreamTheme {
         Scaffold { paddingValues ->
             Surface(
                 modifier = Modifier.padding(paddingValues),
                 color = MaterialTheme.colorScheme.background,
             ) {
    */
    Column(modifier = Modifier.fillMaxWidth()) {
        AnimatedContent(
            targetState = startWithPhone,
            label = "StartScreen",
        ) {
            if (it) {
                StartWithPhone(
                    onPhoneNumberChange = { phone, country ->
                        phoneNumber = phone
                        countryCode = country
                        onStartEmailPhoneResponse(phoneNumber, emailAddress, countryCode)
                    },
                    onNavigateToSignIn = { onNavigateToSignIn() },//navHostController.navigate(route = SignInRoutes.SIGN_IN.name) },
                    /*onContinueClicked = {
                        //implement save of state
                        navHostController.navigate(route = SignInRoutes.CREATE_ACCOUNT.name)
                    }*/
                )
            } else {
                StartWithEmail(
                    onValueChangeEmailInput = { email ->
                        emailAddress = email
                        onStartEmailPhoneResponse(phoneNumber, emailAddress, countryCode)
                    },
                    onNavigateToSignIn = { onNavigateToSignIn() },//navHostController.navigate(route = SignInRoutes.SIGN_IN.name) },
                    //  onContinueClicked = { navHostController.navigate(route = SignInRoutes.CREATE_ACCOUNT.name) },
                )
            }
        }
        VerticalSpacer(height = 20)
        Box(
            modifier = Modifier
                .padding(horizontal = 12.dp),
            contentAlignment = Alignment.BottomCenter,
        ) {
            Column {
                SignInButton(
                    logo = if (startWithPhone) R.drawable.google_gmail else null,
                    icon = if (!startWithPhone) PaceDreamIcons.PhoneAndroid else null,
                    text = if (startWithPhone) R.string.core_ui_continue_with_email else R.string.core_ui_continue_with_phone,
                    onClick = { startWithPhone = !startWithPhone },
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = false,
                )
                Spacer(modifier = Modifier.height(8.dp))
                SignInButton(
                    logo = R.drawable.google_logo,
                    text = R.string.core_ui_continue_with_google, // strings based on loading state
                    onClick = {   /*TODO google auth client */ },
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(
                            bottom = WindowInsets.systemBars
                                .asPaddingValues()
                                .calculateBottomPadding(),
                        ),
                    isLoading = false, // todo replace with auth client loading state
                )
//            Spacer(modifier = Modifier.height(16.dp).padding())
            }
        }
    }
}
/*
        }
    }
}*/
