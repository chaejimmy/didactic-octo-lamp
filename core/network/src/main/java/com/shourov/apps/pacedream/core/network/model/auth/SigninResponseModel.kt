package com.shourov.apps.pacedream.core.network.model.auth

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SigninResponseModel(
    @SerialName("action")
    val action: String?,
    @SerialName("code")
    val code: Int?,
    @SerialName("data")
    val data: Data?,
    @SerialName("message")
    val message: String?,
    @SerialName("status")
    val status: Boolean?,
)