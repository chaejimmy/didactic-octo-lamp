package com.shourov.apps.pacedream.model.response

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class User(
    @Json(name = "emailVerified")
    val emailVerified: Boolean?,

    @Json(name = "first_name")
    val firstName: String?,

    @Json(name = "last_name")
    val lastName: String?,

    @Json(name = "profilePic")
    val profilePic: String?,

    @Json(name = "role_id")
    val roleId: Int?,

    @Json(name = "token")
    val token: String?,

    @Json(name = "user_id")
    val userId: String?,

    @Json(name = "allowSharedBooking")
    val allowSharedBooking: Boolean?,

    @Json(name = "createdAt")
    val createdAt: String?,

    @Json(name = "dob")
    val dob: String?,

    @Json(name = "gender")
    val gender: String?,

    @Json(name = "hobbiesInterests")
    val hobbiesInterests: List<Any?>?,

    @Json(name = "_id")
    val id: String?,

    @Json(name = "identityVerified")
    val identityVerified: Boolean?,

    @Json(name = "isBlocked")
    val isBlocked: Boolean?,

    @Json(name = "mobile")
    val mobile: String?,

    @Json(name = "mobileVerified")
    val mobileVerified: Boolean?,

    @Json(name = "partnerHosting")
    val partnerHosting: List<Any?>?,

    @Json(name = "preferredLanguage")
    val preferredLanguage: String?,

    @Json(name = "properties")
    val properties: List<Any?>?,

    @Json(name = "rating")
    val rating: Int?,

    @Json(name = "rememberMe")
    val rememberMe: Boolean?,

    @Json(name = "reviews")
    val reviews: List<Any?>?,

    @Json(name = "superHost")
    val superHost: Boolean?,

    @Json(name = "updatedAt")
    val updatedAt: String?
)
