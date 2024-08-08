package com.elhady.klivvr.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elhady.klivvr.domain.model.City
import com.elhady.klivvr.domain.usecase.FilterCitiesUseCase
import com.elhady.klivvr.domain.usecase.SortedAlphabeticalCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
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
            Log.d(TAG, "query: $query")
        }
    }

    private fun filterCities(query: String) {
        viewModelScope.launch {
            val filteredCities = filterCitiesUseCase(query)
            _state.update {
                it.copy(cities = filteredCities)
            }
            Log.d(TAG, "getCityFilter: ${filteredCities.size}")
        }
    }

    private fun getCities() {
        viewModelScope.launch {
            val sortedCities = sortedAlphabeticalCitiesUseCase()
                _state.update {
                    it.copy(cities = sortedCities)
                }
            Log.d(TAG, "getCity: ${sortedCities.size}")
        }
    }

    companion object {
        private const val TAG = "CityViewModel"
    }

    override fun onClickItem(city: City) {
        TODO("Not yet implemented")
    }
}