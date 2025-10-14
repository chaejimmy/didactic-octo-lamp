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

package com.shourov.apps.pacedream.feature.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.pacedream.common.composables.VerticalSpacer
import com.pacedream.common.composables.texts.NormalTitleText
import com.pacedream.common.composables.theme.ExtraLargePadding
import com.pacedream.common.composables.theme.LargePadding
import com.pacedream.common.composables.theme.NormalPadding
import com.shourov.apps.pacedream.feature.home.domain.models.CategoryModel
import com.shourov.apps.pacedream.feature.home.presentation.R

@Composable
fun CategoryCard(
    category: CategoryModel,
    onClick: (CategoryModel) -> Unit,
) {
    category.apply {
        Box(
            modifier = Modifier.padding(NormalPadding).clip(RoundedCornerShape(LargePadding))
                .clickable { onClick(category) }
                .paint(
                    painterResource(id = R.drawable.bg_dashboard_header),
                    contentScale = ContentScale.FillBounds,
                ),
        ) {
            Surface(
                color = color.copy(alpha = 0.8f),
            ) {
                Column(
                    modifier = Modifier.padding(
                        vertical = ExtraLargePadding,
                        horizontal = 40.dp,
                    ),
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center,
                ) {
                    Image(
                        painter = painterResource(icon),
                        null,
                        modifier = Modifier.size(40.dp),
                    )
                    VerticalSpacer(20)
                    NormalTitleText(Modifier, title, Color.White)
                }
            }
        }
    }
}