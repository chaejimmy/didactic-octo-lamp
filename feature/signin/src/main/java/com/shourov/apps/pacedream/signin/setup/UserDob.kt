package com.shourov.apps.pacedream.signin.setup

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.shourov.apps.pacedream.core.ui.ShowDatePicker
import com.shourov.apps.pacedream.feature.signin.R
import com.pacedream.common.icon.PaceDreamIcons
import com.pacedream.common.composables.theme.stronglyDeemphasizedAlpha
import java.text.SimpleDateFormat
import java.util.Locale
import java.util.TimeZone

const val simpleDateFormatPattern = "EEE, MMM d yyyy"

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserDob(
    modifier: Modifier = Modifier,
    onDateSelected: (Long) -> Unit,
    dateOfBirth: Long? = null,
) {
    var showDatePicker by remember { mutableStateOf(false) }
    val dateFormat = SimpleDateFormat(simpleDateFormatPattern, Locale.getDefault())
    dateFormat.timeZone = TimeZone.getTimeZone("UTC")
    val dateString = dateOfBirth?.let { dateFormat.format(it) }
        ?: stringResource(id = R.string.feature_signin_ui_dob_label)
    Column(
        modifier = modifier
            .fillMaxWidth(),
    ) {
        Text(
            text = "Date of Birth",
        )
        CustomDateTimePickerButton(
            text = dateString,
            onClick = {
                showDatePicker = true
            },
            modifier = Modifier
                .fillMaxWidth(),
        )
        Spacer(modifier = Modifier.height(16.dp))
        // todo gender picker
    }

    AnimatedVisibility(
        visible = showDatePicker,
        label = "dob_picker_dialog_visibility",
    ) {
        ShowDatePicker(
            onDateSelected = {
                onDateSelected(it)
                showDatePicker = false
            },
            onDismiss = { showDatePicker = false },
        )
    }
}

@Composable
fun CustomDateTimePickerButton(
    modifier: Modifier = Modifier,
    text: String,
    onClick: () -> Unit,
    leadingIcon: ImageVector = PaceDreamIcons.Calendar,
    colors: ButtonColors = ButtonDefaults.outlinedButtonColors(
        containerColor = MaterialTheme.colorScheme.outlineVariant.copy(
            stronglyDeemphasizedAlpha,
        ),
        contentColor = MaterialTheme.colorScheme.onSurface,
    ),
) {
    Button(
        onClick = onClick,
        colors = colors,
        shape = MaterialTheme.shapes.medium,
        modifier = modifier,
    ) {
        Icon(
            imageVector = leadingIcon,
            contentDescription = leadingIcon.name,
        )
        Text(
            text = text,
            modifier = Modifier
                .fillMaxWidth()
                .weight(1f)
                .padding(vertical = 8.dp, horizontal = 12.dp),
        )
        Icon(
            imageVector = PaceDreamIcons.ArrowForward,
            contentDescription = PaceDreamIcons.ArrowForward.name,
        )
    }
}


@Preview
@Composable
fun DobPrev(){
    //UserDob(onDateSelected = {})
    CustomDateTimePickerButton(text = "", onClick = { /*TODO*/ })
}