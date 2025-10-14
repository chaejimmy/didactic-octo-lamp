package com.shourov.apps.pacedream.model.response.auth

import com.shourov.apps.pacedream.model.response.base.StatusResponse
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class MobileOTPResponse(
    @Json(name = "data")
    val user: Any? = null
) : StatusResponse()
