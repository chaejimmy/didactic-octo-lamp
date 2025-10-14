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

package com.shourov.apps.pacedream.signin.screens.createAccount.components


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.focus.onFocusChanged
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pacedream.common.R
import com.pacedream.common.composables.inputfields.CustomInputField
import com.pacedream.common.composables.inputfields.CustomInputTextField
import com.pacedream.common.composables.inputfields.InputType.TEXT
import com.shourov.apps.pacedream.core.ui.textFieldStates.NameValidationState
import com.pacedream.common.icon.PaceDreamIcons

@Composable
fun UsernameDetails(
    modifier: Modifier = Modifier,
    onUserProfileResponse: (String, String, Boolean) -> Unit = {_, _, _ ->},
) {
    val firstName by remember {
        mutableStateOf(NameValidationState())
    }
    val lastName by remember {
        mutableStateOf(NameValidationState())
    }

    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        val focusManager = LocalFocusManager.current

        CustomInputField(
            label = R.string.core_ui_first_name_label,
            value = firstName.text,
            onValueChange = {
                firstName.text = it
                onUserProfileResponse(
                    firstName.text,
                    lastName.text,
                    firstName.isValid && lastName.isValid,
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    firstName.onFocusChange(focusState.isFocused)
                    if (!focusState.isFocused) {
                        firstName.enableShowErrors()
                    }
                },
            inputType = TEXT,
            onClear = {
                firstName.text = ""
            },
            leadingIcon = {
                Icon(
                    imageVector = PaceDreamIcons.Person,
                    contentDescription = PaceDreamIcons.Person.name,
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Next,
            ),
            keyboardActions = KeyboardActions(
                onNext = {
                    firstName.enableShowErrors()
                    focusManager.moveFocus(FocusDirection.Down)
                },
            ),
            showsErrors = lastName.showErrors(),
            errorText = firstName.getError() ?: "",
        )
        Spacer(modifier = Modifier.height(8.dp))
        CustomInputField(
            label = R.string.core_ui_last_name_label,
            value = lastName.text,
            onValueChange = {
                lastName.text = it
                onUserProfileResponse(
                    firstName.text,
                    lastName.text,
                    firstName.isValid && lastName.isValid,
                )
            },
            modifier = Modifier
                .fillMaxWidth()
                .onFocusChanged { focusState ->
                    lastName.onFocusChange(focusState.isFocused)
                    if (!focusState.isFocused) {
                        lastName.enableShowErrors()
                    }
                },
            inputType = TEXT,
            onClear = {
                lastName.text = ""
            },
            leadingIcon = {
                Icon(
                    imageVector = PaceDreamIcons.Person,
                    contentDescription = PaceDreamIcons.Person.name,
                )
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Text,
                imeAction = ImeAction.Done,
            ),
            keyboardActions = KeyboardActions(
                onDone = {
                    focusManager.clearFocus()
                    lastName.enableShowErrors()
                },
            ),
            enabled = firstName.isValid,
            showsErrors = lastName.showErrors(),
            errorText = lastName.getError() ?: "",
        )
        Spacer(modifier = Modifier.height(8.dp))
    }
}

@Preview
@Composable
fun UsernameDetailsPreview() {
    UsernameDetails(
        onUserProfileResponse = { _, _, _ -> },
    )
}