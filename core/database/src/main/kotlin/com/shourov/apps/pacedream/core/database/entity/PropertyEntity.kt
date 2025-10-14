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

package com.shourov.apps.pacedream.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.shourov.apps.pacedream.model.response.home.rooms.Result

@Entity(tableName = "properties")
data class PropertyEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val description: String?,
    val propertyType: String,
    val location: String, // JSON string for location data
    val images: String, // JSON string for image URLs
    val amenities: String, // JSON string for amenities
    val rating: Double?,
    val reviewCount: Int?,
    val basePrice: Double,
    val currency: String,
    val isAvailable: Boolean,
    val hostId: String,
    val hostName: String,
    val hostAvatar: String?,
    val additionalDetails: String?, // JSON string for additional details
    val createdAt: String,
    val updatedAt: String
)

fun PropertyEntity.asExternalModel(): Result {
    return Result(
        id = id,
        name = name,
        description = description,
        property_type = propertyType,
        location = location, // This would need to be parsed from JSON
        images = images, // This would need to be parsed from JSON
        amenities = amenities, // This would need to be parsed from JSON
        rating = rating,
        review_count = reviewCount,
        pricing = com.shourov.apps.pacedream.model.response.home.rooms.Pricing(
            base_price = basePrice,
            currency = currency
        ),
        is_available = isAvailable,
        host = com.shourov.apps.pacedream.model.response.home.rooms.Host(
            id = hostId,
            name = hostName,
            avatar = hostAvatar
        ),
        additional_details = additionalDetails, // This would need to be parsed from JSON
        created_at = createdAt,
        updated_at = updatedAt
    )
}

fun Result.asEntity(): PropertyEntity {
    return PropertyEntity(
        id = id,
        name = name,
        description = description,
        propertyType = property_type,
        location = location, // This would need to be serialized to JSON
        images = images, // This would need to be serialized to JSON
        amenities = amenities, // This would need to be serialized to JSON
        rating = rating,
        reviewCount = review_count,
        basePrice = pricing.base_price,
        currency = pricing.currency,
        isAvailable = is_available,
        hostId = host.id,
        hostName = host.name,
        hostAvatar = host.avatar,
        additionalDetails = additional_details, // This would need to be serialized to JSON
        createdAt = created_at,
        updatedAt = updated_at
    )
}
