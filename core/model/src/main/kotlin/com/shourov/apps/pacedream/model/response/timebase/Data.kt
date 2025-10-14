package com.shourov.apps.pacedream.model.response.timebase

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(
    @Json(name = "__v")
    val __v: Int?,
    @Json(name = "_id")
    val _id: String?,
    @Json(name = "createdAt")
    val createdAt: String?,
    @Json(name = "currency")
    val currency: String?,
    @Json(name = "host_id")
    val host_id: String?,
    @Json(name = "price")
    val price: Int?,
    @Json(name = "rating")
    val rating: Int?,
    @Json(name = "room_data")
    val room_data: RoomData?,
    @Json(name = "room_id")
    val room_id: String?,
    @Json(name = "updatedAt")
    val updatedAt: String?,
)