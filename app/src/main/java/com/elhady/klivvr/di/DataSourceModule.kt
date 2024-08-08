package com.elhady.klivvr.di

import android.content.Context
import com.elhady.klivvr.data.trie.CityTrie
import com.elhady.klivvr.data.trie.CityTrieImp
import com.elhady.klivvr.data.data_source.CitiesDataSource
import com.elhady.klivvr.data.data_source.CitiesDataSourceImp
import com.elhady.klivvr.data.trie.TrieNode
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DataSourceModule {

    /**
     * Provides an instance of CitiesDataSource.
     *
     * This function uses the @Provides annotation to tell Dagger how to create an instance
     * of CitiesDataSource. The @Singleton annotation ensures that the same instance is used
     * throughout the application. The @ApplicationContext annotation is used to inject the
     * application context.
     *
     * @param context The application context.
     * @return An instance of CitiesDataSource.
     */
    @Singleton
    @Provides
    fun provideCityDataSource(@ApplicationContext context: Context): CitiesDataSource {
        return CitiesDataSourceImp(context)
    }

    @Provides
    @Singleton
    fun provideTrie(): CityTrie {
        return CityTrieImp()
    }

}
