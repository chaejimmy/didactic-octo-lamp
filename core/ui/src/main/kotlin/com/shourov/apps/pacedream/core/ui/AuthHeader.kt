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

package com.shourov.apps.pacedream.core.ui

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.ripple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shourov.apps.pacedream.core.data.UserAuthPath
import com.shourov.apps.pacedream.core.data.UserAuthPath.EXISTING
import com.shourov.apps.pacedream.core.data.UserAuthPath.NEW
import com.pacedream.common.composables.theme.PaceDreamTheme

@Composable
fun AccountCreationHeader(
    modifier: Modifier = Modifier,
    title: String,
    subtitle: String = "",
    actionText: String = "",
    userAuthPath: UserAuthPath? = null,
    onNavigateToCreateAccount: () -> Unit = {},
    onNavigateToAccountSignIn: () -> Unit = {},
    onHeaderActionClick: () -> Unit = {},
) {
    val authSubtitle = if (userAuthPath != null) {
        stringResource(
            id = R.string.core_ui_have_account_question,
            when (userAuthPath) {
                NEW -> "Don't"
                EXISTING -> "Already"
                else -> ""
            },
        )
    } else {
        subtitle
    }

    Column(modifier = modifier) {
        if(userAuthPath == EXISTING){
        Text(
            text = title,
            style = MaterialTheme.typography.headlineLarge.copy(
                fontWeight = FontWeight.Medium,
            ),
        )
        }

        Row(
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(text = authSubtitle)
            Spacer(modifier = Modifier.width(4.dp))
            val text = when (userAuthPath) {
                UserAuthPath.EXISTING -> stringResource(id = R.string.core_ui_onboarding_sign_in)
                UserAuthPath.NEW -> stringResource(id = R.string.core_ui_create_account)
                else -> actionText
            }
            Text(
                text = text,
                color = MaterialTheme.colorScheme.primary,
                fontWeight = FontWeight.Medium,
                modifier = Modifier.clickable(
                    onClick = {
                        when (userAuthPath) {
                            EXISTING -> onNavigateToAccountSignIn()
                            NEW -> onNavigateToCreateAccount()
                            else -> onHeaderActionClick()
                        }
                    },
                    interactionSource = remember {
                        MutableInteractionSource()
                    },
                    indication = ripple(
                        bounded = true,
                    ),
                ),
            )
        }
    }
    Spacer(modifier = Modifier.height(24.dp))
}

@Preview
@Composable
fun AccountCreationHeaderPreview() {
    PaceDreamTheme {
        AccountCreationHeader(
            title = stringResource(id = R.string.core_ui_auth_header_title, "phone"),
            subtitle = stringResource(
                R.string.core_ui_have_account_question,
                "Already",
            ),
            userAuthPath = EXISTING,
        )
    }
}