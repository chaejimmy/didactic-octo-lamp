package com.shourov.apps.pacedream.model.request

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ForgotPasswordRequest(
    @Json(name = "email")
    var email: String,
    @Json(name = "method")
    var method: String
)