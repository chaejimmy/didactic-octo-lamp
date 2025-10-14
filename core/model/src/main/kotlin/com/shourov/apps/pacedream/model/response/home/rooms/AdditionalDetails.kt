package com.shourov.apps.pacedream.model.response.home.rooms

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class AdditionalDetails(
    @Json(name = "_id")
    val _id: String?,
    @Json(name = "garage")
    val garage: Boolean?,
    @Json(name = "petFriendly")
    val petFriendly: Boolean?,
    @Json(name = "smokingFriendly")
    val smokingFriendly: Boolean?
)