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

import com.shourov.apps.pacedream.feature.home.domain.models.rooms.RoomModel
import kotlinx.serialization.Serializable

@Serializable
data class Deal(
    val __v: Int,
    val _id: String,
    val archived: Boolean,
    val attachments: List<Attachment>?,
    val available: Boolean,
    val category: String,
    val createdAt: String,
    val deleted: Boolean,
    val details: Details,
    val gallery: Gallery,
    val hidden: Boolean,
    val item_type: String,
    val location: Location,
    val owner: String,
    val price: List<Price>?,
    val rating: Int,
    val status: String,
    val summary: String,
    val title: String,
    val updatedAt: String,
)

fun Deal.toRoomModel() = RoomModel(
    __v,
    _id,
    archived,
    attachments?.map { it.toAttachmentModel() },
    available,
    category,
    createdAt,
    deleted,
    details.toDetailsModel(),
    gallery.toGalleryModel(),
    hidden,
    item_type,
    location.toLocationModel(),
    owner,
    price?.map { it.toPriceModel() },
    rating,
    listOf(),
    status,
    summary,
    title,
    updatedAt,
)