package com.pdmcourse2026.basictemplate.screens.resultados

import com.google.android.gms.location.places.Place

sealed class RankeState {
    object Loading : RankeState()
    data class Success(val places: List<Place>) : RankeState()
    data class Error(val message: String) : RankeState()
    object VoteSuccess : RankeState()
}