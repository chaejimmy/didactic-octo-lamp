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

import androidx.compose.animation.animateColorAsState
import androidx.compose.animation.core.animateDpAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.lerp
import com.pacedream.common.composables.theme.stronglyDeemphasizedAlpha
import kotlin.math.absoluteValue

@Composable
fun CustomDotIndicator(currentPage: Int, numberOfPages: Int, pagerState: PagerState) {
    val dotWidth = 8.dp
    val expandedDotWidth = 16.dp

    Row(
        horizontalArrangement = Arrangement.Center,
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier.fillMaxWidth(),
    ) {
        repeat(numberOfPages) { index ->
            val width by animateDpAsState(
                when (index) {
                    currentPage -> lerp(
                        dotWidth,
                        expandedDotWidth,
                        1f - pagerState.currentPageOffsetFraction.absoluteValue,
                    )

                    (currentPage + 1) % numberOfPages -> lerp(
                        dotWidth,
                        expandedDotWidth,
                        pagerState.currentPageOffsetFraction.absoluteValue,
                    )

                    else -> dotWidth
                },
                label = "onBoarding_dot_width_animation",
            )
            val color by animateColorAsState(
                targetValue = if (index == currentPage) MaterialTheme.colorScheme.primary else MaterialTheme.colorScheme.onSurface.copy(
                    stronglyDeemphasizedAlpha,
                ),
                label = "onBoarding_dot_color_animation",
            )
            Box(
                modifier = Modifier
                    .padding(4.dp)
                    .size(width = width, height = dotWidth)
                    .background(
                        color = color,
                        shape = CircleShape,
                    ),
            )
        }
    }
}
