package com.shourov.apps.pacedream.model.response.home.rooms

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RoomDetails(
    @Json(name = "_id")
    val _id: String?,
    @Json(name = "available_rooms")
    val available_rooms: List<String>?,
    @Json(name = "beds")
    val beds: Int?
)