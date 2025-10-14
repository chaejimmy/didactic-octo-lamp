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

package com.pacedream.common.util

import android.net.http.HttpException
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import java.io.IOException

sealed interface Result<out T> {
    data class Success<T>(val data: T) : Result<T>
    data class Error(val exception: Throwable) : Result<Nothing>
    data object Loading : Result<Nothing>
}

fun <T> Flow<T>.asResult(): Flow<Result<T>> = map<T, Result<T>> { Result.Success(it) }
    .onStart { emit(Result.Loading) }
    .catch { emit(Result.Error(it)) }

//fun Throwable.getRealExceptionString() = when (this) {
//    is HttpException -> {  // Handle errors based on HTTP status codes
//        when (val statusCode = this.code()) {
//            in 400..499 -> {
//                "Client Error: HTTP $statusCode"
//            }
//
//            in 500..599 -> {
//                "Server Error: HTTP $statusCode"
//            }
//
//            else -> {
//                "Unexpected HTTP Response: HTTP $statusCode"
//            }
//        }
//    }
//
//    is MissingFieldException ->{
//        "Result contained missing fields ${this.missingFields}."
//    }
//
//    is IOException -> {
//        "Network Error"
//    }
//
//    is SerializationException -> {
//        "Error Parsing Response Data"
//    }
//
//    else -> {
//        "Unknown Error: ${this.message}"
//    }
//}