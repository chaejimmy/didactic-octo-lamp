package com.shourov.apps.pacedream.model.request

import java.io.Serial

data class LoginWithEmailRequest(
    val email: String,
    val password: String
)