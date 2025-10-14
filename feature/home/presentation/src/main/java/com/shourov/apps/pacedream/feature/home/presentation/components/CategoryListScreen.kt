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

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pacedream.common.composables.components.*
import com.pacedream.common.composables.theme.*

@Composable
fun CategoryListScreen(
    category: String,
    onBackClick: () -> Unit,
    onPropertyClick: (String) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PaceDreamColors.Background)
    ) {
        // Header
        MinimalDashboardHeader(
            title = category,
            subtitle = "Properties in this category",
            onBackClick = onBackClick
        )
        
        // Category Content
        LazyColumn(
            modifier = Modifier
                .fillMaxSize()
                .padding(PaceDreamSpacing.LG)
        ) {
            item {
                // Category Description
                Card(
                    modifier = Modifier.fillMaxWidth(),
                    colors = CardDefaults.cardColors(containerColor = PaceDreamColors.Card),
                    elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
                ) {
                    Column(
                        modifier = Modifier.padding(PaceDreamSpacing.LG)
                    ) {
                        Text(
                            text = "About $category",
                            style = PaceDreamTypography.Title3,
                            color = PaceDreamColors.TextPrimary
                        )
                        
                        Spacer(modifier = Modifier.height(PaceDreamSpacing.SM))
                        
                        Text(
                            text = "Discover amazing properties in the $category category. Find the perfect place for your needs with our curated selection of high-quality listings.",
                            style = PaceDreamTypography.Body,
                            color = PaceDreamColors.TextSecondary
                        )
                    }
                }
            }
            
            item {
                Spacer(modifier = Modifier.height(PaceDreamSpacing.LG))
                
                // Filter Options
                Text(
                    text = "Filter by",
                    style = PaceDreamTypography.Title3,
                    color = PaceDreamColors.TextPrimary
                )
                
                Spacer(modifier = Modifier.height(PaceDreamSpacing.MD))
                
                val filterOptions = listOf("Price", "Rating", "Distance", "Amenities")
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(PaceDreamSpacing.SM)
                ) {
                    items(filterOptions) { option ->
                        FilterChip(
                            label = option,
                            isSelected = false,
                            onClick = { /* Handle filter */ }
                        )
                    }
                }
            }
            
            item {
                Spacer(modifier = Modifier.height(PaceDreamSpacing.LG))
                
                // Properties List
                Text(
                    text = "Properties",
                    style = PaceDreamTypography.Title3,
                    color = PaceDreamColors.TextPrimary
                )
            }
            
            // Mock properties
            items(15) { index ->
                EnhancedPropertyCard(
                    propertyName = "$category Property ${index + 1}",
                    location = "Location ${index + 1}",
                    price = "$${(100 + index * 50)}/night",
                    rating = 4.0f + (index % 5) * 0.2f,
                    amenities = listOf("WiFi", "Parking", "Pool").take(index % 3 + 1),
                    isFavorite = index % 3 == 0,
                    onFavoriteClick = { /* Handle favorite */ },
                    onClick = { onPropertyClick("property_$index") }
                )
                
                Spacer(modifier = Modifier.height(PaceDreamSpacing.MD))
            }
        }
    }
}
