/*
 * Copyright 2024 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.shourov.apps.pacedream.feature.home.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.paint
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import com.pacedream.common.composables.components.PaceDreamSearchBar
import com.pacedream.common.composables.theme.*
import com.shourov.apps.pacedream.feature.home.presentation.R

@Composable
fun EnhancedDashboardHeader(
    userName: String = "Darryl Rutledge",
    onSearchClick: () -> Unit = {},
    onFilterClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .clip(RoundedCornerShape(bottomEnd = PaceDreamRadius.LG, bottomStart = PaceDreamRadius.LG))
            .paint(
                painterResource(id = R.drawable.bg_dashboard_header),
                contentScale = ContentScale.FillBounds,
            ),
    ) {
        Surface(
            color = PaceDreamPrimary.copy(alpha = 0.6f),
            modifier = Modifier.fillMaxSize(),
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(
                        top = 60.dp,
                        start = PaceDreamSpacing.LG,
                        end = PaceDreamSpacing.LG,
                        bottom = PaceDreamSpacing.LG,
                    ),
            ) {
                // User Profile and Notifications Row
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        // User Avatar
                        Image(
                            painter = painterResource(R.drawable.ic_dummy_user),
                            contentDescription = null,
                            modifier = Modifier
                                .size(70.dp)
                                .clip(CircleShape),
                        )
                        
                        Spacer(modifier = Modifier.width(PaceDreamSpacing.MD))
                        
                        // Greeting and Name
                        val annotatedString = buildAnnotatedString {
                            append(stringResource(R.string.feature_home_good_morning))
                            appendLine()
                            withStyle(style = SpanStyle(fontSize = PaceDreamTypography.Body.fontSize)) {
                                append(userName)
                            }
                        }
                        
                        Text(
                            text = annotatedString,
                            color = Color.White,
                            style = PaceDreamTypography.Body,
                            modifier = Modifier.weight(1F),
                        )
                    }
                    
                    // Notification Button
                    Box(
                        modifier = Modifier
                            .clip(CircleShape)
                            .background(PaceDreamAccent)
                            .padding(PaceDreamSpacing.SM)
                    ) {
                        IconButton(
                            onClick = onNotificationClick,
                            modifier = Modifier.size(30.dp)
                        ) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = "Notifications",
                                tint = Color.White,
                                modifier = Modifier.size(PaceDreamIconSize.MD)
                            )
                        }
                    }
                }
                
                Spacer(modifier = Modifier.height(PaceDreamSpacing.LG))
                
                // Divider
                Box(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(1.dp)
                        .background(Color.White.copy(alpha = 0.3f))
                )
                
                Spacer(modifier = Modifier.height(PaceDreamSpacing.LG))
                
                // Main Title
                Text(
                    text = stringResource(R.string.feature_home_find_your_perfect_stay),
                    style = PaceDreamTypography.Title1,
                    color = Color.White,
                    fontWeight = FontWeight.ExtraBold,
                )
                
                Spacer(modifier = Modifier.height(PaceDreamSpacing.LG))
                
                // Search Bar
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(PaceDreamSpacing.MD)
                ) {
                    PaceDreamSearchBar(
                        query = "",
                        onQueryChange = { },
                        onSearchClick = onSearchClick,
                        onFilterClick = onFilterClick,
                        placeholder = "Search properties, locations...",
                        modifier = Modifier.weight(1f)
                    )
                    
                    // Filter Button
                    IconButton(
                        onClick = onFilterClick,
                        modifier = Modifier
                            .size(48.dp)
                            .background(PaceDreamAccent, CircleShape)
                    ) {
                        Icon(
                            imageVector = Icons.Default.FilterList,
                            contentDescription = "Filter",
                            tint = Color.White,
                            modifier = Modifier.size(PaceDreamIconSize.MD)
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun CompactDashboardHeader(
    title: String = "PaceDream",
    onSearchClick: () -> Unit = {},
    onNotificationClick: () -> Unit = {},
    modifier: Modifier = Modifier
) {
    Surface(
        color = PaceDreamPrimary,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 60.dp,
                    start = PaceDreamSpacing.LG,
                    end = PaceDreamSpacing.LG,
                    bottom = PaceDreamSpacing.LG,
                ),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Text(
                text = title,
                style = PaceDreamTypography.Title2,
                color = Color.White,
                fontWeight = FontWeight.Bold
            )
            
            Row(
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.spacedBy(PaceDreamSpacing.SM)
            ) {
                IconButton(onClick = onSearchClick) {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = "Search",
                        tint = Color.White,
                        modifier = Modifier.size(PaceDreamIconSize.MD)
                    )
                }
                
                IconButton(onClick = onNotificationClick) {
                    Icon(
                        imageVector = Icons.Default.Notifications,
                        contentDescription = "Notifications",
                        tint = Color.White,
                        modifier = Modifier.size(PaceDreamIconSize.MD)
                    )
                }
            }
        }
    }
}

@Composable
fun MinimalDashboardHeader(
    title: String = "PaceDream",
    subtitle: String? = null,
    onBackClick: (() -> Unit)? = null,
    onActionClick: (() -> Unit)? = null,
    actionIcon: @Composable (() -> Unit)? = null,
    modifier: Modifier = Modifier
) {
    Surface(
        color = PaceDreamPrimary,
        modifier = modifier.fillMaxWidth()
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(
                    top = 60.dp,
                    start = PaceDreamSpacing.LG,
                    end = PaceDreamSpacing.LG,
                    bottom = PaceDreamSpacing.LG,
                ),
            verticalAlignment = Alignment.CenterVertically
        ) {
            // Back Button
            onBackClick?.let { onClick ->
                IconButton(onClick = onClick) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(PaceDreamIconSize.MD)
                    )
                }
            }
            
            // Title and Subtitle
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = title,
                    style = PaceDreamTypography.Title3,
                    color = Color.White,
                    fontWeight = FontWeight.Bold
                )
                
                subtitle?.let {
                    Text(
                        text = it,
                        style = PaceDreamTypography.Callout,
                        color = Color.White.copy(alpha = 0.9f)
                    )
                }
            }
            
            // Action Button
            onActionClick?.let { onClick ->
                IconButton(onClick = onClick) {
                    actionIcon?.invoke() ?: Icon(
                        imageVector = Icons.Default.MoreVert,
                        contentDescription = "More",
                        tint = Color.White,
                        modifier = Modifier.size(PaceDreamIconSize.MD)
                    )
                }
            }
        }
    }
}
