package com.shourov.apps.pacedream.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class ErrorResponse(
    @Json(name = "message")
    val message: Error
)

@JsonClass(generateAdapter = true)
data class Error(
    @Json(name = "error")
    val error: String
)
