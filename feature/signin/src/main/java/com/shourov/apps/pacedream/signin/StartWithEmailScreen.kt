package com.shourov.apps.pacedream.signin

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Color.Companion
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.shourov.apps.pacedream.core.data.UserAuthPath
import com.shourov.apps.pacedream.core.ui.EmailEntryScreen
import com.shourov.apps.pacedream.core.ui.R
import com.shourov.apps.pacedream.core.ui.SignInButton
import com.pacedream.common.icon.PaceDreamIcons
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.AuthResult
import com.google.android.gms.tasks.Task


@Composable
fun StartWithEmailScreen(
    onContinueWithGoogleClicked: () -> Unit,
    modifier: Modifier = Modifier,
    userAuthPath: UserAuthPath,
    onNavigateToPhoneEntry: (UserAuthPath) -> Unit,
    onForgotPasswordClicked: () -> Unit,
    onContinueAccountSetup: () -> Unit,
    onNavigateToCreateAccount: () -> Unit,
    onNavigateToAccountSignIn: () -> Unit,
) {
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    val auth = FirebaseAuth.getInstance()
    var showDialog by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf("") }


    Scaffold(
        modifier = Modifier.fillMaxSize(),
    ) { innerPadding ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(state = rememberScrollState()),
            verticalArrangement = Arrangement.SpaceBetween,
        ) {

            EmailEntryScreen(
                onForgetPasswordClicked = onForgotPasswordClicked,
                onContinueAccountSetup = onContinueAccountSetup,
                onNavigateToCreateAccount = onNavigateToCreateAccount,
                onVerifySignIn = {
                    if (email.isNotEmpty() && password.isNotEmpty()) {
                        auth.signInWithEmailAndPassword(email, password)
                            .addOnCompleteListener { task: Task<AuthResult> ->
                                if (task.isSuccessful) {
                                    // Sign-in successful
                                    onContinueAccountSetup()
                                } else {
                                    // Sign-in failed, handle the error
                                    errorMessage =
                                        task.exception?.message ?: "Unknown error occurred"
                                    showDialog = true
                                }
                            }
                    } else {
                        // Handle case where email or password is empty
                        errorMessage = "Email and password cannot be empty"
                        showDialog = true
                    }
                },
                onNavigateToAccountSignIn = onNavigateToAccountSignIn,
                userAuthPath = userAuthPath,
                continueButtonText = stringResource(id = R.string.core_ui_continue_button),
                modifier = modifier,
                emailState = email,
                onEmailChange = { email = it },
                passwordState = password,
                onPasswordChange = { password = it },
            )

            Column(
                modifier = Modifier.padding(horizontal = 16.dp),
            ) {
                SignInButton(
                    icon = PaceDreamIcons.PhoneAndroid,
                    text = R.string.core_ui_continue_with_phone,
                    onClick = { onNavigateToPhoneEntry(userAuthPath) },
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = false,
                )
                Spacer(modifier = Modifier.height(8.dp))
                SignInButton(
                    logo = R.drawable.google_logo,
                    text = R.string.core_ui_continue_with_google,
                    onClick = onContinueWithGoogleClicked,
                    modifier = Modifier.fillMaxWidth(),
                    isLoading = false,
                )
            }
        }
    }
}
//
//    if (showDialog) {
//        AlertDialog(
//            onDismissRequest = { showDialog = false },
//            title = { Text(text = "Sign-in Error") },
//            text = { Text(text = errorMessage) },
//            confirmButton = {
//                Button(onClick = { showDialog = false }) {
//                    Text(text = "OK")
//                }
//            }
//        )
//    }
