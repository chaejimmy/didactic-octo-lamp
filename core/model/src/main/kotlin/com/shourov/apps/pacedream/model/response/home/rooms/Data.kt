package com.shourov.apps.pacedream.model.response.home.rooms

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "result")
    val result: List<Result>?,
    @Json(name = "totalCount")
    val totalCount: Int?,
)