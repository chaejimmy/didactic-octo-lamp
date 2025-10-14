package com.shourov.apps.pacedream.core.ui.otp

import android.os.Build
import android.os.Build.VERSION_CODES
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.platform.SoftwareKeyboardController
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pacedream.common.composables.buttons.ProcessButton
import com.shourov.apps.pacedream.core.common.result.Result
import com.shourov.apps.pacedream.core.ui.AccountCreationHeader
import com.shourov.apps.pacedream.core.ui.R
import com.pacedream.common.composables.theme.PaceDreamTheme
import com.pacedream.common.composables.theme.stronglyDeemphasizedAlpha

@RequiresApi(Build.VERSION_CODES.TIRAMISU)
@Composable
fun OtpScreen(
    modifier: Modifier = Modifier,
    otpValue: String,
    keyboardController: SoftwareKeyboardController? = LocalSoftwareKeyboardController.current,
    focusRequester: FocusRequester = remember { FocusRequester() },
    onOtpModified: (String, Boolean) -> Unit,
    userPhoneNumber: String = "",
    onHaveNotReceivedOtp: () -> Unit = {},
    onConfirmOtp: () -> Unit = {},
    otpVerificationState: Result<Boolean> = Result.Loading,
) {
    LaunchedEffect(Unit) {
        focusRequester.requestFocus()
        keyboardController?.show()
    }

    Column(
        modifier = modifier
            .fillMaxWidth(),
        horizontalAlignment = Alignment.Start,
    ) {
        AccountCreationHeader(
            title = stringResource(id = R.string.core_ui_otp_title, ""),
            subtitle = stringResource(id = R.string.core_ui_otp_subtitle, userPhoneNumber),
            modifier = Modifier.fillMaxWidth(),
            actionText = "", // todo add count down timer here
            onHeaderActionClick = {},
        )
        OtpInputField(
            modifier = Modifier
                .fillMaxWidth()
                .focusRequester(focusRequester),
            otpText = otpValue,
            shouldCursorBlink = false,
            onOtpModified = onOtpModified,
        )
        Spacer(modifier = Modifier.height(12.dp))

        val color by animateColorAsState(
            targetValue = if (otpValue.length == OTP_LENGTH) {
                MaterialTheme.colorScheme.outlineVariant.copy(
                    stronglyDeemphasizedAlpha,
                )
            } else {
                ButtonDefaults.buttonColors().disabledContainerColor
            },
            label = "otp_not_received_button_color",
        )
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier
                .wrapContentWidth()
                .background(
                    color = color,
                    shape = MaterialTheme.shapes.large,
                )
                .clickable(
                    onClick = onHaveNotReceivedOtp,
                    enabled = otpValue.length == OTP_LENGTH,
                ),
        ) {
            Text(
                text = stringResource(
                    id = R.string.core_ui_have_not_received_otp,
                    "(0:12)",
                ), // TODO: Add countdown timer here
                color = MaterialTheme.colorScheme.onSurface,
                style = MaterialTheme.typography.bodyMedium,
                modifier = Modifier.padding(horizontal = 8.dp, vertical = 4.dp),
            )
        }
        Spacer(modifier = Modifier.height(12.dp))

        ProcessButton(
            onClick = onConfirmOtp,
            modifier = Modifier.fillMaxWidth(),
            isProcessing = otpVerificationState is Result.Loading,
            isEnabled = otpValue.length == OTP_LENGTH,
        )
    }
}

@RequiresApi(VERSION_CODES.TIRAMISU)
@Preview(showBackground = true)
@Composable
fun OtpScreenPreview() {
    PaceDreamTheme {
        OtpScreen(
            otpValue = "1234",
            onOtpModified = { _, _ -> },
            userPhoneNumber = "+1 234 567 8901",
        )
    }
}
