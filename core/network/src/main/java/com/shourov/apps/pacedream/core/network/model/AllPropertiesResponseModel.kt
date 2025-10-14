package com.shourov.apps.pacedream.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AllPropertiesResponseModel(
    @SerialName("action")
    val action: String?,
    @SerialName("code")
    val code: Int?,
    @SerialName("data")
    val data: ArrayList<PropertiesData?>?,
    @SerialName("message")
    val message: String?,
    @SerialName("status")
    val status: Boolean?
)