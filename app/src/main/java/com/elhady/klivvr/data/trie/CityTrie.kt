package com.elhady.klivvr.data.trie

import com.elhady.klivvr.domain.model.City

interface CityTrie {
    fun insert(word: String, city: City)
    fun insertCities(cities: List<City>)
    fun startsWithPrefix(prefix: String): List<City>
    fun collectCities(node: TrieNode): List<City>
}