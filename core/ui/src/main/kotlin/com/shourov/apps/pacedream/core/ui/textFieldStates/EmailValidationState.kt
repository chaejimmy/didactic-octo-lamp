package com.shourov.apps.pacedream.core.ui.textFieldStates

// email validation
class EmailValidationState : GenericTextFieldState<String>(
    validator = { isValidEmail(it) },
    errorFor = { "Invalid email" },
    initialText = "",
)

fun isValidEmail(email: String): Boolean {
    return android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()
}