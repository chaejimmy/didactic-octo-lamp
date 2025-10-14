package com.shourov.apps.pacedream.core.network.model.auth


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SignInRequestModel(
    @SerialName("mobile")
    val mobile: String?,
    @SerialName("otp")
    val otp: String?
)