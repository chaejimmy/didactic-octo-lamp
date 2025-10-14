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

package com.shourov.apps.pacedream.feature.home.data.dto.time_based_deals

import com.shourov.apps.pacedream.feature.home.domain.models.rooms.LocationModel
import kotlinx.serialization.Serializable

@Serializable
data class Location(
    val city: String,
    val country: String,
    val googlemap_link: String,
    val state: String,
    val street_address: String,
)

fun Location.toLocationModel() = LocationModel(city, country, googlemap_link, state, street_address)
