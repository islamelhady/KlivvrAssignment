package com.elhady.klivvr.data.data_source

import android.content.Context
import android.util.Log
import com.elhady.klivvr.R
import com.elhady.klivvr.data.model.CityDto
import kotlinx.serialization.json.Json
import javax.inject.Inject

/**
 * Implementation of the CitiesLocalData interface that provides local data access for cities.
 *
 * @property context The context used to access resources.
 */
class CitiesDataSourceImp @Inject constructor(private val context: Context) : CitiesDataSource {

    /**
     * Retrieves all cities from a local JSON file.
     *
     * This function reads the JSON file located in the raw resources directory,
     * parses it into a list of CityEntity objects, and returns the list.
     * If an error occurs during the process, an empty list is returned.
     *
     * @return A list of CityEntity objects representing the cities.
     */
    override suspend fun getCitiesFromSource(): List<CityDto> {
        return try {
            val jsonString = context.resources.openRawResource(R.raw.cities).bufferedReader().readText()
            val json = Json { ignoreUnknownKeys = true }
            Log.e(TAG, "json: $jsonString")
            return json.decodeFromString<List<CityDto>>(jsonString)
        } catch (e: Exception) {
            Log.e(TAG, "Error loading cities", e)
            e.printStackTrace()
            emptyList()
        }
    }

    companion object {
        private const val TAG = "CitiesLocalDataImp"
    }
}