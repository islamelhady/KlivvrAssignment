package com.elhady.klivvr.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elhady.klivvr.domain.model.City
import com.elhady.klivvr.domain.usecase.GetFilterCitiesUseCase
import com.elhady.klivvr.domain.usecase.GetSortedCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
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
    private val getSortedCitiesUseCase: GetSortedCitiesUseCase,
    private val getFilterCitiesUseCase: GetFilterCitiesUseCase
) : ViewModel(), CityInteractionListener {

    private val _state = MutableStateFlow(CityUiState())
    val state = _state.asStateFlow()

    private val _event = MutableSharedFlow<CityUiEvent>()
    val event = _event.asSharedFlow()


    init {
        getCities()
        handleQueryChanges()
    }


    private fun handleQueryChanges() {
        viewModelScope.launch {
            state.value.query.debounce(300)
                .distinctUntilChanged()
                .onEach {
                    _state.update { it.copy(isLoading = true) }
                }
                .collectLatest { query ->
                    if (query.isNotEmpty()) {
                        filterCities(query)
                    } else {
                        _state.update { it.copy(cities = it.originalCities, isLoading = false) }
                    }
                }
        }
    }

    private fun filterCities(query: String) {
        execute(
            call = { getFilterCitiesUseCase(query) },
            onSuccess = ::onSuccessFilterCities,
            onError = ::onError
        )
    }

    private fun onSuccessFilterCities(cities: List<City>) {
        _state.update {
            it.copy(
                isLoading = false,
                cities = cities ,
                errorMessages = emptyList()
            )
        }
    }


    private fun getCities() {
        _state.update { it.copy(isLoading = true) }
        execute(
            call = { getSortedCitiesUseCase() },
            onSuccess = ::onSuccessGetCities,
            onError = ::onError
        )
    }

    private fun onSuccessGetCities(cities: List<City>) {
        _state.update {
            it.copy(
                isLoading = false,
                cities = cities,
                originalCities = cities,
                errorMessages = emptyList()
            )
        }
    }

    private fun onError(error: Throwable) {
        _state.update { it.copy(isLoading = false, errorMessages = listOf(error.message ?: "Unknown error")) }
        viewModelScope.launch {
            _event.emit(CityUiEvent.ShowErrorEvent(error.message ?: "Unknown error"))
        }
        Log.e(TAG, "Failed to load cities:: ${error.message} ")
    }

    private fun <T> execute(
        call: suspend () -> T,
        onSuccess: (T) -> Unit,
        onError: (Exception) -> Unit = { exception ->
            _state.update { it.copy(errorMessages = listOf(exception.message ?: "Unknown error")) }
        }
    ) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val result = call()
                onSuccess(result)
            } catch (e: Exception) {
                _state.update { it.copy(isLoading = false) }
               onError(e)
            }
        }
    }


    override fun onClickItem(city: City) {
        viewModelScope.launch {
            _event.emit(CityUiEvent.ClickOpenMapEvent(city))
        }
    }

    companion object {
        private const val TAG = "CityViewModel"
    }
}