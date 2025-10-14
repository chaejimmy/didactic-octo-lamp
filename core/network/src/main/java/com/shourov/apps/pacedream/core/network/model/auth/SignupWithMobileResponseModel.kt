package com.shourov.apps.pacedream.core.network.model.auth


import com.google.gson.annotations.SerializedName


data class SignupWithMobileResponseModel(
    @SerializedName("action")
    val action: String?,
    @SerializedName("code")
    val code: Int?,
    @SerializedName("data")
    val data: Any?,
    @SerializedName("message")
    val message: String?,
    @SerializedName("status")
    val status: Boolean?
)