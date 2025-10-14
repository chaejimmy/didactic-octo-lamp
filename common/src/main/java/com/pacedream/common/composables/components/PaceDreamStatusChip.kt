package com.pacedream.common.composables.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pacedream.common.composables.theme.PaceDreamColors
import com.pacedream.common.composables.theme.PaceDreamRadius
import com.pacedream.common.composables.theme.PaceDreamSpacing
import com.pacedream.common.composables.theme.PaceDreamTypography

@Composable
fun PaceDreamStatusChip(
    status: String,
    isActive: Boolean,
    modifier: Modifier = Modifier
) {
    val backgroundColor = when {
        isActive -> PaceDreamColors.Success.copy(alpha = 0.1f)
        status.equals("pending", ignoreCase = true) -> PaceDreamColors.Warning.copy(alpha = 0.1f)
        status.equals("cancelled", ignoreCase = true) -> PaceDreamColors.Error.copy(alpha = 0.1f)
        status.equals("completed", ignoreCase = true) -> PaceDreamColors.Primary.copy(alpha = 0.1f)
        else -> PaceDreamColors.TextSecondary.copy(alpha = 0.1f)
    }
    
    val textColor = when {
        isActive -> PaceDreamColors.Success
        status.equals("pending", ignoreCase = true) -> PaceDreamColors.Warning
        status.equals("cancelled", ignoreCase = true) -> PaceDreamColors.Error
        status.equals("completed", ignoreCase = true) -> PaceDreamColors.Primary
        else -> PaceDreamColors.TextSecondary
    }
    
    Text(
        text = status.replaceFirstChar { it.uppercase() },
        style = PaceDreamTypography.Caption,
        color = textColor,
        fontWeight = FontWeight.SemiBold,
        modifier = modifier
            .background(
                color = backgroundColor,
                shape = RoundedCornerShape(PaceDreamRadius.Round)
            )
            .padding(
                horizontal = PaceDreamSpacing.SM,
                vertical = PaceDreamSpacing.XS
            )
    )
}
