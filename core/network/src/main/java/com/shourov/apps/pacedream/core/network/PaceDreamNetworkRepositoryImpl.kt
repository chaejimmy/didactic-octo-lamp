package com.shourov.apps.pacedream.core.network

import com.shourov.apps.pacedream.core.network.model.UpdatedProfileData
import com.shourov.apps.pacedream.core.network.model.auth.SignInRequestModel
import com.shourov.apps.pacedream.core.network.model.auth.SignupRequestModel
import com.shourov.apps.pacedream.model.SendOtpRequestModel
import javax.inject.Inject

class PaceDreamNetworkRepositoryImpl @Inject constructor(
    private val retrofitPaceDreamApiService: RetrofitPaceDreamApiService,
) : PaceDreamNetworkRepository {
    override suspend fun getAllProperties() {
        TODO("Not yet implemented")
    }

    override suspend fun sendOtp(sendOtpRequestModel: SendOtpRequestModel) {
        TODO("Not yet implemented")
    }

    override suspend fun signInRequest(signInRequestModel: SignInRequestModel) {
        TODO("Not yet implemented")
    }

    override suspend fun sendSignUpRequest(description: SignupRequestModel) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserInformation(authorization: String) {
        TODO("Not yet implemented")
    }

    override suspend fun updateUserInformation(authorization: String, aData: UpdatedProfileData) {
        TODO("Not yet implemented")
    }

    override suspend fun getUserReviews(authorization: String) {
        TODO("Not yet implemented")
    }

    override suspend fun getProfileAlreadyBookedList(authorization: String) {
        TODO("Not yet implemented")
    }
}

interface PaceDreamNetworkRepository {
    suspend fun getAllProperties()
    suspend fun sendOtp(sendOtpRequestModel: SendOtpRequestModel)
    suspend fun signInRequest(signInRequestModel: SignInRequestModel)
    suspend fun sendSignUpRequest(description: SignupRequestModel)
    suspend fun getUserInformation(authorization: String)
    suspend fun updateUserInformation(authorization: String, aData: UpdatedProfileData)
    suspend fun getUserReviews(authorization: String)
    suspend fun getProfileAlreadyBookedList(authorization: String)
}