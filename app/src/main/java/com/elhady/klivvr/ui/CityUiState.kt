package com.elhady.klivvr.ui

import com.elhady.klivvr.domain.model.City
import kotlinx.coroutines.flow.MutableStateFlow


data class CityUiState(
    val cities: List<City> = emptyList(),
    val isLoading: Boolean = false,
    val errorMessages: List<String> = emptyList(),
    val originalCities: List<City> = emptyList(),
    val query: MutableStateFlow<String> = MutableStateFlow(""),
){
    val isEmpty: Boolean
        get() = cities.isEmpty()
}

//data class CityUi(
//    val country: String,
//    val name: String,
//    val id: Int,
//    val coord: CoordinatesUi
//)
//
//data class CoordinatesUi(
//    val lon: Double,
//    val lat: Double
//)