package com.shourov.apps.pacedream.model.request

data class VerifyEmailRequest(
    val email: String,
    val code: String
)