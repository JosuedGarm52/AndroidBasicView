package com.example.mybasicview

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onEach

class KardexViewModel : ViewModel() {

    // Expose screen UI state as a MutableStateFlow
    private val _uiState = MutableStateFlow(Materia("", "", "", 0))

    // Get kardexFlow from Singleton and map it to update _uiState
    init {
        Singleton.kardexFlow
            .map { kardex ->
                kardex.firstOrNull() ?: Materia("", "", "", 0)
            }
            .onEach { _uiState.value = it }
            .launchIn(viewModelScope)
    }

    // Expose the UI state as a StateFlow
    val uiState: StateFlow<Materia> = _uiState

    // Handle business logic
    fun kardexMaterial() {
        // Aquí puedes realizar cualquier lógica necesaria para actualizar el kardex
    }
}
