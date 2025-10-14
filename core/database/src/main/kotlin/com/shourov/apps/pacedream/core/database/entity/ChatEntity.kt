package com.shourov.apps.pacedream.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "chats")
data class ChatEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val participants: List<String>, // List of user IDs
    val lastMessage: String?,
    val lastMessageTime: String?,
    val unreadCount: Int = 0,
    val createdAt: String,
    val updatedAt: String
)
