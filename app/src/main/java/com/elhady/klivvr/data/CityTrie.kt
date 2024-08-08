package com.elhady.klivvr.data

import com.elhady.klivvr.domain.model.City

class TrieNode {


    val children = mutableMapOf<Char, TrieNode>()
    var isEndOfWord = false
    val cities = mutableListOf<City>()

}

class CityTrie() {
    private val root = TrieNode()

    private fun insert(word: String, city: City) {
        var node = root
        for (char in word) {
            node = node.children.computeIfAbsent(char) { TrieNode() }
        }
        node.isEndOfWord = true
        node.cities.add(city)
    }


    fun insertCities(cities: List<City>) {
        for (city in cities) {
            insert(city.name.lowercase(), city)
        }
    }


    fun startsWithPrefix(prefix: String): List<City> {
        var node = root
        for (char in prefix) {
            node = node.children[char] ?: return emptyList()
        }
        return collectCities(node)
    }

    private fun collectCities(node: TrieNode): List<City> {
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
