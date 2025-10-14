package com.shourov.apps.pacedream.core.ui.textFieldStates

import com.google.i18n.phonenumbers.NumberParseException
import com.google.i18n.phonenumbers.PhoneNumberUtil
import com.google.i18n.phonenumbers.Phonenumber

class PhoneNumberValidationState(
    countryCode: String,
) : GenericTextFieldState<String>(
    validator = { isValidPhoneNumber(it, countryCode) },
    errorFor = { "Invalid phone number" },
    initialText = "",
)

fun isValidPhoneNumber(
    phoneNumber: String,
    countryCode: String = "US",
): Boolean {
    val phoneUtil = PhoneNumberUtil.getInstance()
    return try {
        val numberProto: Phonenumber.PhoneNumber =
            phoneUtil.parse(
                phoneNumber,
                countryCode,
            )
        phoneUtil.isValidNumber(numberProto)
    } catch (e: NumberParseException) {
        false
    }
}
