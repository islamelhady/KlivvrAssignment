package com.elhady.klivvr.data.model

import com.elhady.klivvr.domain.model.City


data class CityDto(
    val country: String,
    val name: String,
    val _id: Int,
    val coord: CoordinatesDto
)

fun CityDto.toCity(): City {
    return City(
        country = country,
        name = name,
        id = _id,
        coord = coord.toCoordinates()
    )
}