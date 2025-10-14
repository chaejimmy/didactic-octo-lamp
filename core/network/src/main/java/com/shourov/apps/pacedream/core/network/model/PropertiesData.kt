package com.shourov.apps.pacedream.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable


@Serializable
data class PropertiesData(
    @SerialName("accommodations")
    val accommodations: List<String?>?,
    @SerialName("acreage")
    val acreage: Int?,
    @SerialName("city")
    val city: String?,
    @SerialName("country")
    val country: String?,
    @SerialName("cover_image")
    val coverImage: String?,
    @SerialName("created_at")
    val created_At: Long?,
    @SerialName("createdAt")
    val createdAt: String?,
    @SerialName("currency")
    val currency: String?,
    @SerialName("description")
    val description: String?,
    @SerialName("general_amenities")
    val generalAmenities: List<GeneralAmenity?>?,
    @SerialName("host_id")
    val hostId: String?,
    @SerialName("_id")
    val id: String?,
    @SerialName("latitude")
    val latitude: Double?,
    @SerialName("longitude")
    val longitude: Double?,
    @SerialName("name")
    val name: String?,
    @SerialName("parking")
    val parking: Parking?,
    @SerialName("place_image")
    val placeImage: List<String?>?,
    @SerialName("postal_code")
    val postalCode: String?,
    @SerialName("property_type")
    val propertyType: String?,
    @SerialName("rental_form")
    val rentalForm: String?,
    @SerialName("reviews")
    val reviews: List<String?>?,
    @SerialName("rules")
    val rules: List<Rule?>?,
    @SerialName("safe_amenities")
    val safeAmenities: List<SafeAmenity?>?,
    @SerialName("state")
    val state: String?,
    @SerialName("street")
    val street: String?,
    @SerialName("updated_at")
    val updated_At: Long?,
    @SerialName("updatedAt")
    val updatedAt: String?,
    @SerialName("__v")
    val v: Int?
)