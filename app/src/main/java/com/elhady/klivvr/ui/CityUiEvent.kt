package com.elhady.klivvr.ui

import com.elhady.klivvr.domain.model.City

sealed interface CityUiEvent {

    data class ClickOpenMapEvent(val city: City) : CityUiEvent
    data class ShowErrorEvent(val error: String) : CityUiEvent

}