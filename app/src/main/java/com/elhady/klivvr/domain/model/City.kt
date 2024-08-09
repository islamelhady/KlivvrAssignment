package com.elhady.klivvr.domain.model

//import com.elhady.klivvr.ui.CityUi

data class City(
    val country: String,
    val name: String,
    val id: Int,
    val coord: Coordinates
)

//fun City.toCityUiState(): CityUi {
//    return CityUi(
//        country = country,
//        name = name,
//        id = id,
//        coord = coord.toCoordinatesUiState()
//    )
//}