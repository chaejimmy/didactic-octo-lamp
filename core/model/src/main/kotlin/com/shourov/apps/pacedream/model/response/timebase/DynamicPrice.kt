package com.shourov.apps.pacedream.model.response.timebase

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class DynamicPrice(
    @Json(name = "_id")
    val _id: String?,
    @Json(name = "currency")
    val currency: String?,
    @Json(name = "daily")
    val daily: Daily?,
    @Json(name = "hourly")
    val hourly: Hourly?,
    @Json(name = "monthly")
    val monthly: Monthly?,
    @Json(name = "price")
    val price: Int?,
    @Json(name = "weekend")
    val weekend: Weekend?
)