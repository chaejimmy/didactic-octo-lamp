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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.pacedream.common.composables.components.*
import com.pacedream.common.composables.theme.*
import com.pacedream.common.util.Consts
import com.pacedream.common.util.Consts.FASHION_TYPE
import com.pacedream.common.util.Consts.MUSIC_GEAR_TYPE
import com.pacedream.common.util.Consts.PHOTOGRAPHY_TYPE
import com.pacedream.common.util.Consts.TECH_GEAR_TYPE
import com.pacedream.common.util.showToast
import com.shourov.apps.pacedream.feature.home.domain.models.CategoryModel
import com.shourov.apps.pacedream.feature.home.domain.models.DestinationModel
import com.shourov.apps.pacedream.feature.home.presentation.HomeScreenRentedGearsState
import com.shourov.apps.pacedream.feature.home.presentation.HomeScreenRoomsState
import com.shourov.apps.pacedream.feature.home.presentation.R

@Composable
fun EnhancedDashboardContent(
    roomsState: HomeScreenRoomsState,
    gearsState: HomeScreenRentedGearsState,
    onTimeBasedRoomsChanged: (String) -> Unit,
    onRentedGearsChanged: (String) -> Unit,
    onPropertyClick: (String) -> Unit = {},
    onCategoryClick: (String) -> Unit = {},
    onDestinationClick: (String) -> Unit = {},
    onViewAllClick: (String) -> Unit = {},
    modifier: Modifier = Modifier
) {
    val context = LocalContext.current
    
    LaunchedEffect(roomsState.error) {
        if (!roomsState.error.isNullOrEmpty()) {
            context.showToast(roomsState.error)
        }
    }
    
    LaunchedEffect(gearsState.error) {
        if (!gearsState.error.isNullOrEmpty()) {
            context.showToast(gearsState.error)
        }
    }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(PaceDreamBackground)
            .padding(PaceDreamSpacing.LG)
    ) {
        // Metrics Cards Section
        item {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(PaceDreamSpacing.SM)
            ) {
                PaceDreamMetricCard(
                    title = "Available Rooms",
                    value = "24",
                    icon = Icons.Default.Home,
                    modifier = Modifier.weight(1f)
                )
                PaceDreamMetricCard(
                    title = "Travel Partners",
                    value = "18",
                    icon = Icons.Default.People,
                    modifier = Modifier.weight(1f)
                )
                PaceDreamMetricCard(
                    title = "Properties",
                    value = "31",
                    icon = Icons.Default.LocationOn,
                    modifier = Modifier.weight(1f)
                )
            }
        }
        
        // Categories Section
        item {
            Spacer(modifier = Modifier.height(PaceDreamSpacing.LG))
            PaceDreamSectionHeader(
                title = "Browse by Category",
                onViewAllClick = { onViewAllClick("categories") }
            )
            
            Spacer(modifier = Modifier.height(PaceDreamSpacing.MD))
            
            val categories = listOf(
                CategoryModel(
                    stringResource(R.string.feature_home_rest_room),
                    R.drawable.ic_rest_room,
                    PaceDreamSecondary,
                ),
                CategoryModel(
                    stringResource(R.string.feature_home_ev_parking),
                    R.drawable.ic_ev_parking,
                    PaceDreamAccent,
                ),
                CategoryModel(
                    stringResource(R.string.feature_home_storage_room),
                    R.drawable.ic_storage_room,
                    PaceDreamInfo,
                ),
                CategoryModel(
                    stringResource(R.string.feature_home_parking_spot),
                    R.drawable.ic_ev_parking,
                    PaceDreamSuccess,
                ),
            )
            
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(PaceDreamSpacing.SM)
            ) {
                items(categories) { category ->
                    PaceDreamCategoryPill(
                        title = category.name,
                        icon = when (category.name) {
                            stringResource(R.string.feature_home_rest_room) -> Icons.Default.Bed
                            stringResource(R.string.feature_home_ev_parking) -> Icons.Default.ElectricCar
                            stringResource(R.string.feature_home_storage_room) -> Icons.Default.Storage
                            stringResource(R.string.feature_home_parking_spot) -> Icons.Default.LocalParking
                            else -> Icons.Default.Category
                        },
                        isSelected = false,
                        onClick = { onCategoryClick(category.name) }
                    )
                }
            }
        }
        
        // Recent Searches Section
        item {
            Spacer(modifier = Modifier.height(PaceDreamSpacing.LG))
            PaceDreamSectionHeader(
                title = "Recent Searches",
                onViewAllClick = { onViewAllClick("recent") }
            )
            
            Spacer(modifier = Modifier.height(PaceDreamSpacing.MD))
            
            val recentSearches = listOf(
                "New York, NY",
                "San Francisco, CA",
                "Miami, FL",
                "Chicago, IL"
            )
            
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(PaceDreamSpacing.SM)
            ) {
                items(recentSearches) { search ->
                    PaceDreamRecentSearchItem(
                        location = search,
                        onClick = { /* Handle recent search click */ }
                    )
                }
            }
        }
        
        // Browse by Destination Section
        item {
            Spacer(modifier = Modifier.height(PaceDreamSpacing.LG))
            PaceDreamSectionHeader(
                title = "Browse by Destination",
                onViewAllClick = { onViewAllClick("destinations") }
            )
            
            Spacer(modifier = Modifier.height(PaceDreamSpacing.SM))
            
            Text(
                text = stringResource(R.string.feature_home_explore_perfect_places_by_destination),
                style = PaceDreamTypography.Callout,
                color = PaceDreamTextSecondary
            )
            
            Spacer(modifier = Modifier.height(PaceDreamSpacing.MD))
            
            val destinations = listOf(
                DestinationModel("London", R.drawable.london),
                DestinationModel("New York", R.drawable.new_york),
                DestinationModel("Tokyo", R.drawable.tokyo),
                DestinationModel("Toronto", R.drawable.toronto),
            )
            
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(PaceDreamSpacing.SM)
            ) {
                items(destinations) { destination ->
                    PaceDreamDestinationCard(
                        name = destination.name,
                        imageUrl = null, // Will use drawable resource
                        onClick = { onDestinationClick(destination.name) }
                    )
                }
            }
        }
        
        // Time-based Properties Section
        item {
            Spacer(modifier = Modifier.height(PaceDreamSpacing.LG))
            PaceDreamSectionHeader(
                title = "Time-based Properties",
                onViewAllClick = { onViewAllClick("time-based") }
            )
            
            Spacer(modifier = Modifier.height(PaceDreamSpacing.SM))
            
            Text(
                text = stringResource(R.string.help_you_what_needed),
                style = PaceDreamTypography.Callout,
                color = PaceDreamTextSecondary
            )
            
            Spacer(modifier = Modifier.height(PaceDreamSpacing.MD))
            
            var timeBasedSelectedTabIndex by remember { mutableIntStateOf(0) }
            val timeBasedTabs = listOf("Room", "Restroom", "EV Parking", "Parking")
            
            // Enhanced Tab Component
            ScrollableTabRow(
                selectedTabIndex = timeBasedSelectedTabIndex,
                modifier = Modifier.fillMaxWidth(),
                containerColor = PaceDreamSurface,
                contentColor = PaceDreamTextPrimary,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[timeBasedSelectedTabIndex]),
                        color = PaceDreamPrimary,
                        height = 3.dp
                    )
                }
            ) {
                timeBasedTabs.forEachIndexed { index, title ->
                    Tab(
                        selected = timeBasedSelectedTabIndex == index,
                        onClick = {
                            timeBasedSelectedTabIndex = index
                            onTimeBasedRoomsChanged(
                                when (index) {
                                    0 -> Consts.ROOM_TYPE
                                    1 -> Consts.REST_ROOM_TYPE
                                    2 -> Consts.EV_PARKING_TYPE
                                    3 -> Consts.PARKING_TYPE
                                    else -> Consts.ROOM_TYPE
                                }
                            )
                        },
                        text = {
                            Text(
                                text = title,
                                style = PaceDreamTypography.Callout,
                                color = if (timeBasedSelectedTabIndex == index) 
                                    PaceDreamPrimary else PaceDreamTextSecondary
                            )
                        }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(PaceDreamSpacing.MD))
            
            if (roomsState.loading) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(PaceDreamSpacing.SM)
                ) {
                    items(5) {
                        PaceDreamShimmerCard()
                    }
                }
            } else if (roomsState.rooms.isEmpty()) {
                PaceDreamEmptyState(
                    title = "No Properties Found",
                    description = "Try adjusting your search criteria or check back later.",
                    icon = Icons.Default.Search
                )
            } else {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(PaceDreamSpacing.SM)
                ) {
                    items(roomsState.rooms) { room ->
                        PaceDreamPropertyCard(
                            title = room.name ?: "Property",
                            location = room.location?.city ?: "Location",
                            price = "$${room.dynamic_price?.firstOrNull()?.price ?: "0"}/hour",
                            rating = room.rating?.toDouble() ?: 0.0,
                            reviewCount = 0, // TODO: Add review count to model
                            imageUrl = room.images?.firstOrNull(),
                            onClick = { onPropertyClick(room._id ?: "") }
                        )
                    }
                }
            }
        }
        
        // Hourly Rented Gear Section
        item {
            Spacer(modifier = Modifier.height(PaceDreamSpacing.LG))
            PaceDreamSectionHeader(
                title = "Hourly Rented Gear",
                onViewAllClick = { onViewAllClick("gear") }
            )
            
            Spacer(modifier = Modifier.height(PaceDreamSpacing.SM))
            
            Text(
                text = stringResource(R.string.help_you_what_needed),
                style = PaceDreamTypography.Callout,
                color = PaceDreamTextSecondary
            )
            
            Spacer(modifier = Modifier.height(PaceDreamSpacing.MD))
            
            var rentedGearsSelectedTabIndex by remember { mutableIntStateOf(0) }
            val rentedGearTabs = listOf("Tech Gear", "Music Gear", "Photography", "Fashion")
            
            // Enhanced Tab Component
            ScrollableTabRow(
                selectedTabIndex = rentedGearsSelectedTabIndex,
                modifier = Modifier.fillMaxWidth(),
                containerColor = PaceDreamSurface,
                contentColor = PaceDreamTextPrimary,
                indicator = { tabPositions ->
                    TabRowDefaults.Indicator(
                        modifier = Modifier.tabIndicatorOffset(tabPositions[rentedGearsSelectedTabIndex]),
                        color = PaceDreamPrimary,
                        height = 3.dp
                    )
                }
            ) {
                rentedGearTabs.forEachIndexed { index, title ->
                    Tab(
                        selected = rentedGearsSelectedTabIndex == index,
                        onClick = {
                            rentedGearsSelectedTabIndex = index
                            onRentedGearsChanged(
                                when (index) {
                                    0 -> TECH_GEAR_TYPE
                                    1 -> MUSIC_GEAR_TYPE
                                    2 -> PHOTOGRAPHY_TYPE
                                    3 -> FASHION_TYPE
                                    else -> TECH_GEAR_TYPE
                                }
                            )
                        },
                        text = {
                            Text(
                                text = title,
                                style = PaceDreamTypography.Callout,
                                color = if (rentedGearsSelectedTabIndex == index) 
                                    PaceDreamPrimary else PaceDreamTextSecondary
                            )
                        }
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(PaceDreamSpacing.MD))
            
            if (gearsState.loading) {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(PaceDreamSpacing.SM)
                ) {
                    items(5) {
                        PaceDreamShimmerCard()
                    }
                }
            } else if (gearsState.rentedGears.isEmpty()) {
                PaceDreamEmptyState(
                    title = "No Gear Available",
                    description = "Check back later for available rental gear.",
                    icon = Icons.Default.ShoppingBag
                )
            } else {
                LazyRow(
                    horizontalArrangement = Arrangement.spacedBy(PaceDreamSpacing.SM)
                ) {
                    items(gearsState.rentedGears) { gear ->
                        PaceDreamPropertyCard(
                            title = gear.name ?: "Gear Item",
                            location = gear.location ?: "Location",
                            price = "$${gear.price ?: "0"}/hour",
                            rating = gear.rating?.toDouble() ?: 0.0,
                            reviewCount = 0, // TODO: Add review count to model
                            imageUrl = gear.image,
                            onClick = { onPropertyClick(gear._id ?: "") }
                        )
                    }
                }
            }
        }
        
        // Bottom padding for better scrolling
        item {
            Spacer(modifier = Modifier.height(PaceDreamSpacing.XXXL))
        }
    }
}
