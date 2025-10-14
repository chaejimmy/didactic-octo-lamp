package com.shourov.apps.pacedream.model.response.base

import com.squareup.moshi.Json

abstract class StatusResponse {
    @Json(name = "action")
    val action: String = ""

    @Json(name = "code")
    val statusCode: Int = 0

    @Json(name = "status")
    var status: Boolean = false

    @Json(name = "message")
    var message: String = ""

    override fun toString(): String {
        return "BaseResponse(status=$status, message=$message, code=$statusCode)"
    }
}
