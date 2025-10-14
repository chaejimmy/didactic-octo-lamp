package com.shourov.apps.pacedream.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Rule(
    @SerialName("description")
    val description: String?,
    @SerialName("icon")
    val icon: String?,
    @SerialName("_id")
    val id: String?,
    @SerialName("title")
    val title: String?
)