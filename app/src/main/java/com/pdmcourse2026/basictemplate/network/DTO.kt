package com.pdmcourse2026.basictemplate.network

import kotlinx.serialization.KSerializer
import kotlinx.serialization.Serializable

@Serializable
data class PlaceDto(
    val id: String,
    val name: String,
    val imageUrl: String,
    val votes: Int? = 0
)

@Serializable
data class VoteRequestDto(
    val placeId: String
)