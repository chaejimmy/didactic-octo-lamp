package com.shourov.apps.pacedream.model.response.timebase

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class TimeBasedResponse(
    @Json(name = "code")
    val code: Int?,
    @Json(name = "data")
    val data: List<Data>?,
    @Json(name = "message")
    val message: String?,
    @Json(name = "status")
    val status: Boolean?
)