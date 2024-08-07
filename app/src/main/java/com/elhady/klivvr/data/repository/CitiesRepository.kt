package com.elhady.klivvr.data.repository

import com.elhady.klivvr.data.model.City

interface CitiesRepository {
    suspend fun getCities(): List<City>
}