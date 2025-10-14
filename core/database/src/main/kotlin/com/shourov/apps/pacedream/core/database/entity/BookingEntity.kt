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
import com.shourov.apps.pacedream.model.BookingModel

@Entity(tableName = "bookings")
data class BookingEntity(
    @PrimaryKey
    val id: String,
    val userId: String,
    val propertyId: String,
    val propertyName: String,
    val propertyImage: String?,
    val startDate: String,
    val endDate: String,
    val startTime: String?,
    val endTime: String?,
    val totalPrice: Double,
    val currency: String,
    val status: String, // PENDING, CONFIRMED, CANCELLED, COMPLETED
    val paymentStatus: String, // PENDING, PAID, REFUNDED
    val specialRequests: String?, // JSON string for special requests
    val hostId: String,
    val hostName: String,
    val createdAt: String,
    val updatedAt: String
)

fun BookingEntity.asExternalModel(): BookingModel {
    return BookingModel(
        id = id,
        userId = userId,
        propertyId = propertyId,
        propertyName = propertyName,
        propertyImage = propertyImage,
        startDate = startDate,
        endDate = endDate,
        startTime = startTime,
        endTime = endTime,
        totalPrice = totalPrice,
        currency = currency,
        status = status,
        paymentStatus = paymentStatus,
        specialRequests = specialRequests, // This would need to be parsed from JSON
        hostId = hostId,
        hostName = hostName,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun BookingModel.asEntity(): BookingEntity {
    return BookingEntity(
        id = id,
        userId = userId,
        propertyId = propertyId,
        propertyName = propertyName,
        propertyImage = propertyImage,
        startDate = startDate,
        endDate = endDate,
        startTime = startTime,
        endTime = endTime,
        totalPrice = totalPrice,
        currency = currency,
        status = status,
        paymentStatus = paymentStatus,
        specialRequests = specialRequests, // This would need to be serialized to JSON
        hostId = hostId,
        hostName = hostName,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}
