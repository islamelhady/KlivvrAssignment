package com.elhady.klivvr.domain.repository

import com.elhady.klivvr.domain.model.City

interface CitiesRepository {
    suspend fun getCities(): List<City>
    suspend fun initTrieCities(cities: List<City>)
    fun searchCities(prefix: String): List<City>
}