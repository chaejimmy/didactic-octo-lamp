package com.shourov.apps.pacedream.core.network.model.auth


import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Data(
    @SerialName("emailVerified")
    val emailVerified: Boolean?,
    @SerialName("first_name")
    val firstName: String?,
    @SerialName("last_name")
    val lastName: String?,
    @SerialName("profilePic")
    val profilePic: String?,
    @SerialName("role_id")
    val roleId: Int?,
    @SerialName("token")
    val token: String?,
    @SerialName("user_id")
    val userId: String?
)