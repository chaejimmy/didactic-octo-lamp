package com.shourov.apps.pacedream.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SafeAmenity(
    @SerialName("icon")
    val icon: String?,
    @SerialName("_id")
    val id: String?,
    @SerialName("name")
    val name: String?
)