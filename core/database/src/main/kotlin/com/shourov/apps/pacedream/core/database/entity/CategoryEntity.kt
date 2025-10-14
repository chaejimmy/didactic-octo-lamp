package com.shourov.apps.pacedream.core.database.entity

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "categories")
data class CategoryEntity(
    @PrimaryKey
    val id: String,
    val name: String,
    val icon: String,
    val color: String,
    val isActive: Boolean = true,
    val sortOrder: Int = 0,
    val createdAt: String,
    val updatedAt: String
)
