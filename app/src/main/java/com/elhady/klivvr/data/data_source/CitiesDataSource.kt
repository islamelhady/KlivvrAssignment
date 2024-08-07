package com.elhady.klivvr.data.data_source

import com.elhady.klivvr.data.model.CityDto

interface CitiesDataSource {
    suspend fun getCitiesFromSource(): List<CityDto>
}