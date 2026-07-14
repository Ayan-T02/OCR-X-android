package com.ayant02.ocrx.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.ayant02.ocrx.data.entity.OCRTextEntity
import com.ayant02.ocrx.data.repository.OCRRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class SessionViewModel(
    private val repository: OCRRepository
) : ViewModel() {

    val sessions = repository
        .getSessions()
        .stateIn(
            scope = viewModelScope,
            started = SharingStarted.WhileSubscribed(5_000),
            initialValue = emptyList()
        )

    private val _currentSessionId =
        MutableStateFlow<Long?>(null)

    val currentSessionId: StateFlow<Long?> =
        _currentSessionId

    private val _currentSessionTitle =
        MutableStateFlow("Live Scan")

    val currentSessionTitle: StateFlow<String> =
        _currentSessionTitle

    private val _sessionTexts =
        MutableStateFlow<List<OCRTextEntity>>(emptyList())

    val sessionTexts: StateFlow<List<OCRTextEntity>> =
        _sessionTexts

    private var lastSavedText = ""
    private var lastSavedAt = 0L

    fun createSession(
        title: String,
        onCreated: (Long) -> Unit
    ) {
        val cleanTitle = title.trim().ifBlank {
            "Untitled Scan"
        }

        viewModelScope.launch {
            val sessionId = repository.createSession(cleanTitle)
            _currentSessionId.value = sessionId
            _currentSessionTitle.value = cleanTitle
            lastSavedText = ""
            lastSavedAt = 0L
            onCreated(sessionId)
        }
    }

    fun saveDetectedText(
        text: String
    ) {
        val sessionId = _currentSessionId.value ?: return
        val cleanText = text.trim()

        if (cleanText.length < 3) return

        val now = System.currentTimeMillis()
        val isDuplicate = cleanText == lastSavedText
        val tooSoon = now - lastSavedAt < 2_500

        if (isDuplicate || tooSoon) return

        lastSavedText = cleanText
        lastSavedAt = now

        viewModelScope.launch {
            repository.saveText(
                sessionId = sessionId,
                text = cleanText
            )
        }
    }

    fun loadSessionTexts(
        sessionId: Long
    ) {
        viewModelScope.launch {
            repository
                .getTexts(sessionId)
                .collect {
                    _sessionTexts.value = it
                }
        }
    }

    fun clearSession() {
        _currentSessionId.value = null
        _currentSessionTitle.value = "Live Scan"
        lastSavedText = ""
        lastSavedAt = 0L
    }

    class Factory(
        private val repository: OCRRepository
    ) : ViewModelProvider.Factory {

        @Suppress("UNCHECKED_CAST")
        override fun <T : ViewModel> create(
            modelClass: Class<T>
        ): T {
            return SessionViewModel(repository) as T
        }
    }
}
