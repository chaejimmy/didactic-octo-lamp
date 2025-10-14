package com.shourov.apps.pacedream.model.response.home.rooms

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Faq(
    @Json(name = "_id")
    val _id: String?,
    @Json(name = "answer")
    val answer: String?,
    @Json(name = "question")
    val question: String?
)