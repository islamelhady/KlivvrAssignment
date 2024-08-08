package com.elhady.klivvr.ui

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.elhady.klivvr.domain.model.City
import com.elhady.klivvr.domain.usecase.SortedAlphabeticalCitiesUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CityViewModel @Inject constructor(private val sortedAlphabeticalCitiesUseCase: SortedAlphabeticalCitiesUseCase): ViewModel() , CityInteractionListener{

    private val _state = MutableStateFlow(CityUiState())
    val state = _state.asStateFlow()


    init {
        getCitiesByName()
    }
    private fun getCitiesByName(){
        viewModelScope.launch {
            val cityList = sortedAlphabeticalCitiesUseCase
            _state.update {
                it.copy(cities = sortedAlphabeticalCitiesUseCase())
            }
            Log.d(TAG, "getCity: ${cityList.invoke()[8]}")
        }
    }
    companion object{
        private const val TAG = "CityViewModel"
    }

    override fun onClickItem(city: City) {
        TODO("Not yet implemented")
    }
}