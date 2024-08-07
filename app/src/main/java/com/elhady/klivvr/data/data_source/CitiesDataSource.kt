package com.elhady.klivvr.data.data_source

import com.elhady.klivvr.data.model.City

interface CitiesDataSource {
    suspend fun getCitiesFromSource(): List<City>
}