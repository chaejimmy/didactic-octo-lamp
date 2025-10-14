package com.shourov.apps.pacedream.navigation

import androidx.annotation.DrawableRes
import androidx.annotation.StringRes
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.Stable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pacedream.common.composables.theme.PaceDreamColors
import com.pacedream.common.composables.theme.PaceDreamSpacing
import com.pacedream.common.composables.theme.PaceDreamTypography

@Composable
fun AppBottomNavigation(
    items: List<BottomNavigationItem>,
    selectedIndex: Int,
    onItemClick: (Int) -> Unit,
) {

    NavigationBar(
        windowInsets = WindowInsets(
            top = 0.dp,
            bottom = 0.dp,
            left = 0.dp,
            right = 0.dp,
        ),
        modifier = Modifier
            .fillMaxWidth()
            .height(80.dp),
        containerColor = PaceDreamColors.Background,
        tonalElevation = 8.dp
    ) {
        items.forEachIndexed { index, bottomNavigationItem ->
            NavigationBarItem(
                selected = selectedIndex == index,
                onClick = { onItemClick(index) },
                icon = {
                    Box(
                        modifier = Modifier
                            .size(48.dp)
                            .clip(RoundedCornerShape(12.dp))
                            .background(
                                if (selectedIndex == index) 
                                    PaceDreamColors.Primary.copy(alpha = 0.1f) 
                                else 
                                    Color.Transparent
                            ),
                        contentAlignment = Alignment.Center
                    ) {
                        Icon(
                            modifier = Modifier.size(24.dp),
                            painter = painterResource(id = bottomNavigationItem.icon),
                            contentDescription = stringResource(id = bottomNavigationItem.text),
                            tint = if (selectedIndex == index) 
                                PaceDreamColors.Primary 
                            else 
                                PaceDreamColors.TextSecondary
                        )
                    }
                },
                label = {
                    Text(
                        text = stringResource(id = bottomNavigationItem.text),
                        style = PaceDreamTypography.Caption.copy(
                            fontSize = 11.sp,
                            fontWeight = if (selectedIndex == index) 
                                FontWeight.SemiBold 
                            else 
                                FontWeight.Normal
                        ),
                        color = if (selectedIndex == index) 
                            PaceDreamColors.Primary 
                        else 
                            PaceDreamColors.TextSecondary
                    )
                },
                colors = NavigationBarItemDefaults.colors(
                    selectedIconColor = PaceDreamColors.Primary,
                    selectedTextColor = PaceDreamColors.Primary,
                    unselectedIconColor = PaceDreamColors.TextSecondary,
                    unselectedTextColor = PaceDreamColors.TextSecondary,
                    indicatorColor = Color.Transparent
                )
            )
        }
    }
}

@Stable
data class BottomNavigationItem(
    @DrawableRes val icon: Int,
    @StringRes val text: Int
)