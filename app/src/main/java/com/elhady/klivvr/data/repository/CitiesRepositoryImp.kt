package com.elhady.klivvr.data.repository

import android.util.Log
import com.elhady.klivvr.data.data_source.CitiesDataSource
import com.elhady.klivvr.data.model.toCity
import com.elhady.klivvr.domain.repository.CitiesRepository
import com.elhady.klivvr.domain.model.City
import javax.inject.Inject

class CitiesRepositoryImp @Inject constructor(private val citiesLocalData: CitiesDataSource) : CitiesRepository {
    override suspend fun getCities(): List<City> {
        val city = citiesLocalData.getCitiesFromSource().map { it.toCity() }

        Log.d(TAG, "getCities: ${city.size}")
        return city
    }
    companion object{
        private const val TAG = "CitiesRepositoryImp"
    }
}