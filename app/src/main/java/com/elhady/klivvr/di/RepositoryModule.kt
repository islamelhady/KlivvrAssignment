package com.elhady.klivvr.di

import com.elhady.klivvr.data.repository.CitiesRepositoryImp
import com.elhady.klivvr.domain.repository.CitiesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

/**
 * Dagger module for providing repository dependencies.
 *
 * This module is installed in the SingletonComponent, meaning the provided dependencies
 * will have a singleton scope and be available throughout the entire application.
 */
@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    /**
     * Binds the implementation of CitiesRepository to CitiesRepositoryImp.
     *
     * This function tells Dagger to use CitiesRepositoryImp whenever an instance of
     * CitiesRepository is requested. The @Binds annotation is used to bind the interface
     * to its implementation.
     *
     * @param citiesRepositoryImp The implementation of CitiesRepository.
     * @return An instance of CitiesRepository.
     */
    @Binds
    abstract fun bindCityRepository(
        citiesRepositoryImp: CitiesRepositoryImp
    ): CitiesRepository
}