package com.shourov.apps.pacedream.core.network.model


import com.google.gson.annotations.SerializedName

data class ProfileData(
    @SerializedName("allowSharedBooking")
    val allowSharedBooking: Boolean?,
    @SerializedName("createdAt")
    val createdAt: String?,
    @SerializedName("dob")
    val dob: String?,
    @SerializedName("emailVerified")
    val emailVerified: Boolean?,
    @SerializedName("first_name")
    val firstName: String?,
    @SerializedName("gender")
    val gender: String?,
    @SerializedName("hobbiesInterests")
    val hobbiesInterests: List<Any?>?,
    @SerializedName("_id")
    val id: String?,
    @SerializedName("identityVerified")
    val identityVerified: Boolean?,
    @SerializedName("isBlocked")
    val isBlocked: Boolean?,
    @SerializedName("last_name")
    val lastName: String?,
    @SerializedName("mobile")
    val mobile: String?,
    @SerializedName("mobileVerified")
    val mobileVerified: Boolean?,
    @SerializedName("partnerHosting")
    val partnerHosting: List<Any?>?,
    @SerializedName("preferredLanguage")
    val preferredLanguage: String?,
    @SerializedName("profilePic")
    val profilePic: String?,
    @SerializedName("properties")
    val properties: List<Any?>?,
    @SerializedName("rating")
    val rating: Int?,
    @SerializedName("rememberMe")
    val rememberMe: Boolean?,
    @SerializedName("reviews")
    val reviews: List<Any?>?,
    @SerializedName("role_id")
    val roleId: Int?,
    @SerializedName("superHost")
    val superHost: Boolean?,
    @SerializedName("updatedAt")
    val updatedAt: String?
)