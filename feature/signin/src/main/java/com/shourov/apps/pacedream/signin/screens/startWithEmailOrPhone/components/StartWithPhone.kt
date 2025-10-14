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

package com.shourov.apps.pacedream.signin.screens.startWithEmailOrPhone.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pacedream.common.R
import com.pacedream.common.composables.VerticalSpacer
import com.pacedream.common.composables.buttons.ProcessButton
import com.pacedream.common.composables.inputfields.PhoneInputField
import com.pacedream.common.composables.texts.ClickableText
import com.pacedream.common.composables.texts.TitleText

@Preview
@Composable
fun StartWithPhone(
    onNavigateToSignIn: () -> Unit = {},
    //onContinueClicked: () -> Unit = {}
    onPhoneNumberChange: (String,String) -> Unit = {_,_ ->}
){
    var phoneNumber by remember{ mutableStateOf("") }
    var selectedCountry by remember { mutableStateOf("") }
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(6.dp)) {
        TitleText(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.core_ui_auth_header_title, "Phone")
        )
        VerticalSpacer(height = 6)
        ClickableText(
            modifier = Modifier.fillMaxWidth(),
            nonClickPart = stringResource(id = R.string.core_ui_have_account_question, "Already"),
            clickablePart = stringResource(id = R.string.core_ui_onboarding_sign_in),
            onClick = { onNavigateToSignIn() }
        )

        VerticalSpacer(height = 10)

        PhoneInputField(
            modifier = Modifier.fillMaxWidth(),
            phoneNumber = phoneNumber,
            onPhoneNumberChanged = {
                phoneNumber = it
                onPhoneNumberChange(phoneNumber, selectedCountry)
            },
            onCountrySelected = {
                selectedCountry = it.countryCode
            },
            onClear = {phoneNumber = ""},
        )

        //VerticalSpacer(height = 6)

        /*ProcessButton(
            modifier = Modifier.fillMaxWidth(),
            text = stringResource(id = R.string.core_ui_continue_button),
            onClick = { onContinueClicked() },
        )*/
    }
}