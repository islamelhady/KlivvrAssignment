package com.elhady.klivvr.data.trie

import com.elhady.klivvr.domain.model.City

class CityTrieImp: CityTrie {
    private val root = TrieNode()

    override fun insert(word: String, city: City) {
        var node = root
        for (char in word) {
            node = node.children.computeIfAbsent(char) { TrieNode() }
        }
        node.isEndOfWord = true
        node.cities.add(city)
    }


    override fun insertCities(cities: List<City>) {
        for (city in cities) {
            insert(city.name.lowercase(), city)
        }
    }

    override fun startsWithPrefix(prefix: String): List<City> {
        var node = root
        for (char in prefix) {
            node = node.children[char] ?: return emptyList()
        }
        return collectCities(node)
    }

    override fun collectCities(node: TrieNode): List<City> {
        val result = mutableListOf<City>()
        if (node.isEndOfWord) {
            result.addAll(node.cities)
        }
        for (child in node.children.values) {
            result.addAll(collectCities(child))
        }
        return result
    }
}
