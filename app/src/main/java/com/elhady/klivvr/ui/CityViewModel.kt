package com.elhady.klivvr.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elhady.klivvr.domain.model.City
import com.elhady.klivvr.domain.usecase.FilterCitiesUseCase
import com.elhady.klivvr.domain.usecase.SortedAlphabeticalCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.flow.debounce
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(
    private val sortedAlphabeticalCitiesUseCase: SortedAlphabeticalCitiesUseCase,
    private val filterCitiesUseCase: FilterCitiesUseCase
) : ViewModel(), CityInteractionListener {

    private val _state = MutableStateFlow(CityUiState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<CityUiEvent>()
    val event = _event.asSharedFlow()

    val query = MutableStateFlow("")


    init {
        getCities()
        viewModelScope.launch {
            query.onEach {
                _state.update { it.copy(isLoading = true) }
            }.debounce(300).distinctUntilChanged()
                .collectLatest { query ->
                    if (query.isNotEmpty()) {
                        filterCities(query)
                    }else{
                        _state.update {
                            it.copy(cities = it.cities) }
                    }
                }
        }
    }

    private fun filterCities(query: String) {
            val filteredCities = filterCitiesUseCase(query)
            _state.update {
                it.copy(cities = filteredCities, isLoading = false)
            }
            Log.d(TAG, "getCityFilter: ${filteredCities.size}")
    }

    private fun getCities() {
        _state.update { it.copy(isLoading = true) }
        viewModelScope.launch {
            val sortedCities = sortedAlphabeticalCitiesUseCase()
                _state.update {
                    it.copy(cities = sortedCities, isLoading = false)
                }
            Log.d(TAG, "getCity: ${sortedCities.size}")
        }
    }

    companion object {
        private const val TAG = "CityViewModel"
    }


    override fun onClickItem(city: City) {
        viewModelScope.launch {
            _event.emit(CityUiEvent.ClickOpenMapEvent(city))
        }
    }
}