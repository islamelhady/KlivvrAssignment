package com.elhady.klivvr.ui

import com.elhady.klivvr.domain.model.City


data class CityUiState(
    val cities: List<City> = emptyList()
)