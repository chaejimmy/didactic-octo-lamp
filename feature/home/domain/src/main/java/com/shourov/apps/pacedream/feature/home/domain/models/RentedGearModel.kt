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

package com.shourov.apps.pacedream.feature.home.domain.models

data class RentedGearModel(
    val version: Int,
    val id: String,
    val city: String?,
    val country: String,
    val createdAt: String,
    val description: String,
    val name: String,
    val product: String?,
    val type: String,
    val hostId: String,
    val hourlyRate: Int,
    val images: List<String>?,
    val isDeleted: Boolean,
    val isSnoozed: Boolean,
    val location: String,
    val rentalDurationEndDate: String,
    val rentalDurationEndTime: String,
    val rentalDurationStartDate: String,
    val rentalDurationStartTime: String,
    val street: String,
    val updatedAt: String,
)