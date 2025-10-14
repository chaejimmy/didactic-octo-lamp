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
import com.shourov.apps.pacedream.model.MessageModel

@Entity(tableName = "messages")
data class MessageEntity(
    @PrimaryKey
    val id: String,
    val chatId: String,
    val senderId: String,
    val receiverId: String,
    val content: String,
    val messageType: String, // TEXT, IMAGE, FILE, SYSTEM
    val attachmentUrl: String?,
    val isRead: Boolean,
    val timestamp: String,
    val createdAt: String
)

fun MessageEntity.asExternalModel(): MessageModel {
    return MessageModel(
        id = id,
        chatId = chatId,
        senderId = senderId,
        receiverId = receiverId,
        content = content,
        messageType = messageType,
        attachmentUrl = attachmentUrl,
        isRead = isRead,
        timestamp = timestamp,
        createdAt = createdAt
    )
}

fun MessageModel.asEntity(): MessageEntity {
    return MessageEntity(
        id = id,
        chatId = chatId,
        senderId = senderId,
        receiverId = receiverId,
        content = content,
        messageType = messageType,
        attachmentUrl = attachmentUrl,
        isRead = isRead,
        timestamp = timestamp,
        createdAt = createdAt
    )
}
