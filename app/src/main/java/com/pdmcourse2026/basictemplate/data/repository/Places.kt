package com.pdmcourse2026.basictemplate.data.repository

import com.google.android.gms.location.places.Place

interface RankeRepository {
    suspend fun getPlaces(): Result<List<Place>>
    suspend fun voteForPlace(placeId: String): Result<Unit>
}