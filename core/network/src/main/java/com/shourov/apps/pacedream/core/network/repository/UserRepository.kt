package com.shourov.apps.pacedream.core.network.repository

import com.shourov.apps.pacedream.core.network.model.ApiResult
import com.shourov.apps.pacedream.model.request.ForgotPasswordRequest
import com.shourov.apps.pacedream.model.request.GetOTPRequest
import com.shourov.apps.pacedream.model.request.LoginRequest
import com.shourov.apps.pacedream.model.request.LoginWithEmailRequest
import com.shourov.apps.pacedream.model.request.SendEmailCodeRequest
import com.shourov.apps.pacedream.model.request.SignUpRequest
import com.shourov.apps.pacedream.model.request.SignUpRequestEmail
import com.shourov.apps.pacedream.model.request.VerifyEmailRequest
import com.shourov.apps.pacedream.model.response.auth.ForgotPasswordResponse
import com.shourov.apps.pacedream.model.response.auth.LoginResponse
import com.shourov.apps.pacedream.model.response.auth.MobileOTPResponse
import com.shourov.apps.pacedream.model.response.auth.UserProfileResponse
import com.shourov.apps.pacedream.model.response.home.HourlyRentedGearResponse
import com.shourov.apps.pacedream.model.response.home.rooms.RoomsResponse

interface UserRepository {
    suspend fun signInUser(request: LoginRequest): ApiResult<LoginResponse>

    suspend fun sendOTP(request: GetOTPRequest): ApiResult<MobileOTPResponse>
    suspend fun signInUserEmail(request: LoginWithEmailRequest): ApiResult<LoginResponse>
    suspend fun forgotPassword(request: ForgotPasswordRequest): ApiResult<ForgotPasswordResponse>

    suspend fun signUpUser(request: SignUpRequest): ApiResult<MobileOTPResponse>
    suspend fun signUpUserEmail(request: SignUpRequestEmail): ApiResult<LoginResponse>
    suspend fun sendVerificationCodeEmail(request: SendEmailCodeRequest): ApiResult<LoginResponse>
    suspend fun verifyEmail(request: VerifyEmailRequest): ApiResult<LoginResponse>

    suspend fun getUserInfo(): ApiResult<UserProfileResponse>
    suspend fun getRoomStayAll(): ApiResult<RoomsResponse>
}