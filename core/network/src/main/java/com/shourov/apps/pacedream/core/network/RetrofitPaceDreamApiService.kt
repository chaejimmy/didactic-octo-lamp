package com.shourov.apps.pacedream.core.network

import com.shourov.apps.pacedream.core.network.model.AllPropertiesResponseModel
import com.shourov.apps.pacedream.core.network.model.ProfileInformationResponse
import com.shourov.apps.pacedream.core.network.model.UpdatedProfileData
import com.shourov.apps.pacedream.core.network.model.auth.SignInRequestModel
import com.shourov.apps.pacedream.core.network.model.auth.SigninResponseModel
import com.shourov.apps.pacedream.core.network.model.auth.SignupRequestModel
import com.shourov.apps.pacedream.core.network.model.auth.SignupWithMobileResponseModel
import com.shourov.apps.pacedream.core.network.model.otp.SendMobileOtpResponse
import com.shourov.apps.pacedream.model.SendOtpRequestModel
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.POST
import retrofit2.http.PUT

interface RetrofitPaceDreamApiService {
    @GET(ApiEndPoints.GET_ALL_PROPERTIES)
    suspend fun getAllProperties(): Response<AllPropertiesResponseModel>

    @POST(ApiEndPoints.SEND_OTP)
    suspend fun sendOtp(
        @Body sendOtpRequestModel: SendOtpRequestModel,
    ): Response<SendMobileOtpResponse>

    @POST(ApiEndPoints.AUTH_LOGIN_MOBILE)
    suspend fun signInRequest(
        @Body sendOtpRequestModel: SignInRequestModel,
    ): Response<SigninResponseModel>

    @POST(ApiEndPoints.AUTH_SIGNUP_MOBILE)
    suspend fun sendSignUpRequest(
        @Body data: SignupRequestModel,
    ): Response<SignupWithMobileResponseModel>

    @GET(ApiEndPoints.USER_GET_PROFILE)
    suspend fun getUserInformation(
        @Header("Authorization") aAuthorization: String,
    ): Response<ProfileInformationResponse>

    @PUT(ApiEndPoints.UPDATE_USER_PROFILE)
    suspend fun updateUserInformation(
        @Header("Authorization") aAuthorization: String,
        @Body data: UpdatedProfileData,
    ): Response<Any>

    @GET(ApiEndPoints.GET_USER_REVIEWS)
    suspend fun getUserReviews(
        @Header("Authorization") aAuthorization: String,
    ): Response<Any>

    @GET(ApiEndPoints.GET_ALREADY_BOOKED)
    suspend fun getProfileAlreadyBookedList(
        @Header("Authorization") aAuthorization: String,
    ): Response<Any>
}