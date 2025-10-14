package com.shourov.apps.pacedream.model.response.home.rooms

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class GuestDetails(
    @Json(name = "_id")
    val _id: String?,
    @Json(name = "gender")
    val gender: String?,
    @Json(name = "quantity")
    val quantity: Int?
)