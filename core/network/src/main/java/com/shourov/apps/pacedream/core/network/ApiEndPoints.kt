package com.shourov.apps.pacedream.core.network

object ApiEndPoints {

    const val GET_ALL_PROPERTIES = "property/get-all-properties"
    const val SEND_OTP = "auth/send-otp"
    const val AUTH_LOGIN_MOBILE = "auth/login/mobile"
    const val AUTH_LOGIN_EMAIL = "auth/login/email"
    const val AUTH_SIGNUP_EMAIL = "auth/signup/email"
    const val AUTH_SEND_EMAIL_CODE = "auth/send-email-code"
    const val AUTH_VERIFY_EMAIL = "auth/verify-email-code"
    const val AUTH_FORGOT_PASSWORD = "auth/forgot-password"
    const val AUTH_SIGNUP_MOBILE = "auth/signup/mobile"
    const val USER_GET_PROFILE = "user/get/profile"
    const val UPDATE_USER_PROFILE = "user/profile"
    const val GET_USER_REVIEWS = "reviews"
    const val GET_ALREADY_BOOKED = ""
    const val HOURLY_RENTED_GEAR = "gear-rentals/get/hourly-rental-gear/{type}"
    const val ROOMMATE_ROOM_STAY = "roommate/get/room-stay"
    const val GET_TIMEBASE_ROOM = "properties/filter-rentable-items-by-group/time_based"
}