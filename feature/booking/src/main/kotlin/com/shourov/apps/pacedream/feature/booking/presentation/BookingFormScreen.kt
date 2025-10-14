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

package com.shourov.apps.pacedream.feature.booking.presentation

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.pacedream.common.composables.components.PaceDreamHeroHeader
import com.pacedream.common.composables.components.PaceDreamPropertyImage
import com.pacedream.common.composables.theme.PaceDreamDesignSystem
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun BookingFormScreen(
    propertyId: String,
    modifier: Modifier = Modifier,
    viewModel: BookingFormViewModel = hiltViewModel(),
    onBookingCreated: (String) -> Unit = {}
) {
    val uiState by viewModel.uiState.collectAsState()
    
    LaunchedEffect(propertyId) {
        viewModel.loadProperty(propertyId)
    }
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PaceDreamDesignSystem.PaceDreamColors.Background)
    ) {
        PaceDreamHeroHeader(
            title = "Book Property",
            subtitle = "Complete your reservation",
            onBackClick = { /* Handle back navigation */ }
        )
        
        if (uiState.isLoading) {
            BookingFormLoadingState()
        } else {
            BookingFormContent(
                uiState = uiState,
                onStartDateChange = viewModel::onStartDateChange,
                onEndDateChange = viewModel::onEndDateChange,
                onStartTimeChange = viewModel::onStartTimeChange,
                onEndTimeChange = viewModel::onEndTimeChange,
                onSpecialRequestsChange = viewModel::onSpecialRequestsChange,
                onBookNow = {
                    viewModel.createBooking {
                        onBookingCreated(it)
                    }
                }
            )
        }
    }
}

@Composable
private fun BookingFormContent(
    uiState: BookingFormUiState,
    onStartDateChange: (String) -> Unit,
    onEndDateChange: (String) -> Unit,
    onStartTimeChange: (String) -> Unit,
    onEndTimeChange: (String) -> Unit,
    onSpecialRequestsChange: (String) -> Unit,
    onBookNow: () -> Unit
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .padding(PaceDreamDesignSystem.PaceDreamSpacing.MD)
    ) {
        // Property Summary
        PropertySummaryCard(
            propertyName = uiState.propertyName,
            propertyImage = uiState.propertyImage,
            basePrice = uiState.basePrice,
            currency = uiState.currency
        )
        
        Spacer(modifier = Modifier.height(PaceDreamDesignSystem.PaceDreamSpacing.LG))
        
        // Booking Details Form
        BookingDetailsForm(
            startDate = uiState.startDate,
            endDate = uiState.endDate,
            startTime = uiState.startTime,
            endTime = uiState.endTime,
            specialRequests = uiState.specialRequests,
            onStartDateChange = onStartDateChange,
            onEndDateChange = onEndDateChange,
            onStartTimeChange = onStartTimeChange,
            onEndTimeChange = onEndTimeChange,
            onSpecialRequestsChange = onSpecialRequestsChange
        )
        
        Spacer(modifier = Modifier.height(PaceDreamDesignSystem.PaceDreamSpacing.LG))
        
        // Price Summary
        PriceSummaryCard(
            basePrice = uiState.basePrice,
            currency = uiState.currency,
            totalPrice = uiState.totalPrice
        )
        
        Spacer(modifier = Modifier.height(PaceDreamDesignSystem.PaceDreamSpacing.LG))
        
        // Book Now Button
        Button(
            onClick = onBookNow,
            modifier = Modifier
                .fillMaxWidth()
                .height(56.dp),
            colors = ButtonDefaults.buttonColors(
                containerColor = PaceDreamDesignSystem.PaceDreamColors.Primary
            ),
            shape = RoundedCornerShape(PaceDreamDesignSystem.PaceDreamRadius.MD)
        ) {
            Text(
                text = "Book Now - ${uiState.currency} ${String.format("%.2f", uiState.totalPrice)}",
                style = PaceDreamDesignSystem.PaceDreamTypography.Button,
                color = PaceDreamDesignSystem.PaceDreamColors.OnPrimary
            )
        }
    }
}

@Composable
private fun PropertySummaryCard(
    propertyName: String,
    propertyImage: String?,
    basePrice: Double,
    currency: String
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = PaceDreamDesignSystem.PaceDreamColors.Card),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(PaceDreamDesignSystem.PaceDreamRadius.MD)
    ) {
        Row(
            modifier = Modifier.padding(PaceDreamDesignSystem.PaceDreamSpacing.MD)
        ) {
            PaceDreamPropertyImage(
                imageUrl = propertyImage,
                contentDescription = "Property: $propertyName",
                modifier = Modifier
                    .size(80.dp)
                    .clip(RoundedCornerShape(PaceDreamDesignSystem.PaceDreamRadius.SM))
            )
            
            Spacer(modifier = Modifier.width(PaceDreamDesignSystem.PaceDreamSpacing.MD))
            
            Column(
                modifier = Modifier.weight(1f)
            ) {
                Text(
                    text = propertyName,
                    style = PaceDreamDesignSystem.PaceDreamTypography.Headline,
                    color = PaceDreamDesignSystem.PaceDreamColors.OnCard,
                    fontWeight = FontWeight.SemiBold
                )
                
                Spacer(modifier = Modifier.height(PaceDreamDesignSystem.PaceDreamSpacing.SM))
                
                Text(
                    text = "$currency ${String.format("%.2f", basePrice)} per hour",
                    style = PaceDreamDesignSystem.PaceDreamTypography.Callout,
                    color = PaceDreamDesignSystem.PaceDreamColors.Primary,
                    fontWeight = FontWeight.SemiBold
                )
            }
        }
    }
}

@Composable
private fun BookingDetailsForm(
    startDate: String,
    endDate: String,
    startTime: String,
    endTime: String,
    specialRequests: String,
    onStartDateChange: (String) -> Unit,
    onEndDateChange: (String) -> Unit,
    onStartTimeChange: (String) -> Unit,
    onEndTimeChange: (String) -> Unit,
    onSpecialRequestsChange: (String) -> Unit
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = PaceDreamDesignSystem.PaceDreamColors.Card),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(PaceDreamDesignSystem.PaceDreamRadius.MD)
    ) {
        Column(
            modifier = Modifier.padding(PaceDreamDesignSystem.PaceDreamSpacing.MD)
        ) {
            Text(
                text = "Booking Details",
                style = PaceDreamDesignSystem.PaceDreamTypography.Headline,
                color = PaceDreamDesignSystem.PaceDreamColors.OnCard,
                fontWeight = FontWeight.SemiBold
            )
            
            Spacer(modifier = Modifier.height(PaceDreamDesignSystem.PaceDreamSpacing.MD))
            
            // Date Fields
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(PaceDreamDesignSystem.PaceDreamSpacing.MD)
            ) {
                OutlinedTextField(
                    value = startDate,
                    onValueChange = onStartDateChange,
                    label = { Text("Start Date") },
                    modifier = Modifier.weight(1f),
                    readOnly = true,
                    trailingIcon = {
                        Icon(Icons.Default.CalendarToday, contentDescription = "Select date")
                    }
                )
                
                OutlinedTextField(
                    value = endDate,
                    onValueChange = onEndDateChange,
                    label = { Text("End Date") },
                    modifier = Modifier.weight(1f),
                    readOnly = true,
                    trailingIcon = {
                        Icon(Icons.Default.CalendarToday, contentDescription = "Select date")
                    }
                )
            }
            
            Spacer(modifier = Modifier.height(PaceDreamDesignSystem.PaceDreamSpacing.MD))
            
            // Time Fields
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.spacedBy(PaceDreamDesignSystem.PaceDreamSpacing.MD)
            ) {
                OutlinedTextField(
                    value = startTime,
                    onValueChange = onStartTimeChange,
                    label = { Text("Start Time") },
                    modifier = Modifier.weight(1f),
                    readOnly = true,
                    trailingIcon = {
                        Icon(Icons.Default.AccessTime, contentDescription = "Select time")
                    }
                )
                
                OutlinedTextField(
                    value = endTime,
                    onValueChange = onEndTimeChange,
                    label = { Text("End Time") },
                    modifier = Modifier.weight(1f),
                    readOnly = true,
                    trailingIcon = {
                        Icon(Icons.Default.AccessTime, contentDescription = "Select time")
                    }
                )
            }
            
            Spacer(modifier = Modifier.height(PaceDreamDesignSystem.PaceDreamSpacing.MD))
            
            // Special Requests
            OutlinedTextField(
                value = specialRequests,
                onValueChange = onSpecialRequestsChange,
                label = { Text("Special Requests (Optional)") },
                modifier = Modifier.fillMaxWidth(),
                minLines = 3,
                maxLines = 5
            )
        }
    }
}

@Composable
private fun PriceSummaryCard(
    basePrice: Double,
    currency: String,
    totalPrice: Double
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = PaceDreamDesignSystem.PaceDreamColors.Card),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(PaceDreamDesignSystem.PaceDreamRadius.MD)
    ) {
        Column(
            modifier = Modifier.padding(PaceDreamDesignSystem.PaceDreamSpacing.MD)
        ) {
            Text(
                text = "Price Summary",
                style = PaceDreamDesignSystem.PaceDreamTypography.Headline,
                color = PaceDreamDesignSystem.PaceDreamColors.OnCard,
                fontWeight = FontWeight.SemiBold
            )
            
            Spacer(modifier = Modifier.height(PaceDreamDesignSystem.PaceDreamSpacing.MD))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Base Price",
                    style = PaceDreamDesignSystem.PaceDreamTypography.Body,
                    color = PaceDreamDesignSystem.PaceDreamColors.OnCard
                )
                Text(
                    text = "$currency ${String.format("%.2f", basePrice)}",
                    style = PaceDreamDesignSystem.PaceDreamTypography.Body,
                    color = PaceDreamDesignSystem.PaceDreamColors.OnCard
                )
            }
            
            Spacer(modifier = Modifier.height(PaceDreamDesignSystem.PaceDreamSpacing.SM))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Service Fee",
                    style = PaceDreamDesignSystem.PaceDreamTypography.Body,
                    color = PaceDreamDesignSystem.PaceDreamColors.OnCard
                )
                Text(
                    text = "$currency ${String.format("%.2f", totalPrice * 0.1)}",
                    style = PaceDreamDesignSystem.PaceDreamTypography.Body,
                    color = PaceDreamDesignSystem.PaceDreamColors.OnCard
                )
            }
            
            Spacer(modifier = Modifier.height(PaceDreamDesignSystem.PaceDreamSpacing.SM))
            
            Divider(color = PaceDreamDesignSystem.PaceDreamColors.Outline)
            
            Spacer(modifier = Modifier.height(PaceDreamDesignSystem.PaceDreamSpacing.SM))
            
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Text(
                    text = "Total",
                    style = PaceDreamDesignSystem.PaceDreamTypography.Headline,
                    color = PaceDreamDesignSystem.PaceDreamColors.OnCard,
                    fontWeight = FontWeight.Bold
                )
                Text(
                    text = "$currency ${String.format("%.2f", totalPrice)}",
                    style = PaceDreamDesignSystem.PaceDreamTypography.Headline,
                    color = PaceDreamDesignSystem.PaceDreamColors.Primary,
                    fontWeight = FontWeight.Bold
                )
            }
        }
    }
}

@Composable
private fun BookingFormLoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = PaceDreamDesignSystem.PaceDreamColors.Primary
        )
    }
}

data class BookingFormUiState(
    val isLoading: Boolean = false,
    val propertyId: String = "",
    val propertyName: String = "",
    val propertyImage: String? = null,
    val basePrice: Double = 0.0,
    val currency: String = "USD",
    val startDate: String = "",
    val endDate: String = "",
    val startTime: String = "",
    val endTime: String = "",
    val specialRequests: String = "",
    val totalPrice: Double = 0.0,
    val error: String? = null
)
