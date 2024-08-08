package com.elhady.klivvr.domain.usecase

import com.elhady.klivvr.domain.model.City
import com.elhady.klivvr.domain.repository.CitiesRepository
import javax.inject.Inject

class FilterCitiesUseCase @Inject constructor(private val citiesRepository: CitiesRepository) {

    operator fun invoke(prefix: String): List<City> {
        return citiesRepository.searchCities(prefix)
    }
}