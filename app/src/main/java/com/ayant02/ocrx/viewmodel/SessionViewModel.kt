package com.ayant02.ocrx.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class SessionViewModel : ViewModel() {

    private val _currentSessionId =
        MutableStateFlow<Long?>(null)

    val currentSessionId: StateFlow<Long?> =
        _currentSessionId

    fun setCurrentSession(
        sessionId: Long
    ) {
        _currentSessionId.value = sessionId
    }

    fun clearSession() {
        _currentSessionId.value = null
    }
}