package com.shourov.apps.pacedream.model.response.home.rooms

import com.shourov.apps.pacedream.model.response.home.rooms.Data
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RoomsResponse(
    @Json(name = "action")
    val action: String?,
    @Json(name = "code")
    val code: Int?,
    @Json(name = "data")
    val `data`: Data?,
    @Json(name = "message")
    val message: String?,
    @Json(name = "status")
    val status: Boolean?
)