package com.shourov.apps.pacedream.core.network.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class UpdatedProfileData(
    @SerialName("first_name")
    val firstName: String?,
    @SerialName("last_name")
    val lastName: String?,
    @SerialName("mobile")
    val mobile: String?,
    @SerialName("gender")
    val gender: String?,
    @SerialName("dob")
    val dob: String?,
    @SerialName("profilePic")
    val profilePic: String?,
    @SerialName("updatedAt")
    val updatedAt: String?,
    @SerialName("country")
    val country: String?= null,
    @SerialName("about")
    val about: String?= null,
    @SerialName("facebookId")
    val facebookId: String?= null,
    @SerialName("twitterId")
    val twitterId: String?= null,
    @SerialName("instaGramId")
    val instaGramId: String?= null,
    @SerialName("occupation")
    val occupation: String?= null,
    @SerialName("rating")
    val rating: String?= null,
    @SerialName("preferredLanguage")
    val preferredLanguage: String? = null,
)