package com.elhady.klivvr.data.repository

import com.elhady.klivvr.data.data_source.CitiesDataSource
import com.elhady.klivvr.data.model.toCity
import com.elhady.klivvr.domain.repository.CitiesRepository
import com.elhady.klivvr.domain.model.City

class CitiesRepositoryImp(private val citiesLocalData: CitiesDataSource) : CitiesRepository {
    override suspend fun getCities(): List<City> {
        return citiesLocalData.getCitiesFromSource().map { it.toCity() }
    }
}