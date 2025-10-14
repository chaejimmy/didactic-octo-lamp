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

package com.shourov.apps.pacedream.feature.home.data.dto.retned_gears

import com.shourov.apps.pacedream.feature.home.domain.models.RentedGearModel
import kotlinx.serialization.Serializable

@Serializable
data class RentedGearDeal(
    val __v: Int,
    val _id: String,
    val city: String,
    val country: String,
    val createdAt: String,
    val gear_description: String,
    val gear_name: String,
    val gear_product: String,
    val gear_type: String,
    val host_id: String,
    val hourly_rate: Int,
    val images: List<String>?,
    val isDeleted: Boolean,
    val isSnoozed: Boolean,
    val location: String,
    val rental_duration_end_date: String,
    val rental_duration_end_time: String,
    val rental_duration_start_date: String,
    val rental_duration_start_time: String,
    val street: String,
    val updatedAt: String,
)

fun RentedGearDeal.toRentedGearModel() = RentedGearModel(
    __v,
    _id,
    city,
    country,
    createdAt,
    gear_description,
    gear_name,
    gear_product,
    gear_type,
    host_id,
    hourly_rate,
    images,
    isDeleted,
    isSnoozed,
    location,
    rental_duration_end_date,
    rental_duration_end_time,
    rental_duration_start_date,
    rental_duration_start_time,
    street,
    updatedAt,
)