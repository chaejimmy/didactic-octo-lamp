package com.shourov.apps.pacedream.core.network.model


import com.google.gson.annotations.SerializedName
import com.shourov.apps.pacedream.core.network.model.ProfileData

data class ProfileInformationResponse(
    @SerializedName("action")
    val action: String?,
    @SerializedName("code")
    val code: Int?,
    @SerializedName("data")
    val profileData: ProfileData?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)