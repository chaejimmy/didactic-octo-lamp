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

package com.shourov.apps.pacedream.feature.home.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shourov.apps.pacedream.core.common.result.Result.Error
import com.shourov.apps.pacedream.core.common.result.Result.Loading
import com.shourov.apps.pacedream.core.common.result.Result.Success
import com.shourov.apps.pacedream.core.common.result.asResult
import com.shourov.apps.pacedream.feature.home.domain.repository.HomeRepository
import com.shourov.apps.pacedream.feature.home.presentation.HomeScreenEvent.GetRentedGears
import com.shourov.apps.pacedream.feature.home.presentation.HomeScreenEvent.GetTimeBasedRooms
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class HomeScreenViewModel @Inject constructor(
    private val homeRepository: HomeRepository,
) : ViewModel() {

    private var _homeScreenRoomsState: MutableStateFlow<HomeScreenRoomsState> =
        MutableStateFlow(HomeScreenRoomsState())
    val homeScreenRoomsState: StateFlow<HomeScreenRoomsState> =
        _homeScreenRoomsState.asStateFlow()

    private var _homeScreenRentedGearsState: MutableStateFlow<HomeScreenRentedGearsState> =
        MutableStateFlow(HomeScreenRentedGearsState())
    val homeScreenRentedGearsState: StateFlow<HomeScreenRentedGearsState> =
        _homeScreenRentedGearsState.asStateFlow()

    fun onEvent(event: HomeScreenEvent) {
        viewModelScope.launch(Dispatchers.IO) {
            when (event) {
                is GetRentedGears -> {
                    homeRepository.getRentedGears(event.type).asResult().collectLatest { result ->
                        when (result) {
                            is Error -> _homeScreenRentedGearsState.update {
                                it.copy(
                                    loading = false,
                                    error = result.exception.message ?: "Unknown Error",
                                )
                            }

                            Loading -> _homeScreenRentedGearsState.update { it.copy(loading = true) }
                            is Success -> _homeScreenRentedGearsState.update {
                                it.copy(
                                    loading = false,
                                    rentedGears = result.data,
                                )
                            }
                        }
                    }
                }

                is GetTimeBasedRooms -> {
                    homeRepository.getTimeBasedRooms(event.type).asResult()
                        .collectLatest { result ->
                            when (result) {
                                is Error -> _homeScreenRoomsState.update {
                                    it.copy(
                                        loading = false,
                                        error = result.exception.message ?: "Unknown Error",
                                    )
                                }

                                Loading -> _homeScreenRoomsState.update { it.copy(loading = true) }
                                is Success -> _homeScreenRoomsState.update {
                                    it.copy(
                                        loading = false,
                                        rooms = result.data,
                                    )
                                }
                            }
                        }
                }
            }
        }
    }
}