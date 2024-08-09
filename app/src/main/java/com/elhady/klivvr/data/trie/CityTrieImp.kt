package com.elhady.klivvr.data.trie

import com.elhady.klivvr.domain.model.City

/**
 * Implementation of the CityTrie interface, used to efficiently store and search for City objects
 * based on their names using a Trie data structure.
 */
class CityTrieImp: CityTrie {

    // Root node of the Trie, representing the starting point of the Trie structure.
    private val root = TrieNode()


    /**
     * Inserts a word (city name) into the Trie along with its associated City object.
     *
     * @param word The name of the city to be inserted into the Trie.
     * @param city The City object associated with the word.
     */
    override fun insert(word: String, city: City) {
        var node = root
        for (char in word) {
            // Traverse the Trie, creating new nodes as necessary.
            node = node.children.computeIfAbsent(char) { TrieNode() }
        }
        // Mark the end of the word and add the city to the node.
        node.isEndOfWord = true
        node.cities.add(city)
    }

    /**
     * Inserts a list of cities into the Trie. The city names are converted to lowercase
     * before being inserted.
     *
     * @param cities The list of City objects to be inserted into the Trie.
     */
    override fun insertCities(cities: List<City>) {
        for (city in cities) {
            insert(city.name.lowercase(), city)
        }
    }

    /**
     * Searches the Trie for cities that start with the given prefix.
     *
     * @param prefix The prefix to search for.
     * @return A list of City objects whose names start with the specified prefix.
     */
    override fun startsWithPrefix(prefix: String): List<City> {
        var node = root
        for (char in prefix) {
            // Traverse the Trie according to the characters in the prefix.
            node = node.children[char] ?: return emptyList() // If a character is not found, return an empty list.
        }
        // Collect and return all cities that match the prefix.
        return collectCities(node)
    }

    /**
     * Collects all City objects from the given TrieNode and its descendants.
     *
     * @param node The starting TrieNode from which to collect cities.
     * @return A list of City objects collected from the Trie.
     */
    override fun collectCities(node: TrieNode): List<City> {
        val result = mutableListOf<City>()
        // If the node represents the end of a word, add its cities to the result.
        if (node.isEndOfWord) {
            result.addAll(node.cities)
        }
        // Recursively collect cities from all child nodes.
        for (child in node.children.values) {
            result.addAll(collectCities(child))
        }
        return result
    }
}
