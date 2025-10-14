package com.shourov.apps.pacedream.core.ui.textFieldStates

import com.shourov.apps.pacedream.core.data.util.isPasswordValid
import com.shourov.apps.pacedream.core.data.util.passwordAndConfirmationValid
import com.shourov.apps.pacedream.core.data.util.passwordConfirmationError
import com.shourov.apps.pacedream.core.data.util.passwordValidationError

class PasswordValidationState : GenericTextFieldState<String>(
    validator = { isPasswordValid(it) },
    errorFor = { passwordValidationError(it) },
    initialText = "",
)

class ConfirmPasswordState(
    private val newPassword: String,
) : GenericTextFieldState<String>(
    validator = { passwordAndConfirmationValid(newPassword, it) },
    errorFor = { passwordConfirmationError() },
    initialText = "",
)