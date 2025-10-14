package com.shourov.apps.pacedream.core.data

import android.net.Uri

data class AccountSetupData(
    val email: String = "",
    val phoneNumber: String = "",
    val password: String = "",
    val firstName: String = "",
    val lastName: String = "",
    val profilePicture: Uri = Uri.EMPTY,
    val dateOfBirthMillis: Long? = null,
    val gender: UserSetupGender = UserSetupGender.PREFERS_NOT_TO_SAY,
    val hobbiesNInterest: Set<String> = emptySet(),
)

fun AccountSetupData.userProfileDetailsValid(): Boolean {
    return firstName.isNotEmpty() && lastName.isNotEmpty() && email.isNotEmpty()
}

fun AccountSetupData.dateOfBirthValid(): Boolean {
    return dateOfBirthMillis != null && dateOfBirthMillis < System.currentTimeMillis() // todo check if the user is older than 13
}