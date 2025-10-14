package com.shourov.apps.pacedream.core.data

data class SignInResult(
    val data: UserData?,
    val errorMessage: String?,
)

data class UserData(
    val userId: String,
    val username: String?,
    val profilePictureUrl: String?,
)

data class UserDataUiState(
    val userId: String = "",
    val username: String? = "",
    val profilePictureUrl: String? = "",
)

fun UserData.toUserDataUiState(): UserDataUiState = UserDataUiState(
    userId = userId,
    username = username,
    profilePictureUrl = profilePictureUrl,
)

fun UserDataUiState.toUserData(): UserData = UserData(
    userId = userId,
    username = username,
    profilePictureUrl = profilePictureUrl,
)

data class GoogleSignInState(
    val isSignInSuccessful: Boolean = false,
    val signInError: String? = null,
)
