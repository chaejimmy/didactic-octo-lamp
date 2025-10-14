package com.shourov.apps.pacedream.model.request

data class SignUpRequestEmail(
    val dob: String,
    val firstName: String,
    val lastName: String,
    val gender: String,
    val password: String,
    val email: String
)
