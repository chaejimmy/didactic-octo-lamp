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

package com.shourov.apps.pacedream.feature.chat.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shourov.apps.pacedream.core.common.result.Result
import com.shourov.apps.pacedream.core.data.repository.MessageRepository
import com.shourov.apps.pacedream.model.MessageModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ChatListViewModel @Inject constructor(
    private val messageRepository: MessageRepository
) : ViewModel() {
    
    private val _uiState = MutableStateFlow(ChatListUiState())
    val uiState: StateFlow<ChatListUiState> = _uiState.asStateFlow()
    
    fun loadChats() {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true)
            
            // For now, using a mock user ID - this should come from user session
            val userId = "current_user_id"
            
            messageRepository.getUserMessages(userId).collect { result ->
                when (result) {
                    is Result.Success -> {
                        val chats = groupMessagesIntoChats(result.data, userId)
                        _uiState.value = _uiState.value.copy(
                            isLoading = false,
                            chats = chats,
                            error = null
                        )
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
    
    private fun groupMessagesIntoChats(messages: List<MessageModel>, currentUserId: String): List<ChatItem> {
        val chatMap = mutableMapOf<String, MutableList<MessageModel>>()
        
        // Group messages by chat ID
        messages.forEach { message ->
            chatMap.getOrPut(message.chatId) { mutableListOf() }.add(message)
        }
        
        // Convert to ChatItem list
        return chatMap.map { (chatId, messageList) ->
            val sortedMessages = messageList.sortedBy { it.timestamp }
            val lastMessage = sortedMessages.lastOrNull()
            val otherUserId = if (lastMessage?.senderId == currentUserId) {
                lastMessage.receiverId
            } else {
                lastMessage?.senderId ?: ""
            }
            
            ChatItem(
                chatId = chatId,
                otherUserId = otherUserId,
                otherUserName = "User $otherUserId", // This should come from user data
                otherUserAvatar = null, // This should come from user data
                lastMessage = lastMessage?.content ?: "",
                lastMessageTime = lastMessage?.timestamp ?: "",
                unreadCount = messageList.count { !it.isRead && it.receiverId == currentUserId }
            )
        }.sortedByDescending { it.lastMessageTime }
    }
    
    fun clearError() {
        _uiState.value = _uiState.value.copy(error = null)
    }
}

data class ChatListUiState(
    val isLoading: Boolean = false,
    val chats: List<ChatItem> = emptyList(),
    val error: String? = null
)
