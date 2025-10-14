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

package com.shourov.apps.pacedream.core.data.repository

import com.shourov.apps.pacedream.core.common.result.Result
import com.shourov.apps.pacedream.core.database.dao.BookingDao
import com.shourov.apps.pacedream.core.database.entity.BookingEntity
import com.shourov.apps.pacedream.core.network.services.PaceDreamApiService
import com.shourov.apps.pacedream.model.BookingModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class BookingRepository @Inject constructor(
    private val apiService: PaceDreamApiService,
    private val bookingDao: BookingDao
) {
    
    fun getUserBookings(userId: String): Flow<Result<List<BookingModel>>> {
        return bookingDao.getBookingsByUser(userId).map { entities ->
            Result.Success(entities.map { it.asExternalModel() })
        }
    }

    fun getBookingById(bookingId: String): Flow<Result<BookingModel?>> {
        return bookingDao.getBookingById(bookingId).map { entity ->
            Result.Success(entity?.asExternalModel())
        }
    }

    fun getBookingsByStatus(status: String): Flow<Result<List<BookingModel>>> {
        return bookingDao.getBookingsByStatus(status).map { entities ->
            Result.Success(entities.map { it.asExternalModel() })
        }
    }

    fun getBookingsByUserAndStatus(userId: String, status: String): Flow<Result<List<BookingModel>>> {
        return bookingDao.getBookingsByUserAndStatus(userId, status).map { entities ->
            Result.Success(entities.map { it.asExternalModel() })
        }
    }

    suspend fun createBooking(booking: BookingModel): Result<BookingModel> {
        return try {
            val response = apiService.createBooking(booking)
            if (response.isSuccessful) {
                // Save to local database
                bookingDao.insertBooking(booking.asEntity())
                Result.Success(booking)
            } else {
                Result.Error(Exception("Failed to create booking: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun updateBooking(bookingId: String, booking: BookingModel): Result<BookingModel> {
        return try {
            val response = apiService.updateBooking(bookingId, booking)
            if (response.isSuccessful) {
                // Update local database
                bookingDao.updateBooking(booking.asEntity())
                Result.Success(booking)
            } else {
                Result.Error(Exception("Failed to update booking: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun cancelBooking(bookingId: String): Result<Unit> {
        return try {
            val response = apiService.cancelBooking(bookingId)
            if (response.isSuccessful) {
                // Update local database
                bookingDao.deleteBookingById(bookingId)
                Result.Success(Unit)
            } else {
                Result.Error(Exception("Failed to cancel booking: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun confirmBooking(bookingId: String): Result<Unit> {
        return try {
            val response = apiService.confirmBooking(bookingId)
            if (response.isSuccessful) {
                // Update local database
                val booking = bookingDao.getBookingById(bookingId)
                // Update booking status to confirmed
                Result.Success(Unit)
            } else {
                Result.Error(Exception("Failed to confirm booking: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }

    suspend fun refreshUserBookings(userId: String): Result<Unit> {
        return try {
            val response = apiService.getUserBookings(userId)
            if (response.isSuccessful) {
                // Handle response and save to database
                // This would need to be implemented based on your API response structure
                Result.Success(Unit)
            } else {
                Result.Error(Exception("Failed to refresh bookings: ${response.message()}"))
            }
        } catch (e: Exception) {
            Result.Error(e)
        }
    }
}
