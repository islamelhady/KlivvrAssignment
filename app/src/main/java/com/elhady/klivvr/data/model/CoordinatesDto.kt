package com.elhady.klivvr.data.model

import com.elhady.klivvr.domain.model.Coordinates
import kotlinx.serialization.Serializable

@Serializable
data class CoordinatesDto(
    val lat: Double,
    val lon: Double
)

fun CoordinatesDto.toCoordinates(): Coordinates {
    return Coordinates(lat, lon)
}