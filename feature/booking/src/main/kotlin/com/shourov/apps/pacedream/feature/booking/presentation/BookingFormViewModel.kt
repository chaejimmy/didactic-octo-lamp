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

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shourov.apps.pacedream.core.common.result.Result
import com.shourov.apps.pacedream.core.data.repository.BookingRepository
import com.shourov.apps.pacedream.core.data.repository.PropertyRepository
import com.shourov.apps.pacedream.model.BookingModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.*
import javax.inject.Inject

@HiltViewModel
class BookingFormViewModel @Inject constructor(
    private val bookingRepository: BookingRepository,
    private val propertyRepository: PropertyRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(BookingFormUiState())
    val uiState: StateFlow<BookingFormUiState> = _uiState.asStateFlow()
    
    fun loadProperty(propertyId: String) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, propertyId = propertyId)
            
            propertyRepository.getPropertyById(propertyId).collect { result ->
                when (result) {
                    is Result.Success -> {
                        val property = result.data
                        if (property != null) {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                propertyName = property.name,
                                propertyImage = property.images.firstOrNull(),
                                basePrice = property.pricing.base_price,
                                currency = property.pricing.currency
                            )
                        } else {
                            _uiState.value = _uiState.value.copy(
                                isLoading = false,
                                error = "Property not found"
                            )
                        }
                    }
                    is Result.Error -> {
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            error = result.exception.message
                        )
                    }
                }
            }
        }
    }
    
    fun onStartDateChange(date: String) {
        _uiState.value = _uiState.value.copy(startDate = date)
        calculateTotalPrice()
    }
    
    fun onEndDateChange(date: String) {
        _uiState.value = _uiState.value.copy(endDate = date)
        calculateTotalPrice()
    }
    
    fun onStartTimeChange(time: String) {
        _uiState.value = _uiState.value.copy(startTime = time)
    }
    
    fun onEndTimeChange(time: String) {
        _uiState.value = _uiState.value.copy(endTime = time)
    }
    
    fun onSpecialRequestsChange(requests: String) {
        _uiState.value = _uiState.value.copy(specialRequests = requests)
    }
    
    private fun calculateTotalPrice() {
        val currentState = _uiState.value
        if (currentState.startDate.isNotEmpty() && currentState.endDate.isNotEmpty()) {
            val hours = calculateHours(currentState.startDate, currentState.endDate)
            val totalPrice = currentState.basePrice * hours
            _uiState.value = currentState.copy(totalPrice = totalPrice)
        }
    }
    
    private fun calculateHours(startDate: String, endDate: String): Double {
        return try {
            val format = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val start = format.parse(startDate)
            val end = format.parse(endDate)
            
            if (start != null && end != null) {
                val diffInMillis = end.time - start.time
                val diffInHours = diffInMillis / (1000 * 60 * 60)
                maxOf(1.0, diffInHours.toDouble())
            } else {
                1.0
            }
        } catch (e: Exception) {
            1.0
        }
    }
    
    fun createBooking(onSuccess: (String) -> Unit) {
        viewModelScope.launch {
            val currentState = _uiState.value
            
            if (currentState.startDate.isEmpty() || currentState.endDate.isEmpty()) {
                _uiState.value = currentState.copy(error = "Please select start and end dates")
                return@launch
            }
            
            val booking = BookingModel(
                id = UUID.randomUUID().toString(),
                userId = "current_user_id", // This should come from user session
                propertyId = currentState.propertyId,
                propertyName = currentState.propertyName,
                propertyImage = currentState.propertyImage,
                startDate = currentState.startDate,
                endDate = currentState.endDate,
                startTime = currentState.startTime.takeIf { it.isNotEmpty() },
                endTime = currentState.endTime.takeIf { it.isNotEmpty() },
                totalPrice = currentState.totalPrice,
                currency = currentState.currency,
                status = "PENDING",
                paymentStatus = "PENDING",
                specialRequests = currentState.specialRequests.takeIf { it.isNotEmpty() },
                hostId = "host_id", // This should come from property data
                hostName = "Host Name", // This should come from property data
                createdAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).format(Date()),
                updatedAt = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.getDefault()).format(Date())
            )
            
            when (val result = bookingRepository.createBooking(booking)) {
                is Result.Success -> {
                    onSuccess(booking.id)
                }
                is Result.Error -> {
                    _uiState.value = currentState.copy(error = result.exception.message)
                }
            }
        }
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}
