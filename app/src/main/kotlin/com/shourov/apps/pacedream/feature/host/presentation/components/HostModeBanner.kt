package com.shourov.apps.pacedream.feature.host.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pacedream.common.composables.theme.*

@Composable
fun HostModeBanner(
    isHostMode: Boolean,
    onSwitchToGuest: () -> Unit,
    onSwitchToHost: () -> Unit,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(60.dp),
        colors = CardDefaults.cardColors(
            containerColor = if (isHostMode) 
                PaceDreamColors.Primary.copy(alpha = 0.1f) 
            else 
                PaceDreamColors.Info.copy(alpha = 0.1f)
        ),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaceDreamSpacing.MD),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Row(verticalAlignment = Alignment.CenterVertically) {
                Icon(
                    imageVector = if (isHostMode) Icons.Default.Home else Icons.Default.Person,
                    contentDescription = null,
                    tint = if (isHostMode) PaceDreamColors.Primary else PaceDreamColors.Info,
                    modifier = Modifier.size(20.dp)
                )
                Spacer(modifier = Modifier.width(PaceDreamSpacing.SM))
                Text(
                    text = if (isHostMode) "Host Mode" else "Guest Mode",
                    style = PaceDreamTypography.Body,
                    color = if (isHostMode) PaceDreamColors.Primary else PaceDreamColors.Info,
                    fontWeight = FontWeight.SemiBold
                )
            }
            
            TextButton(
                onClick = if (isHostMode) onSwitchToGuest else onSwitchToHost,
                colors = ButtonDefaults.textButtonColors(
                    contentColor = if (isHostMode) PaceDreamColors.Primary else PaceDreamColors.Info
                )
            ) {
                Text(
                    text = if (isHostMode) "Switch to Guest" else "Switch to Host",
                    style = PaceDreamTypography.Caption,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}
