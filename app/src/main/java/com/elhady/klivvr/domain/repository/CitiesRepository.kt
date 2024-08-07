package com.elhady.klivvr.domain.repository

import com.elhady.klivvr.domain.model.City

interface CitiesRepository {
    suspend fun getCities(): List<City>
}