package com.shourov.apps.pacedream.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Parking(
    @SerialName("availability")
    val availability: Boolean?,
    @SerialName("capacity")
    val capacity: Int?,
    @SerialName("_id")
    val id: String?
)