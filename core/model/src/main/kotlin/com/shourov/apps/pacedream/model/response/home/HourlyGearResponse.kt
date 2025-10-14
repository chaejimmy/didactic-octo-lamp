package com.shourov.apps.pacedream.model.response.home

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HourlyGearResponse(
    @Json(name = "_id")
    val uId: String?,
    @Json(name ="gear_name")
    val gearName: String?,
    @Json(name ="host_id")
    val hostId : String?,
    @Json(name ="gear_description")
    val gearDescription : String?,
    @Json(name ="hourly_rate")
    val hourlyRate: Int?,
    @Json(name ="gear_categories")
    val gearCategories  : String?,
    @Json(name ="slots_booked")
    val slotsBooked: List<String>? = mutableListOf(),
    @Json(name ="images")
    val images: List<String>? = mutableListOf(),
    @Json(name ="createdAt")
    val createdAt: String?,
    @Json(name ="updatedAt")
    val updatedAt: String?,
    @Json(name ="__v")
    val _v: Int?
)
