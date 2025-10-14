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

import androidx.compose.animation.AnimatedContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import com.shourov.apps.pacedream.signin.model.CreateAccountComponents
import com.shourov.apps.pacedream.signin.model.CreateAccountData
import com.shourov.apps.pacedream.signin.screens.startWithEmailOrPhone.StartWithEmailOrPhoneScreen

@Composable
fun CreateAccountParts(
    state : String,
    accountDataStateChange: (CreateAccountData) -> Unit = {},
    onNavigateToSignIn : () -> Unit = {}
) {
    var accountData by remember { mutableStateOf(CreateAccountData()) }


        AnimatedContent(targetState = state, label = "createAccountScreenAnimation") { it ->
            when (it) {
                CreateAccountComponents.START_EMAIL_PHONE.name ->{
                    StartWithEmailOrPhoneScreen(
                        onStartEmailPhoneResponse = {phone, email, _, ->
                            if (phone.isNotBlank()){
                                accountData = accountData.copy(phoneNumber = phone,)
                            }else if (email.isNotBlank()){
                                accountData = accountData.copy(email= email)
                            }
                        },
                        onNavigateToSignIn = {}
                    )
                }
                CreateAccountComponents.NAME_DETAILS.toString() -> {
                    //userNameDetails
                    UsernameDetails(
                        onUserProfileResponse = { firstName, lastName, isNameValid ->
                            if (isNameValid) {
                                accountData =
                                    accountData.copy(firstName = firstName, lastName = lastName)
                                accountDataStateChange(accountData)
                            }
                        }
                    )
                }

                CreateAccountComponents.DATE_OF_BIRTH.toString() -> {
                    //Date of birth
                    UserDateOfBirth(
                        onDateSelected = { timeInMills ->
                            accountData = accountData.copy(dateOfBirthMillis = timeInMills)
                            accountDataStateChange(accountData)
                        }
                    )
                }

                CreateAccountComponents.HOBBIES_AND_INTERESTS.toString() -> {
                    //Hobbies and interests
                    HobbiesNInterests()
                }

                CreateAccountComponents.PASSWORD.toString() -> {
                    //Password content
                    Password(
                        onPasswordResponse = { password, isPasswordValid ->
                            if (isPasswordValid) {
                                accountData = accountData.copy(password = password)
                                accountDataStateChange(accountData)
                            }
                        }
                    )
                }

                CreateAccountComponents.PROFILE_PICTURE_SETUP.toString() -> {
                    //Profile picture setup
                    ProfilePicture()
                }
            }
        }
    }