package com.elhady.klivvr.domain.usecase

import com.elhady.klivvr.domain.model.City
import com.elhady.klivvr.domain.repository.CitiesRepository
import javax.inject.Inject


/**
 * Searches for cities whose names start with the given prefix and returns them sorted alphabetically.
 *
 * @param prefix The prefix string to search for in city names.
 * @return A list of City objects whose names start with the specified prefix, sorted alphabetically
 *         by city name (case-insensitive) and then by country name (case-insensitive).
 */
class GetFilterCitiesUseCase @Inject constructor(private val citiesRepository: CitiesRepository) {

    operator fun invoke(prefix: String): List<City> {
        return citiesRepository.searchCities(prefix).sortedWith(compareBy<City> { it.name.lowercase() }.thenBy { it.country.lowercase() })
    }
}