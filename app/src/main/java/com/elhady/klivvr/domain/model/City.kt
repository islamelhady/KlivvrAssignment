package com.elhady.klivvr.domain.model

data class City(
    val country: String,
    val name: String,
    val id: Int,
    val coord: Coordinates
)