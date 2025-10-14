package com.shourov.apps.pacedream.model.response.timebase

import com.shourov.apps.pacedream.model.response.timebase.AdditionalDetails
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class RoomData(
    @Json(name = "__v")
    val __v: Int?,
    @Json(name = "_id")
    val _id: String?,
    @Json(name = "additional_details")
    val additional_details: AdditionalDetails?,
    @Json(name = "amenities")
    val amenities: List<String>?,
    @Json(name = "createdAt")
    val createdAt: String?,
    @Json(name = "description")
    val description: String?,
    @Json(name = "dynamic_price")
    val dynamic_price: List<DynamicPrice>?,
    @Json(name = "end_date")
    val end_date: String?,
    @Json(name = "facilities")
    val facilities: List<String>?,
    @Json(name = "faq")
    val faq: List<Faq>,
    @Json(name = "guest_details")
    val guest_details: GuestDetails?,
    @Json(name = "host_id")
    val host_id: String?,
    @Json(name = "ideal_renters")
    val ideal_renters: List<Any>?,
    @Json(name = "images")
    val images: List<String>?,
    @Json(name = "isDeleted")
    val isDeleted: Boolean?,
    @Json(name = "location")
    val location: Location?,
    @Json(name = "name")
    val name: String?,
    @Json(name = "property_type")
    val property_type: String?,
    @Json(name = "rating")
    val rating: Int?,
    @Json(name = "room_details")
    val room_details: RoomDetails?,
    @Json(name = "room_type")
    val room_type: String?,
    @Json(name = "rules")
    val rules: List<String>?,
    @Json(name = "start_date")
    val start_date: String?,
    @Json(name = "status")
    val status: Boolean?,
    @Json(name = "updatedAt")
    val updatedAt: String?
)