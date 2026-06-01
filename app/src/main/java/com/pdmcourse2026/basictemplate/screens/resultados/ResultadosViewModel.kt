package com.pdmcourse2026.basictemplate.screens.resultados

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pdmcourse2026.basictemplate.data.repository.RankeRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class RankeViewModel(private val repository: RankeRepository) : ViewModel() {

    private val _uiState = MutableStateFlow<RankeState>(RankeState.Loading)
    val uiState: StateFlow<RankeState> = _uiState

    // Variables para manejar la lógica de la UI de votación
    private val _selectedPlaceId = MutableStateFlow<String?>(null)
    val selectedPlaceId: StateFlow<String?> = _selectedPlaceId

    fun loadPlaces() {
        viewModelScope.launch {
            _uiState.value = RankeState.Loading
            repository.getPlaces().fold(
                onSuccess = { places ->
                    _uiState.value = RankeState.Success(places)
                },
                onFailure = { error ->
                    _uiState.value = RankeState.Error(error.message ?: "Error desconocido")
                }
            )
        }
    }

    // Para la pantalla de resultados (ordenar de mayor a menor)
    fun loadResults() {
        viewModelScope.launch {
            _uiState.value = RankeState.Loading
            repository.getPlaces().fold(
                onSuccess = { places ->
                    val sortedPlaces = places.sortedByDescending { it.votes }
                    _uiState.value = RankeState.Success(sortedPlaces)
                },
                onFailure = { error ->
                    _uiState.value = RankeState.Error(error.message ?: "Error desconocido")
                }
            )
        }
    }

    fun selectAndVote(placeId: String) {
        _selectedPlaceId.value = placeId
        viewModelScope.launch {
            repository.voteForPlace(placeId).fold(
                onSuccess = {
                    _uiState.value = RankeState.VoteSuccess
                },
                onFailure = { error ->
                    _uiState.value = RankeState.Error(error.message ?: "Error al votar")
                    _selectedPlaceId.value = null // Revertir selección si falla
                }
            )
        }
    }
}