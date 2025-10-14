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

package com.shourov.apps.pacedream.signin.screens.onBoarding.components

import com.shourov.apps.pacedream.signin.model.OnBoardingScreenItem
import com.pacedream.common.R


object OnBoardingScreenItems {
    val onBoardingScreenItems = listOf(
        OnBoardingScreenItem(
            title =  R.string.core_ui_onboarding_title_1,
            description = R.string.core_ui_onboarding_description_1,
            image = R.drawable.onboarding_image_1,
        ),
        OnBoardingScreenItem(
            title = R.string.core_ui_onboarding_title_2,
            description = R.string.core_ui_onboarding_description_2,
            image = R.drawable.onboarding_image_2,
        ),
        OnBoardingScreenItem(
            title = R.string.core_ui_onboarding_title_3,
            description = R.string.core_ui_onboarding_description_3,
            image = R.drawable.onboarding_image_3,
        ),
    )
}