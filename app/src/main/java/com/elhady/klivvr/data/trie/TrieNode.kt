package com.elhady.klivvr.data.trie

import com.elhady.klivvr.domain.model.City

class TrieNode {
    val children = mutableMapOf<Char, TrieNode>()
    var isEndOfWord = false
    val cities = mutableListOf<City>()
}