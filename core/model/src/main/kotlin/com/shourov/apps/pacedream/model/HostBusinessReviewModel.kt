package com.shourov.apps.pacedream.model

data class HostBusinessReviewModel(
    val userProfilePicture: Int? = null,
    val userName: String? = null,
    val rating: Double? = null,
    val bookingNo: String? = null,
    val details: String? = null,
    val visitorCount: Int? = null,
    val shareCount: Int? = null,
    val dateTime: String? = null,
    var isClicked: Boolean? = false
)
