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
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
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
import com.pacedream.common.composables.components.PaceDreamUserAvatar
import com.pacedream.common.composables.theme.PaceDreamDesignSystem
import com.shourov.apps.pacedream.model.BookingModel
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun BookingScreen(
    modifier: Modifier = Modifier,
    viewModel: BookingViewModel = hiltViewModel()
) {
    val uiState by viewModel.uiState.collectAsState()
    
    LaunchedEffect(Unit) {
        viewModel.loadBookings()
    }
    
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(PaceDreamDesignSystem.PaceDreamColors.Background)
    ) {
        PaceDreamHeroHeader(
            title = "My Bookings",
            subtitle = "Manage your reservations",
            onNotificationClick = { /* Handle notification */ }
        )
        
        when {
            uiState.isLoading -> {
                BookingLoadingState()
            }
            uiState.bookings.isEmpty() -> {
                BookingEmptyState()
            }
            else -> {
                BookingContent(
                    bookings = uiState.bookings,
                    onBookingClick = viewModel::onBookingClick,
                    onCancelBooking = viewModel::cancelBooking,
                    onConfirmBooking = viewModel::confirmBooking
                )
            }
        }
    }
}

@Composable
private fun BookingContent(
    bookings: List<BookingModel>,
    onBookingClick: (String) -> Unit,
    onCancelBooking: (String) -> Unit,
    onConfirmBooking: (String) -> Unit
) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(PaceDreamDesignSystem.PaceDreamSpacing.MD),
        verticalArrangement = Arrangement.spacedBy(PaceDreamDesignSystem.PaceDreamSpacing.MD)
    ) {
        items(bookings) { booking ->
            BookingCard(
                booking = booking,
                onClick = { onBookingClick(booking.id) },
                onCancel = { onCancelBooking(booking.id) },
                onConfirm = { onConfirmBooking(booking.id) }
            )
        }
    }
}

@Composable
private fun BookingCard(
    booking: BookingModel,
    onClick: () -> Unit,
    onCancel: () -> Unit,
    onConfirm: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = PaceDreamDesignSystem.PaceDreamColors.Card),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(PaceDreamDesignSystem.PaceDreamRadius.MD)
    ) {
        Column(
            modifier = Modifier.padding(PaceDreamDesignSystem.PaceDreamSpacing.MD)
        ) {
            // Property Image and Basic Info
            Row(
                modifier = Modifier.fillMaxWidth()
            ) {
                PaceDreamPropertyImage(
                    imageUrl = booking.propertyImage,
                    contentDescription = "Property: ${booking.propertyName}",
                    modifier = Modifier
                        .size(80.dp)
                        .clip(RoundedCornerShape(PaceDreamDesignSystem.PaceDreamRadius.SM))
                )
                
                Spacer(modifier = Modifier.width(PaceDreamDesignSystem.PaceDreamSpacing.MD))
                
                Column(
                    modifier = Modifier.weight(1f)
                ) {
                    Text(
                        text = booking.propertyName,
                        style = PaceDreamDesignSystem.PaceDreamTypography.Headline,
                        color = PaceDreamDesignSystem.PaceDreamColors.OnCard,
                        fontWeight = FontWeight.SemiBold
                    )
                    
                    Spacer(modifier = Modifier.height(PaceDreamDesignSystem.PaceDreamSpacing.XS))
                    
                    Text(
                        text = "Host: ${booking.hostName}",
                        style = PaceDreamDesignSystem.PaceDreamTypography.Body,
                        color = PaceDreamDesignSystem.PaceDreamColors.OnCard.copy(alpha = 0.7f)
                    )
                    
                    Spacer(modifier = Modifier.height(PaceDreamDesignSystem.PaceDreamSpacing.SM))
                    
                    Text(
                        text = "${booking.currency} ${String.format("%.2f", booking.totalPrice)}",
                        style = PaceDreamDesignSystem.PaceDreamTypography.Callout,
                        color = PaceDreamDesignSystem.PaceDreamColors.Primary,
                        fontWeight = FontWeight.SemiBold
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(PaceDreamDesignSystem.PaceDreamSpacing.MD))
            
            // Booking Details
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "Check-in",
                        style = PaceDreamDesignSystem.PaceDreamTypography.Caption,
                        color = PaceDreamDesignSystem.PaceDreamColors.OnCard.copy(alpha = 0.7f)
                    )
                    Text(
                        text = formatDate(booking.startDate),
                        style = PaceDreamDesignSystem.PaceDreamTypography.Body,
                        color = PaceDreamDesignSystem.PaceDreamColors.OnCard
                    )
                }
                
                Column {
                    Text(
                        text = "Check-out",
                        style = PaceDreamDesignSystem.PaceDreamTypography.Caption,
                        color = PaceDreamDesignSystem.PaceDreamColors.OnCard.copy(alpha = 0.7f)
                    )
                    Text(
                        text = formatDate(booking.endDate),
                        style = PaceDreamDesignSystem.PaceDreamTypography.Body,
                        color = PaceDreamDesignSystem.PaceDreamColors.OnCard
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(PaceDreamDesignSystem.PaceDreamSpacing.MD))
            
            // Status and Actions
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                StatusChip(status = booking.status)
                
                Row {
                    if (booking.status == "PENDING") {
                        TextButton(onClick = onCancel) {
                            Text("Cancel")
                        }
                        Spacer(modifier = Modifier.width(PaceDreamDesignSystem.PaceDreamSpacing.SM))
                        Button(onClick = onConfirm) {
                            Text("Confirm")
                        }
                    }
                }
            }
        }
    }
}

@Composable
private fun StatusChip(status: String) {
    val (backgroundColor, textColor) = when (status) {
        "CONFIRMED" -> PaceDreamDesignSystem.PaceDreamColors.Success to PaceDreamDesignSystem.PaceDreamColors.OnSuccess
        "PENDING" -> PaceDreamDesignSystem.PaceDreamColors.Warning to PaceDreamDesignSystem.PaceDreamColors.OnWarning
        "CANCELLED" -> PaceDreamDesignSystem.PaceDreamColors.Error to PaceDreamDesignSystem.PaceDreamColors.OnError
        else -> PaceDreamDesignSystem.PaceDreamColors.SurfaceVariant to PaceDreamDesignSystem.PaceDreamColors.OnSurfaceVariant
    }
    
    Card(
        colors = CardDefaults.cardColors(containerColor = backgroundColor),
        shape = RoundedCornerShape(PaceDreamDesignSystem.PaceDreamRadius.SM)
    ) {
        Text(
            text = status,
            style = PaceDreamDesignSystem.PaceDreamTypography.Caption,
            color = textColor,
            modifier = Modifier.padding(
                horizontal = PaceDreamDesignSystem.PaceDreamSpacing.SM,
                vertical = PaceDreamDesignSystem.PaceDreamSpacing.XS
            )
        )
    }
}

@Composable
private fun BookingLoadingState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        CircularProgressIndicator(
            color = PaceDreamDesignSystem.PaceDreamColors.Primary
        )
    }
}

@Composable
private fun BookingEmptyState() {
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Default.CalendarToday,
                contentDescription = "No bookings",
                tint = PaceDreamDesignSystem.PaceDreamColors.OnBackground.copy(alpha = 0.5f),
                modifier = Modifier.size(64.dp)
            )
            Spacer(modifier = Modifier.height(PaceDreamDesignSystem.PaceDreamSpacing.MD))
            Text(
                text = "No bookings yet",
                style = PaceDreamDesignSystem.PaceDreamTypography.Headline,
                color = PaceDreamDesignSystem.PaceDreamColors.OnBackground
            )
            Spacer(modifier = Modifier.height(PaceDreamDesignSystem.PaceDreamSpacing.SM))
            Text(
                text = "Start exploring properties to make your first booking",
                style = PaceDreamDesignSystem.PaceDreamTypography.Body,
                color = PaceDreamDesignSystem.PaceDreamColors.OnBackground.copy(alpha = 0.7f)
            )
        }
    }
}

private fun formatDate(dateString: String): String {
    return try {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
        val outputFormat = SimpleDateFormat("MMM dd, yyyy", Locale.getDefault())
        val date = inputFormat.parse(dateString)
        outputFormat.format(date ?: Date())
    } catch (e: Exception) {
        dateString
    }
}
