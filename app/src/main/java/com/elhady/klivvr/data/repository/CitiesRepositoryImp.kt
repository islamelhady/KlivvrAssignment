package com.elhady.klivvr.data.repository

import com.elhady.klivvr.data.data_source.CitiesDataSource
import com.elhady.klivvr.data.model.City

class CitiesRepositoryImp(private val citiesLocalData: CitiesDataSource): CitiesRepository{
    override suspend fun getCities(): List<City> {
       return citiesLocalData.getCitiesFromSource()
    }
}