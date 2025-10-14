package com.shourov.apps.pacedream.model.response.timebase

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Hourly(
    @Json(name = "_id")
    val _id: String?,
    @Json(name = "available")
    val available: Boolean?,
    @Json(name = "price")
    val price: Int?
)