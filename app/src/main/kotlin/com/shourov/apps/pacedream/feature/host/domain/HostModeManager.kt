package com.shourov.apps.pacedream.feature.host.domain

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HostModeManager @Inject constructor() {
    private val _isHostMode = MutableStateFlow(false)
    val isHostMode: StateFlow<Boolean> = _isHostMode.asStateFlow()
    
    private val _isHostVerified = MutableStateFlow(false)
    val isHostVerified: StateFlow<Boolean> = _isHostVerified.asStateFlow()
    
    fun toggleHostMode() {
        _isHostMode.value = !_isHostMode.value
    }
    
    fun setHostMode(enabled: Boolean) {
        _isHostMode.value = enabled
    }
    
    fun setHostVerified(verified: Boolean) {
        _isHostVerified.value = verified
    }
    
    fun canSwitchToHostMode(): Boolean {
        return _isHostVerified.value
    }
}
