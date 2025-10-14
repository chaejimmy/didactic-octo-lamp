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

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.pacedream.common.composables.VerticalSpacer
import com.pacedream.common.composables.buttons.ProcessButton
import com.pacedream.common.composables.texts.MediumTitleText
import com.pacedream.common.composables.texts.SmallTitleText
import com.pacedream.common.composables.theme.HeadlineColor
import com.pacedream.common.composables.theme.LargePadding
import com.pacedream.common.composables.theme.MediumPadding
import com.pacedream.common.composables.theme.NormalPadding
import com.pacedream.common.composables.theme.ViewAllColor
import com.pacedream.common.util.toCurrencySymbol
import com.shourov.apps.pacedream.feature.home.domain.models.RentedGearModel
import com.shourov.apps.pacedream.feature.home.domain.models.rooms.RoomModel
import com.shourov.apps.pacedream.feature.home.presentation.R

@Composable
fun DealsCard(
    roomModel: RoomModel,
    onClick: () -> Unit,
) {
    roomModel.apply {
        Column(
            modifier = Modifier
                .width(280.dp)
                .padding(NormalPadding)
                .background(Color.White, RoundedCornerShape(LargePadding))
                .clip(RoundedCornerShape(LargePadding))
                .clickable { onClick() },
        ) {
            AsyncImage(
                model = gallery.thumbnail,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(LargePadding)),
            )
            Column(modifier = Modifier.padding(MediumPadding)) {
                VerticalSpacer(6)
                Text(
                    roomModel.title,
                    color = HeadlineColor,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                )
                VerticalSpacer(2)
                Text(
                    roomModel.summary,
                    color = ViewAllColor,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                )
                VerticalSpacer(8)
                val price = roomModel.price?.get(0)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MediumTitleText(
                        text = "${
                            price?.currency?.ifBlank { "USD" }?.toCurrencySymbol()
                        }${price?.amount}",
                        color = HeadlineColor,
                    )
                    SmallTitleText(
                        text = "/ ${price?.frequency}",
                        color = HeadlineColor,
                    )
                }
                VerticalSpacer(12)
                ProcessButton(
                    text = stringResource(R.string.feature_home_rent_now),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                )

            }

        }
    }
}

@Composable
fun RentedGearDealsCard(
    rentedGearModel: RentedGearModel,
    onClick: () -> Unit,
) {
    rentedGearModel.apply {
        Column(
            modifier = Modifier
                .width(280.dp)
                .padding(NormalPadding)
                .background(Color.White, RoundedCornerShape(LargePadding))
                .clip(RoundedCornerShape(LargePadding))
                .clickable { onClick() },
        ) {
            AsyncImage(
                model = if (images.isNullOrEmpty()) R.drawable.no_image else images?.get(0)
                    ?: R.drawable.no_image,
                contentDescription = "",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .fillMaxWidth()
                    .height(180.dp)
                    .clip(RoundedCornerShape(LargePadding)),
            )
            Column(modifier = Modifier.padding(MediumPadding)) {
                VerticalSpacer(6)
                Text(
                    name,
                    color = HeadlineColor,
                    maxLines = 1,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                )
                VerticalSpacer(2)
                Text(
                    description,
                    color = ViewAllColor,
                    maxLines = 2,
                    fontWeight = FontWeight.Bold,
                    overflow = TextOverflow.Ellipsis,
                )
                VerticalSpacer(8)
                Row(verticalAlignment = Alignment.CenterVertically) {
                    MediumTitleText(
                        text = "${
                            "USD".toCurrencySymbol()
                        }${hourlyRate}",
                        color = HeadlineColor,
                    )
                    SmallTitleText(
                        text = "/hour",
                        color = HeadlineColor,
                    )
                }
                VerticalSpacer(12)
                ProcessButton(
                    text = stringResource(R.string.feature_home_rent_now),
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                )

            }

        }
    }
}