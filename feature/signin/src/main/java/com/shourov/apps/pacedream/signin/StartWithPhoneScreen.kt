package com.shourov.apps.pacedream.signin

import PhoneEntryScreen
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.systemBars
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.shourov.apps.pacedream.core.data.UserAuthPath
//import com.shourov.apps.pacedream.core.ui.PhoneEntryScreen
import com.shourov.apps.pacedream.core.ui.R
import com.shourov.apps.pacedream.core.ui.SignInButton

@Composable
fun StartWithPhoneScreen(
    onCreateAccountClicked: () -> Unit,
    onContinueWithMailClicked: (UserAuthPath) -> Unit,
    modifier: Modifier = Modifier,
    userAuthPath: UserAuthPath,
    onNavigateToAccountSignIn: () -> Unit,
    onContinueAccountSetup: () -> Unit,
) {

    PhoneEntryScreen(
        onProceedToOtpVerification = {},
        modifier = modifier,
        onNavigateToCreateAccount = onCreateAccountClicked,
        onNavigateToAccountSignIn = onNavigateToAccountSignIn,
        userAuthPath = userAuthPath,
        onNavigateToAccountSetup = onContinueAccountSetup,
    )
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 12.dp)
            .padding(bottom = 20.dp),
        contentAlignment = Alignment.BottomCenter,
    ) {
        Column {
            SignInButton( //Continue with Email button
                logo = R.drawable.google_gmail,
                text = R.string.core_ui_continue_with_email,
                onClick = { onContinueWithMailClicked(userAuthPath) },
                modifier = Modifier.fillMaxWidth(),
                isLoading = false,
            )
            Spacer(modifier = Modifier.height(8.dp))
            SignInButton( //Continue with Google
                logo = R.drawable.google_logo,
                text = R.string.core_ui_continue_with_google, // strings based on loading state
                onClick = { /*TODO google auth client */ },
                modifier = Modifier.fillMaxWidth().padding(bottom = WindowInsets.systemBars.asPaddingValues().calculateBottomPadding()),
                isLoading = false, // todo replace with auth client loading state
            )
           // Spacer(modifier = Modifier.height(16.dp))
        }
    }
}