package com.elhady.klivvr.ui

import com.elhady.klivvr.domain.model.City

interface CityInteractionListener {
    fun onClickItem(city: City)
}