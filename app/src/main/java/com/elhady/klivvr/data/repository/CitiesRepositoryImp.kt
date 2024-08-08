package com.elhady.klivvr.data.repository

import android.util.Log
import com.elhady.klivvr.data.trie.CityTrieImp
import com.elhady.klivvr.data.data_source.CitiesDataSource
import com.elhady.klivvr.data.model.toCity
import com.elhady.klivvr.data.trie.CityTrie
import com.elhady.klivvr.domain.repository.CitiesRepository
import com.elhady.klivvr.domain.model.City
import javax.inject.Inject

class CitiesRepositoryImp @Inject constructor(private val citiesLocalData: CitiesDataSource, private val trie: CityTrie) : CitiesRepository {

    override suspend fun getCities(): List<City> {
        val cities = citiesLocalData.getCitiesFromSource().map { it.toCity() }
        Log.d(TAG, "getCities: ${cities.size}")
        return cities
    }
    override suspend fun initTrieCities(cities: List<City>) {
        trie.insertCities(cities)
    }

    override fun searchCities(prefix: String): List<City> {
        return trie.startsWithPrefix(prefix)
    }

    companion object{
        private const val TAG = "CitiesRepositoryImp"
    }
}