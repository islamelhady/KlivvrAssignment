package com.elhady.klivvr.domain.usecase

import com.elhady.klivvr.domain.model.City
import com.elhady.klivvr.domain.repository.CitiesRepository
import javax.inject.Inject

class SortedAlphabeticalCitiesUseCase @Inject constructor(private val citiesRepository: CitiesRepository) {
    suspend operator fun invoke(): List<City> {
        /**
         * The requirements says that the list should be sorted alphabetically,
         * city name then country, and case is insensitive.
         */
//        return citiesRepository.getCities().sortedWith(compareBy<City> { it.name.lowercase() }.thenBy { it.country.lowercase() })
        val cities = citiesRepository.getCities()
        citiesRepository.initTrieCities(cities)
        return cities.sortedWith(compareBy({ it.name.lowercase() }, { it.country.lowercase() }))
    }

}