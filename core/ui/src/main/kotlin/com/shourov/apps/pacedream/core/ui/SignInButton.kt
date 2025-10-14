package com.shourov.apps.pacedream.core.ui

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeOut
import androidx.compose.animation.scaleIn
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun SignInButton(
    @DrawableRes logo: Int? = null,
    icon: ImageVector? = null,
    @StringRes text: Int,
    onClick: () -> Unit,
    modifier: Modifier,
    isLoading: Boolean,
) {
    OutlinedButton(
        modifier = modifier,
        onClick = onClick,
    ) {
        logo?.let {
            Image(
                painter = painterResource(id = logo),
                contentDescription = stringResource(id = text),
                modifier = Modifier
                    .size(ButtonDefaults.IconSize)
                    .align(Alignment.CenterVertically),
            )
            Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
        }
        icon?.let {
            Icon(
                imageVector = icon,
                contentDescription = stringResource(id = text),
                modifier = Modifier
                    .size(ButtonDefaults.IconSize)
                    .align(Alignment.CenterVertically),
                tint = MaterialTheme.colorScheme.onBackground,
            )
            Spacer(modifier = Modifier.width(ButtonDefaults.IconSpacing))
        }
        Text(
            text = stringResource(id = text),
            modifier = Modifier
                .padding(vertical = 2.dp)
                .align(Alignment.CenterVertically),
            style = MaterialTheme.typography.labelLarge,
            color = MaterialTheme.colorScheme.onBackground,
        )
        val density = LocalDensity.current
        AnimatedVisibility(
            visible = isLoading,
            label = "sign in loading animation",
            enter = slideInHorizontally {
                with(density) { -40.dp.roundToPx() }
            } + scaleIn(),
            exit = slideOutHorizontally(
                tween(
                    durationMillis = 300,
                    easing = FastOutSlowInEasing,
                ),
            ) + fadeOut(),
            modifier = Modifier.padding(start = 8.dp),
        ) {
            CircularProgressIndicator(
                modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .padding(vertical = 8.dp)
                    .size(18.dp),
                strokeWidth = 2.dp,
                color = MaterialTheme.colorScheme.onBackground,
            )
        }
    }
}

@Preview
@Composable
fun SignInItemPreview() {
    SignInButton(
        logo = R.drawable.google_logo,
        text = R.string.core_ui_continue_with_google,
        onClick = {},
        modifier = Modifier.fillMaxWidth(),
        isLoading = false,
    )
}
