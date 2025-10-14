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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import com.pacedream.common.composables.theme.*
import com.shourov.apps.pacedream.feature.home.presentation.R

/**
 * Property Detail Screen showcasing enhanced components
 * This demonstrates how to use the design system in a full screen
 */
@Composable
fun PropertyDetailScreen(
    propertyId: String,
    onBackClick: () -> Unit,
    onBookClick: () -> Unit,
    onShareClick: () -> Unit,
    onFavoriteClick: () -> Unit,
    modifier: Modifier = Modifier
) {
    var isFavorite by remember { mutableStateOf(false) }
    var currentImageIndex by remember { mutableStateOf(0) }
    
    val propertyImages = listOf(
        "https://example.com/image1.jpg",
        "https://example.com/image2.jpg",
        "https://example.com/image3.jpg"
    )
    
    val amenities = listOf(
        "WiFi", "Parking", "Pool", "Gym", "Kitchen", "AC", "TV", "Pet Friendly"
    )
    
    val selectedAmenities = remember { mutableStateOf(setOf<String>()) }

    LazyColumn(
        modifier = modifier
            .fillMaxSize()
            .background(PaceDreamColors.Background)
    ) {
        // Hero Image Section
        item {
            PropertyImageCarousel(
                images = propertyImages,
                currentImageIndex = currentImageIndex,
                onImageClick = { index -> currentImageIndex = index }
            )
        }
        
        // Property Info Section
        item {
            PropertyInfoSection(
                propertyName = "Luxury Modern Apartment",
                location = "Downtown Miami, FL",
                rating = 4.9f,
                reviewCount = 124,
                price = "$250/night",
                isFavorite = isFavorite,
                onFavoriteClick = { isFavorite = !isFavorite },
                onShareClick = onShareClick
            )
        }
        
        // Host Information
        item {
            Spacer(modifier = Modifier.height(PaceDreamSpacing.LG))
            HostCard(
                hostName = "Sarah Johnson",
                hostTitle = "Superhost • 5 years hosting",
                rating = 4.9f,
                responseTime = "Within an hour",
                isSuperhost = true,
                onContactClick = { /* Handle contact */ },
                onProfileClick = { /* Handle profile */ }
            )
        }
        
        // Amenities Section
        item {
            Spacer(modifier = Modifier.height(PaceDreamSpacing.LG))
            AmenitiesSection(
                amenities = amenities,
                selectedAmenities = selectedAmenities.value,
                onAmenityClick = { amenity ->
                    val current = selectedAmenities.value.toMutableSet()
                    if (current.contains(amenity)) {
                        current.remove(amenity)
                    } else {
                        current.add(amenity)
                    }
                    selectedAmenities.value = current
                }
            )
        }
        
        // Description Section
        item {
            Spacer(modifier = Modifier.height(PaceDreamSpacing.LG))
            DescriptionSection(
                description = "Experience luxury living in this stunning modern apartment located in the heart of downtown Miami. This beautifully designed space offers breathtaking ocean views, premium amenities, and easy access to the city's best attractions."
            )
        }
        
        // Location Section
        item {
            Spacer(modifier = Modifier.height(PaceDreamSpacing.LG))
            LocationSection(
                address = "123 Ocean Drive, Miami, FL 33139",
                distance = "2.5 miles from city center"
            )
        }
        
        // Reviews Section
        item {
            Spacer(modifier = Modifier.height(PaceDreamSpacing.LG))
            ReviewsSection(
                rating = 4.9f,
                reviewCount = 124,
                reviews = listOf(
                    Review("Amazing place!", "John D.", 5f, "The apartment was perfect for our stay."),
                    Review("Great location", "Sarah M.", 5f, "Right in the heart of Miami with great views."),
                    Review("Excellent host", "Mike R.", 4f, "Sarah was very responsive and helpful.")
                )
            )
        }
        
        // Bottom Spacing
        item {
            Spacer(modifier = Modifier.height(PaceDreamSpacing.XXXL))
        }
    }
    
    // Floating Action Button
    Box(
        modifier = Modifier.fillMaxSize(),
        contentAlignment = Alignment.BottomCenter
    ) {
        Card(
            modifier = Modifier
                .fillMaxWidth()
                .padding(PaceDreamSpacing.LG),
            colors = CardDefaults.cardColors(containerColor = PaceDreamColors.Card),
            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp),
            shape = RoundedCornerShape(PaceDreamRadius.LG)
        ) {
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(PaceDreamSpacing.LG),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Column {
                    Text(
                        text = "$250",
                        style = PaceDreamTypography.Title2,
                        color = PaceDreamColors.Primary,
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        text = "per night",
                        style = PaceDreamTypography.Caption,
                        color = PaceDreamColors.TextSecondary
                    )
                }
                
                Button(
                    onClick = onBookClick,
                    colors = ButtonDefaults.buttonColors(containerColor = PaceDreamColors.Primary),
                    shape = RoundedCornerShape(PaceDreamRadius.MD)
                ) {
                    Text(
                        text = "Book Now",
                        style = PaceDreamTypography.Headline,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    }
}

@Composable
private fun PropertyInfoSection(
    propertyName: String,
    location: String,
    rating: Float,
    reviewCount: Int,
    price: String,
    isFavorite: Boolean,
    onFavoriteClick: () -> Unit,
    onShareClick: () -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(PaceDreamSpacing.LG),
        colors = CardDefaults.cardColors(containerColor = PaceDreamColors.Card),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(PaceDreamRadius.LG)
    ) {
        Column(
            modifier = Modifier.padding(PaceDreamSpacing.LG)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.Top
            ) {
                Column(modifier = Modifier.weight(1f)) {
                    Text(
                        text = propertyName,
                        style = PaceDreamTypography.Title1,
                        color = PaceDreamColors.TextPrimary,
                        fontWeight = FontWeight.Bold
                    )
                    
                    Spacer(modifier = Modifier.height(PaceDreamSpacing.SM))
                    
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.LocationOn,
                            contentDescription = null,
                            tint = PaceDreamColors.TextSecondary,
                            modifier = Modifier.size(PaceDreamIconSize.SM)
                        )
                        
                        Spacer(modifier = Modifier.width(PaceDreamSpacing.XS))
                        
                        Text(
                            text = location,
                            style = PaceDreamTypography.Body,
                            color = PaceDreamColors.TextSecondary
                        )
                    }
                    
                    Spacer(modifier = Modifier.height(PaceDreamSpacing.SM))
                    
                    Row(
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Icon(
                            imageVector = Icons.Default.Star,
                            contentDescription = null,
                            tint = PaceDreamColors.Warning,
                            modifier = Modifier.size(PaceDreamIconSize.SM)
                        )
                        
                        Spacer(modifier = Modifier.width(PaceDreamSpacing.XS))
                        
                        Text(
                            text = "$rating ($reviewCount reviews)",
                            style = PaceDreamTypography.Body,
                            color = PaceDreamColors.TextPrimary,
                            fontWeight = FontWeight.Medium
                        )
                    }
                }
                
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.spacedBy(PaceDreamSpacing.SM)
                ) {
                    IconButton(onClick = onShareClick) {
                        Icon(
                            imageVector = Icons.Default.Share,
                            contentDescription = "Share",
                            tint = PaceDreamColors.Primary
                        )
                    }
                    
                    IconButton(onClick = onFavoriteClick) {
                        Icon(
                            imageVector = if (isFavorite) Icons.Default.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = if (isFavorite) "Remove from favorites" else "Add to favorites",
                            tint = if (isFavorite) PaceDreamColors.Error else PaceDreamColors.TextSecondary
                        )
                    }
                }
            }
        }
    }
}

@Composable
private fun AmenitiesSection(
    amenities: List<String>,
    selectedAmenities: Set<String>,
    onAmenityClick: (String) -> Unit
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaceDreamSpacing.LG),
        colors = CardDefaults.cardColors(containerColor = PaceDreamColors.Card),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(PaceDreamRadius.LG)
    ) {
        Column(
            modifier = Modifier.padding(PaceDreamSpacing.LG)
        ) {
            Text(
                text = "Amenities",
                style = PaceDreamTypography.Title3,
                color = PaceDreamColors.TextPrimary,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(PaceDreamSpacing.MD))
            
            LazyRow(
                horizontalArrangement = Arrangement.spacedBy(PaceDreamSpacing.SM)
            ) {
                items(amenities) { amenity ->
                    AmenityChip(
                        amenity = amenity,
                        icon = when (amenity) {
                            "WiFi" -> Icons.Default.Wifi
                            "Parking" -> Icons.Default.LocalParking
                            "Pool" -> Icons.Default.Pool
                            "Gym" -> Icons.Default.FitnessCenter
                            "Kitchen" -> Icons.Default.Kitchen
                            "AC" -> Icons.Default.Air
                            "TV" -> Icons.Default.Tv
                            "Pet Friendly" -> Icons.Default.Pets
                            else -> Icons.Default.Star
                        },
                        isSelected = selectedAmenities.contains(amenity),
                        onClick = { onAmenityClick(amenity) }
                    )
                }
            }
        }
    }
}

@Composable
private fun DescriptionSection(
    description: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaceDreamSpacing.LG),
        colors = CardDefaults.cardColors(containerColor = PaceDreamColors.Card),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(PaceDreamRadius.LG)
    ) {
        Column(
            modifier = Modifier.padding(PaceDreamSpacing.LG)
        ) {
            Text(
                text = "About this place",
                style = PaceDreamTypography.Title3,
                color = PaceDreamColors.TextPrimary,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(PaceDreamSpacing.MD))
            
            Text(
                text = description,
                style = PaceDreamTypography.Body,
                color = PaceDreamColors.TextPrimary
            )
        }
    }
}

@Composable
private fun LocationSection(
    address: String,
    distance: String
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaceDreamSpacing.LG),
        colors = CardDefaults.cardColors(containerColor = PaceDreamColors.Card),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(PaceDreamRadius.LG)
    ) {
        Column(
            modifier = Modifier.padding(PaceDreamSpacing.LG)
        ) {
            Text(
                text = "Where you'll be",
                style = PaceDreamTypography.Title3,
                color = PaceDreamColors.TextPrimary,
                fontWeight = FontWeight.Bold
            )
            
            Spacer(modifier = Modifier.height(PaceDreamSpacing.MD))
            
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.LocationOn,
                    contentDescription = null,
                    tint = PaceDreamColors.Primary,
                    modifier = Modifier.size(PaceDreamIconSize.MD)
                )
                
                Spacer(modifier = Modifier.width(PaceDreamSpacing.SM))
                
                Column {
                    Text(
                        text = address,
                        style = PaceDreamTypography.Body,
                        color = PaceDreamColors.TextPrimary,
                        fontWeight = FontWeight.Medium
                    )
                    
                    Text(
                        text = distance,
                        style = PaceDreamTypography.Caption,
                        color = PaceDreamColors.TextSecondary
                    )
                }
            }
        }
    }
}

@Composable
private fun ReviewsSection(
    rating: Float,
    reviewCount: Int,
    reviews: List<Review>
) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = PaceDreamSpacing.LG),
        colors = CardDefaults.cardColors(containerColor = PaceDreamColors.Card),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        shape = RoundedCornerShape(PaceDreamRadius.LG)
    ) {
        Column(
            modifier = Modifier.padding(PaceDreamSpacing.LG)
        ) {
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = "Reviews",
                    style = PaceDreamTypography.Title3,
                    color = PaceDreamColors.TextPrimary,
                    fontWeight = FontWeight.Bold
                )
                
                Spacer(modifier = Modifier.width(PaceDreamSpacing.SM))
                
                Row(
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Icon(
                        imageVector = Icons.Default.Star,
                        contentDescription = null,
                        tint = PaceDreamColors.Warning,
                        modifier = Modifier.size(PaceDreamIconSize.SM)
                    )
                    
                    Spacer(modifier = Modifier.width(PaceDreamSpacing.XS))
                    
                    Text(
                        text = "$rating • $reviewCount reviews",
                        style = PaceDreamTypography.Body,
                        color = PaceDreamColors.TextPrimary,
                        fontWeight = FontWeight.Medium
                    )
                }
            }
            
            Spacer(modifier = Modifier.height(PaceDreamSpacing.MD))
            
            reviews.take(3).forEach { review ->
                ReviewItem(review = review)
                Spacer(modifier = Modifier.height(PaceDreamSpacing.MD))
            }
        }
    }
}

@Composable
private fun ReviewItem(
    review: Review
) {
    Column {
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = review.author,
                style = PaceDreamTypography.Callout,
                color = PaceDreamColors.TextPrimary,
                fontWeight = FontWeight.Medium
            )
            
            Spacer(modifier = Modifier.width(PaceDreamSpacing.SM))
            
            Row(
                verticalAlignment = Alignment.CenterVertically
            ) {
                Icon(
                    imageVector = Icons.Default.Star,
                    contentDescription = null,
                    tint = PaceDreamColors.Warning,
                    modifier = Modifier.size(12.dp)
                )
                
                Spacer(modifier = Modifier.width(2.dp))
                
                Text(
                    text = review.rating.toString(),
                    style = PaceDreamTypography.Caption,
                    color = PaceDreamColors.TextSecondary
                )
            }
        }
        
        Spacer(modifier = Modifier.height(PaceDreamSpacing.XS))
        
        Text(
            text = review.comment,
            style = PaceDreamTypography.Body,
            color = PaceDreamColors.TextPrimary
        )
    }
}

data class Review(
    val comment: String,
    val author: String,
    val rating: Float,
    val date: String = ""
)
