package com.shourov.apps.pacedream.model.response.auth

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForgotPasswordResponse(
    @Json(name = "action")
    val action: String?,
    @Json(name = "code")
    val code: Int?,
    @Json(name = "status")
    val status: Boolean?,
    @Json(name = "data")
    val data: String?,
    @Json(name = "message")
    val message: String?,
)



