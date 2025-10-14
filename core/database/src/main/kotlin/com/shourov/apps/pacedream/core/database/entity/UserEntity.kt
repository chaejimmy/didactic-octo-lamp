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
import com.shourov.apps.pacedream.model.response.User

@Entity(tableName = "users")
data class UserEntity(
    @PrimaryKey
    val id: String,
    val firstName: String,
    val lastName: String,
    val email: String,
    val phone: String?,
    val profilePic: String?,
    val dateOfBirth: String?,
    val gender: String?,
    val isVerified: Boolean,
    val createdAt: String,
    val updatedAt: String,
    val preferences: String? // JSON string for user preferences
)

fun UserEntity.asExternalModel(): User {
    return User(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phone = phone,
        profilePic = profilePic,
        dateOfBirth = dateOfBirth,
        gender = gender,
        isVerified = isVerified,
        createdAt = createdAt,
        updatedAt = updatedAt
    )
}

fun User.asEntity(): UserEntity {
    return UserEntity(
        id = id,
        firstName = firstName,
        lastName = lastName,
        email = email,
        phone = phone,
        profilePic = profilePic,
        dateOfBirth = dateOfBirth,
        gender = gender,
        isVerified = isVerified,
        createdAt = createdAt,
        updatedAt = updatedAt,
        preferences = null // Handle preferences separately
    )
}
