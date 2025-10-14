package com.shourov.apps.pacedream.feature.auth.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.shourov.apps.pacedream.core.data.repository.PaceDreamRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

data class AuthUiState(
    val isLoginMode: Boolean = true,
    val firstName: String = "",
    val lastName: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = "",
    val isLoading: Boolean = false,
    val error: String? = null
)

class AuthViewModel(
    private val repository: PaceDreamRepository
) : ViewModel() {
    private val _uiState = MutableStateFlow(AuthUiState())
    val uiState: StateFlow<AuthUiState> = _uiState.asStateFlow()
    
    fun updateFirstName(firstName: String) {
        _uiState.value = _uiState.value.copy(firstName = firstName)
    }
    
    fun updateLastName(lastName: String) {
        _uiState.value = _uiState.value.copy(lastName = lastName)
    }
    
    fun updateEmail(email: String) {
        _uiState.value = _uiState.value.copy(email = email)
    }
    
    fun updatePassword(password: String) {
        _uiState.value = _uiState.value.copy(password = password)
    }
    
    fun updateConfirmPassword(confirmPassword: String) {
        _uiState.value = _uiState.value.copy(confirmPassword = confirmPassword)
    }
    
    fun switchToLogin() {
        _uiState.value = _uiState.value.copy(
            isLoginMode = true,
            error = null
        )
    }
    
    fun switchToRegister() {
        _uiState.value = _uiState.value.copy(
            isLoginMode = false,
            error = null
        )
    }
    
    fun login(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            try {
                // Validate input
                if (_uiState.value.email.isBlank() || _uiState.value.password.isBlank()) {
                    _uiState.value = _uiState.value.copy(
                        error = "Please fill in all fields",
                        isLoading = false
                    )
                    return@launch
                }
                
                // Implement actual login logic here
                // For now, just simulate success
                kotlinx.coroutines.delay(2000)
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message ?: "Login failed",
                    isLoading = false
                )
            }
        }
    }
    
    fun register(onSuccess: () -> Unit) {
        viewModelScope.launch {
            _uiState.value = _uiState.value.copy(isLoading = true, error = null)
            
            try {
                // Validate input
                if (_uiState.value.firstName.isBlank() || 
                    _uiState.value.lastName.isBlank() || 
                    _uiState.value.email.isBlank() || 
                    _uiState.value.password.isBlank() || 
                    _uiState.value.confirmPassword.isBlank()) {
                    _uiState.value = _uiState.value.copy(
                        error = "Please fill in all fields",
                        isLoading = false
                    )
                    return@launch
                }
                
                // Validate passwords match
                if (_uiState.value.password != _uiState.value.confirmPassword) {
                    _uiState.value = _uiState.value.copy(
                        error = "Passwords do not match",
                        isLoading = false
                    )
                    return@launch
                }
                
                // Validate password strength
                if (_uiState.value.password.length < 6) {
                    _uiState.value = _uiState.value.copy(
                        error = "Password must be at least 6 characters",
                        isLoading = false
                    )
                    return@launch
                }
                
                // Implement actual registration logic here
                // For now, just simulate success
                kotlinx.coroutines.delay(2000)
                onSuccess()
            } catch (e: Exception) {
                _uiState.value = _uiState.value.copy(
                    error = e.message ?: "Registration failed",
                    isLoading = false
                )
            }
        }
    }
}
